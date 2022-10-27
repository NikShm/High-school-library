package com.HighSchoolLibrary.mappers;

import com.HighSchoolLibrary.dto.LogInDTO;
import com.HighSchoolLibrary.dto.usersDTO.*;
import com.HighSchoolLibrary.entities.users.*;
import org.springframework.stereotype.Component;


/*
@author Микола
@project FreshBeauty
@class UserMapper
@version 1.0.0
@since 03.08.2022 - 15.19
*/
@Component
public class UserMapper {

    public UserDTO toDto(User user) {
        UserDTO userDTO = null;
        switch (user.getType()) {
            case ("Student") -> userDTO = new StudentDTO((Student) user);
            case ("Teacher") -> userDTO = new TeacherDTO((Teacher) user);
            case ("Administrator") -> userDTO = new AdministratorDTO((Administrator) user);
            case ("Librarian") -> userDTO = new LibrarianDTO((Librarian) user);
        }
        return userDTO;
    }

    public User toEntity(UserDTO dto) {
        User user = null;
        switch (dto.getType()) {
            case "Student" -> user = new Student((StudentDTO) dto);
            case "Teacher" -> user = new Teacher((TeacherDTO) dto);
            case ("Administrator") -> user = new Administrator((AdministratorDTO) dto);
            case ("Librarian") -> user = new Librarian((LibrarianDTO) dto);
        }
        return user;
    }

    public User toEntity(User user, UserDTO dto) {
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setType(dto.getType());
        user.setRole(dto.getRole());
        user.setCreatedAt(dto.getCreatedAt());
        switch (dto.getType()) {
            case "Student" -> user = toStudentEntity((Student) user, (StudentDTO) dto);
            case "Teacher" -> user = toTeacherEntity((Teacher) user, (TeacherDTO) dto);
        }
        return user;
    }

    public Student toStudentEntity(Student student, StudentDTO dto) {
        student.setFaculty(dto.getFaculty());
        student.setGroup(dto.getGroup());
        student.setSubgroup(dto.getSubgroup());
        return student;
    }

    public Teacher toTeacherEntity(Teacher teacher, TeacherDTO dto) {
        teacher.setCathedra(dto.getCathedra());
        teacher.setDegree(dto.getDegree());
        teacher.setRank(dto.getRank());
        return teacher;
    }

    public LogInDTO toLogInDTO(User user){
        LogInDTO logInDTO = new LogInDTO();
        logInDTO.setRole(user.getRole());
        logInDTO.setId(user.getId());
        return logInDTO;
    }
}
