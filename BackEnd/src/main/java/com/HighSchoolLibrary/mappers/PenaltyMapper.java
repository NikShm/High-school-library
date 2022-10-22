package com.HighSchoolLibrary.mappers;


import com.HighSchoolLibrary.dto.PenaltyDTO;
import com.HighSchoolLibrary.dto.StudentDTO;
import com.HighSchoolLibrary.dto.TeacherDTO;
import com.HighSchoolLibrary.dto.UserDTO;
import com.HighSchoolLibrary.entities.Penalty;
import com.HighSchoolLibrary.entities.Student;
import com.HighSchoolLibrary.entities.Teacher;
import com.HighSchoolLibrary.entities.User;
import org.springframework.stereotype.Component;

/*
@author Микола
@project High_school_library
@class PenaltyMapper
@version 1.0.0
@since 01.09.2022 - 15.08
*/
@Component
public class PenaltyMapper {
    public PenaltyDTO toDto(Penalty penalty) {
        return new PenaltyDTO(penalty);
    }
}
