package com.HighSchoolLibrary.services.impls;


import com.HighSchoolLibrary.dto.BookDTO;
import com.HighSchoolLibrary.dto.BookMap;
import com.HighSchoolLibrary.dto.PageDTO;
import com.HighSchoolLibrary.dto.search.BookSearch;
import com.HighSchoolLibrary.dto.search.SearchDTO;
import com.HighSchoolLibrary.dto.search.SearchPattern;
import com.HighSchoolLibrary.entities.Book;
import com.HighSchoolLibrary.mappers.BookMapper;
import com.HighSchoolLibrary.services.BookService;
import com.HighSchoolLibrary.services.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
@author Микола
@project High_school_library
@class BookServiceImpl
@version 1.0.0
@since 05.09.2022 - 22.05
*/
@Service
public class BookServiceImpl implements BookService {
    private final EntityManager entityManager;
    private final OrderService orderService;
    private final BookMapper mapper;

    public BookServiceImpl(EntityManager entityManager, OrderService orderService, BookMapper mapper) {
        this.entityManager = entityManager;
        this.orderService = orderService;
        this.mapper = mapper;
    }

    @Override
    public PageDTO<BookDTO> getPage(SearchDTO<BookSearch> search) {
        List<BookDTO> postDTOS = new ArrayList<>();
        for (Object entity : entityManager.createNativeQuery(getPageQuery(search), Book.class).getResultList()) {
            postDTOS.add(mapper.toDto((Book) entity));
        }
        List<Integer> ids = postDTOS.stream().map(BookDTO::getId).toList();
        List<BookMap> countsOrder = orderService.getCount(ids);
        postDTOS.forEach(book -> {
            for (BookMap bookMap : countsOrder) {
                if (bookMap.getIdBook().equals(book.getId())) {
                    book.setCount(book.getCount() - bookMap.getCount());
                    break;
                }
            }
        });
        Page<BookDTO> page = new PageImpl<>(postDTOS);
        PageDTO<BookDTO> pageDTO = new PageDTO<>();
        pageDTO.setContent(page.getContent());
        pageDTO.setTotalItem(((BigInteger) entityManager.createNativeQuery(getCountQuery(search)).getSingleResult()).longValue());
        return pageDTO;
    }

    private StringBuilder getQuery() {
        return new StringBuilder();
    }

    private String getPageQuery(SearchDTO<BookSearch> search) {
        StringBuilder query = getQuery();
        BookSearch searchPattern = search.getSearchPattern();
        query.append("SELECT * FROM book");
        if (searchPattern != null && searchPattern.getSearch() != null) {
            if (searchPattern.getAuthorId() != null) {
                query.append(" Left JOIN book_author ba on book.id = ba.bookid " +
                        "Left JOIN author a on ba.authorid = a.id");
            }
            query.append(" where ");
            if (!Objects.equals(searchPattern.getSearch(), "")) {
                query.append("book.ts_description @@ phraseto_tsquery('english', '").append(searchPattern.getSearch()).append("')")
                        .append(" or ");
            }
            query.append("book.name like '%").append(searchPattern.getSearch()).append("%'");
            if (searchPattern.getAuthorId() != null) {
                query.append(" and a.id = ").append(searchPattern.getAuthorId());
            }
            if (search.getSortDirection() != null && search.getSortField() != null) {
                query.append(" ORDER BY book.").append(search.getSortField()).append(" ").append(search.getSortDirection());
            }
        }
        if (search.getPage() != null && search.getPageSize() != null) {
            query.append(" limit ").append(search.getPageSize()).append(" offset ").append(search.getPage() * search.getPageSize());
        }

        return query.toString();
    }

    private String getCountQuery(SearchDTO<BookSearch> search) {
        StringBuilder query = getQuery();
        BookSearch searchPattern = search.getSearchPattern();
        query.append("SELECT count(*) FROM book");
        if (searchPattern != null && searchPattern.getSearch() != null) {
            if (searchPattern.getAuthorId() != null) {
                query.append(" Left JOIN book_author ba on book.id = ba.bookid " +
                        "Left JOIN author a on ba.authorid = a.id");
            }
            query.append(" where ");
            if (!Objects.equals(searchPattern.getSearch(), "")) {
                query.append("book.ts_description @@ phraseto_tsquery('english', '").append(searchPattern.getSearch()).append("')")
                        .append(" or ");
            }
            query.append("book.name like '%").append(searchPattern.getSearch()).append("%'");
            if (searchPattern.getAuthorId() != null) {
                query.append(" and a.id = ").append(searchPattern.getAuthorId());
            }
        }
        return query.toString();
    }
}
