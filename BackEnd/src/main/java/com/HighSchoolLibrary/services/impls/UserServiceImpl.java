package com.HighSchoolLibrary.services.impls;


import com.HighSchoolLibrary.dto.LogInDTO;
import com.HighSchoolLibrary.dto.PageDTO;
import com.HighSchoolLibrary.dto.search.SearchDTO;
import com.HighSchoolLibrary.dto.search.UserSearch;
import com.HighSchoolLibrary.dto.usersDTO.LibrarianDTO;
import com.HighSchoolLibrary.dto.usersDTO.StudentDTO;
import com.HighSchoolLibrary.dto.usersDTO.TeacherDTO;
import com.HighSchoolLibrary.dto.usersDTO.UserDTO;
import com.HighSchoolLibrary.entities.users.Librarian;
import com.HighSchoolLibrary.entities.users.Student;
import com.HighSchoolLibrary.entities.users.Teacher;
import com.HighSchoolLibrary.entities.users.User;
import com.HighSchoolLibrary.enums.RoleType;
import com.HighSchoolLibrary.exceptions.DatabaseFetchException;
import com.HighSchoolLibrary.mappers.UserMapper;
import com.HighSchoolLibrary.repositoriesJPA.StudentRepository;
import com.HighSchoolLibrary.repositoriesJPA.UsersRepository;
import com.HighSchoolLibrary.services.UserService;
import com.HighSchoolLibrary.utils.QueryHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
@author Микола
@project High_school_library
@class UserServiceImpl
@version 1.0.0
@since 17.08.2022 - 22.31
*/
@Service
public class UserServiceImpl implements UserService {

    private final UsersRepository repository;
    private final UserMapper mapper;
    private final StudentRepository studentRepository;
    private final EntityManager entityManager;

