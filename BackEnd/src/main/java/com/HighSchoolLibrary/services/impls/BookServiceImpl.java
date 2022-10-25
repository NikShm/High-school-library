package com.HighSchoolLibrary.services.impls;


import com.HighSchoolLibrary.dto.BookDTO;
import com.HighSchoolLibrary.dto.PageDTO;
import com.HighSchoolLibrary.dto.search.SearchAuthorsBookDTO;
import com.HighSchoolLibrary.entities.Book;
import com.HighSchoolLibrary.mappers.BookMapper;
import com.HighSchoolLibrary.services.BookService;
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
    private final BookMapper mapper;

    public BookServiceImpl(EntityManager entityManager, BookMapper mapper) {
        this.entityManager = entityManager;
        this.mapper = mapper;
    }

    @Override
    public PageDTO<BookDTO> getPage(SearchAuthorsBookDTO search) {
        List<BookDTO> postDTOS = new ArrayList<>();
        for (Object entity : entityManager.createNativeQuery(getPageQuery(search), Book.class).getResultList()) {
            postDTOS.add(mapper.toDto((Book) entity));
        }
        Page<BookDTO> page = new PageImpl<>(postDTOS);
        PageDTO<BookDTO> pageDTO = new PageDTO<>();
        pageDTO.setContent(page.getContent());
        pageDTO.setTotalItem(((BigInteger) entityManager.createNativeQuery(getCountQuery(search.getAuthorId())).getSingleResult()).longValue());
        return pageDTO;
    }

    private StringBuilder getQuery() {
        return new StringBuilder();
    }

    private String getPageQuery(SearchAuthorsBookDTO search) {
        StringBuilder query = getQuery();
        if (search.getPage() != null && search.getPageSize() != null && search.getSearch() != null) {
            query.append("SELECT * FROM book");
            if (search.getAuthorId() != null) {
                query.append(" Left JOIN book_author ba on book.id = ba.bookid " +
                        "Left JOIN author a on ba.authorid = a.id");
            }
            query.append(" where ");
            if (!Objects.equals(search.getSearch(), "")) {
                query.append("book.ts_description @@ phraseto_tsquery('english', '").append(search.getSearch()).append("')")
                        .append(" or ");
            }
            query.append("book.name like '%").append(search.getSearch()).append("%'");
            if (search.getAuthorId() != null) {
                query.append(" and a.id = ").append(search.getAuthorId());
            }
            if (search.getSortDirection() != null && search.getSortField() != null) {
                query.append(" ORDER BY book.").append(search.getSortField()).append(" ").append(search.getSortDirection());
            }
            query.append(" limit ").append(search.getPageSize()).append(" offset ").append(search.getPage() * search.getPageSize());
        }
        return query.toString();
    }

    private String getCountQuery(Integer authorId) {
        StringBuilder query = getQuery();
        query.append("SELECT count(*) FROM book");
        if (authorId != null) {
            query.append(" Left JOIN book_author ba on book.id = ba.bookid " +
                    "Left JOIN author a on ba.authorid = a.id");
            query.append(" where a.id = ").append(authorId);
        }
        return query.toString();
    }
}
