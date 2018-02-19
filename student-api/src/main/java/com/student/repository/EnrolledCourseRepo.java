package com.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.entity.EnrolledCourse;

public interface EnrolledCourseRepo extends JpaRepository<EnrolledCourse, Long> {

}
