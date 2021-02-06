package com.yorkiiz.Dao;

import com.yorkiiz.domain.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface IStudentDao {
    @Select("select * from student")
    List<Student> findAll();
}
