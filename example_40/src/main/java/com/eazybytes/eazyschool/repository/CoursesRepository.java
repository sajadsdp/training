package com.eazybytes.eazyschool.repository;

import com.eazybytes.eazyschool.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursesRepository extends JpaRepository<Courses,Integer>{

//    List<Courses> findByOrderByNameDesc();

//    List<Courses> findByOrderByName();
}
