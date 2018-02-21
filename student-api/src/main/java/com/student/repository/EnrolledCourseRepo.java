package com.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.student.entity.EnrolledCourse;

public interface EnrolledCourseRepo extends JpaRepository<EnrolledCourse, Long> {
	@Transactional
	public void deleteByCourseId(Long courseId);

}
