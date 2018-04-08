package com.student.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.entity.Course;
import com.student.entity.EnrolledCourse;
import com.student.repository.CourseRepo;
import com.student.repository.EnrolledCourseRepo;

@Service
public class CourseService {

	@Autowired
	private CourseRepo courseRepo;
	@Autowired
	private EnrolledCourseRepo enrolledCourseRepo;

	public Course getCourseById(Long courseId) {
		return courseRepo.findByCourseId(courseId);
	}

	public void updateCourse(Long CourseId, Course updatedCourse) {
		Course course = courseRepo.findByCourseId(CourseId);
		course.setCourseDescription(updatedCourse.getCourseDescription());
		course.setCourseName(updatedCourse.getCourseName());
		course.setInstructor(updatedCourse.getInstructor());
		courseRepo.save(course);
	}

	public boolean courseExists(Course newCourse) {

		Course course = courseRepo.findByCourseNameAndCourseDescriptionAndInstructor(newCourse.getCourseName(),
				newCourse.getCourseDescription(), newCourse.getInstructor());
		if (course == null) {
			return false;
		} else {
			return course.equals(newCourse);
		}

	}

	public Course saveCourse(Course course) {
		if (course != null) {
			return courseRepo.save(course);
		}else {
			return null;
		}
	}

	public void deleteCourse(Course course) {
		if (course != null) {
			courseRepo.delete(course);
			enrolledCourseRepo.deleteByCourseId(course.getCourseId());
		}
	}
	
	public List<Course> getAllCourses(){
		List<Course> courses= new ArrayList<>();
		courses=courseRepo.findAll();
		return courses;
	}

}
