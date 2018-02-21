package com.student.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.student.entity.EnrolledCourse;
import com.student.entity.Student;
import com.student.exception.ResourceAlreadyExistException;
import com.student.exception.ResourceNotFoundException;
import com.student.service.CourseService;
import com.student.service.EnrolledCourseService;
import com.student.service.StudentService;

@RestController
public class EnrolledCourseController {

	@Autowired
	StudentService studentService;

	@Autowired
	EnrolledCourseService enrolledCourseService;

	@Autowired
	CourseService courseService;

	@PostMapping("/enrollStudent/{studentId}")
	public ResponseEntity enrollStudent(@PathVariable Long studentId, @Valid @RequestBody EnrolledCourse enrolledCourse,
			BindingResult result) throws ResourceNotFoundException, ResourceAlreadyExistException {
		if (result.hasErrors()) {
			return new ResponseEntity(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		} else {
			Student student = studentService.getStudentById(studentId);
			if (student == null) {
				throw new ResourceNotFoundException(studentId);
			} else if (courseService.getCourseById(enrolledCourse.getCourseId()) == null) {
				throw new ResourceNotFoundException(enrolledCourse.getCourseId());
			} else if (student.isEnrolled(enrolledCourse)) {
				throw new ResourceAlreadyExistException("Student is enrolled in this course");
			} else {

				enrolledCourse.setStudent(student);
				enrolledCourseService.enrollStudent(enrolledCourse);
				return new ResponseEntity(HttpStatus.CREATED);

			}
		}

	}
}