    public UserServiceImpl(UsersRepository repository, UserMapper mapper, StudentRepository studentRepository, EntityManager entityManager) {
        this.repository = repository;
        this.mapper = mapper;
        this.studentRepository = studentRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<UserDTO> getAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PageDTO<UserDTO> getPage(SearchDTO<UserSearch> search) {
        Pageable pageable = PageRequest.of(search.getPage(), search.getPageSize(), QueryHelper.getSort(search.getSortDirection(),
                search.getSortField()));
        Page<User> all = repository.findAll((root, query, criteriaBuilder) -> getPredicate(search, criteriaBuilder, root), pageable);
        PageDTO<UserDTO> dto = new PageDTO<>();
        dto.setContent(all.stream().map(mapper::toDto).collect(Collectors.toList()));
        dto.setTotalItem(all.getTotalElements());
        return dto;
    }

    private Predicate getPredicate(SearchDTO<UserSearch> search, CriteriaBuilder criteriaBuilder, Root<User> user) {
        List<Predicate> predicates = new ArrayList<>();
        String value = search.getSearchPattern().getSearch();
        if (value != null) {
            predicates.add(criteriaBuilder.or(QueryHelper.ilike(user.get("surname"), criteriaBuilder, value),
                    QueryHelper.ilike(user.get("name"), criteriaBuilder, value)));
        }
        RoleType roleType = search.getSearchPattern().getRole();
        if (roleType != null){
            switch (roleType){
                case USER -> predicates.add(criteriaBuilder.equal(user.get("role"),RoleType.NONE));
                case OPERATOR -> predicates.add(criteriaBuilder.equal(user.get("role"),RoleType.USER));
                case ADMIN -> predicates.add(criteriaBuilder.or(criteriaBuilder.equal(user.get("role"),RoleType.USER)
                        ,criteriaBuilder.or(criteriaBuilder.equal(user.get("role"),RoleType.OPERATOR))));
            }
        }
        return predicates.size() == 1 ? predicates.get(0) : criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    @Override
    public Optional<UserDTO> getOneUser(Integer id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public void update(UserDTO dto) throws IOException {
        repository.findById(dto.getId()).orElseThrow(() -> new DatabaseFetchException(dto.getId(), User.class.getSimpleName()));
        User updatedUser = mapper.toEntity(dto);
        repository.save(updatedUser);
    }

    @Override
    public void delete(Integer id) throws IOException {
        repository.findById(id).orElseThrow(() -> new DatabaseFetchException(id, User.class.getSimpleName()));
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Integer createStudent(StudentDTO dto) {
        String INSERT_USER = "insert into users(name, surname, role, login, password, type)\n" +
                "values (?1, ?2, ?3, ?4, ?5, ?6);";
        entityManager.createNativeQuery(INSERT_USER).setParameter(1, dto.getName())
                .setParameter(2, dto.getSurname())
                .setParameter(3, RoleType.USER.toString())
                .setParameter(4, dto.getLogin())
                .setParameter(5, dto.getPassword())
                .setParameter(6, dto.getType())
                .executeUpdate();
        dto.setId((Integer) entityManager.createNativeQuery("select id from  users ORDER By id DESC").getResultList().get(0));
        String INSERT_STUDENTS = "insert into student(id, faculty, \"group\", subgroup)\n" +
                "values (?1, ?2, ?3, ?4);";
        entityManager.createNativeQuery(INSERT_STUDENTS).setParameter(1, dto.getId())
                .setParameter(2, dto.getFaculty())
                .setParameter(3, dto.getGroup())
                .setParameter(4, dto.getSubgroup())
                .executeUpdate();
        return 1;
    }
    @Override
    @Transactional
    public Integer createTeacher(TeacherDTO dto) {
        String INSERT_USER = "insert into users(name, surname, role, login, password, type)\n" +
                "values (?1, ?2, ?3, ?4, ?5, ?6);";
        entityManager.createNativeQuery(INSERT_USER).setParameter(1, dto.getName())
                .setParameter(2, dto.getSurname())
                .setParameter(3, RoleType.USER.toString())
                .setParameter(4, dto.getLogin())
                .setParameter(5, dto.getPassword())
                .setParameter(6, dto.getType())
                .executeUpdate();
        dto.setId((Integer) entityManager.createNativeQuery("select id from  users ORDER By id DESC").getResultList().get(0));
        String INSERT_STUDENTS = "insert into teacher(id, cathedra, degree, rank)\n" +
                "values (?1, ?2, ?3, ?4);";
        entityManager.createNativeQuery(INSERT_STUDENTS).setParameter(1, dto.getId())
                .setParameter(2, dto.getRank())
                .setParameter(3, dto.getCathedra())
                .setParameter(4, dto.getRank())
                .executeUpdate();
        return 1;
    }
    @Override
    @Transactional
    public Integer createLibrarian(LibrarianDTO dto) {
        String INSERT_USER = "insert into users(name, surname, role, login, password, type)\n" +
                "values (?1, ?2, ?3, ?4, ?5, ?6);";
        entityManager.createNativeQuery(INSERT_USER).setParameter(1, dto.getName())
                .setParameter(2, dto.getSurname())
                .setParameter(3, RoleType.USER.toString())
                .setParameter(4, dto.getLogin())
                .setParameter(5, dto.getPassword())
                .setParameter(6, dto.getType())
                .executeUpdate();
        System.out.println(entityManager.createNativeQuery("select id from  users ORDER By id DESC").getResultList());
        dto.setId((Integer) entityManager.createNativeQuery("select id from  users ORDER By id DESC").getResultList().get(0));
        String INSERT_STUDENTS = "insert into librarian(id, position )\n" +
                "values (?1, ?2);";
        entityManager.createNativeQuery(INSERT_STUDENTS).setParameter(1, dto.getId())
                .setParameter(2, dto.getPosition())
                .executeUpdate();
        return 1;
    }

    @Override
    public LogInDTO authorize(String login, String password) {
        User user = repository.findByLoginAndPassword(login,password);
        return user == null?null:mapper.toLogInDTO(user) ;
    }
}
