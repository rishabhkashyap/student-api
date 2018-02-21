package com.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.entity.EnrolledCourse;
import com.student.repository.EnrolledCourseRepo;

@Service
public class EnrolledCourseService {

	@Autowired
	EnrolledCourseRepo enrolledCourseRepo;
	
	public void enrollStudent(EnrolledCourse enrolledcourse) {
		if(enrolledcourse!=null) {
			enrolledCourseRepo.save(enrolledcourse);
		}
		
	}
}
