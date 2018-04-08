package com.student.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.student.constants.StudentMessage;
import com.student.constants.StudentStatusCode;
import com.student.entity.Course;
import com.student.entity.StudentResponse;
import com.student.exception.ResourceAlreadyExistException;
import com.student.exception.ResourceNotFoundException;
import com.student.repository.CourseRepo;
import com.student.service.CourseService;

@RestController
@CrossOrigin
public class CourseController {
	@Autowired
	CourseService courseService;

	@GetMapping("/course/{courseId}")
	public ResponseEntity<Course> getCourseData(@PathVariable Long courseId) throws ResourceNotFoundException {
		Course course = courseService.getCourseById(courseId);

		if (course != null) {
			return new ResponseEntity<Course>(course, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException(courseId);
		}

	}
	
	@GetMapping("/courses")
	public ResponseEntity<List<Course>> getAllCourse() throws ResourceNotFoundException{
		List courses = courseService.getAllCourses();
		if(courses!=null && courses.size()>0){
			return new ResponseEntity<List<Course>>(courses,HttpStatus.OK);
		}else{
			throw new ResourceNotFoundException("Courses does not exist"); 
		}
		
	}

	@PostMapping("/course/create")
	public ResponseEntity createCourse(@Valid @RequestBody Course newCourse, BindingResult result)
			throws ResourceAlreadyExistException {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getFieldError());
		} else {
			if (courseService.courseExists(newCourse)) {
				throw new ResourceAlreadyExistException("Course already exist.Try adding new course");
			} else {
				Course course = courseService.saveCourse(newCourse);
				if (course != null) {
					return new ResponseEntity(new StudentResponse(StudentMessage.COURSE_CREATED,
							StudentStatusCode.RESOURCE_CREATED.getValue()), HttpStatus.CREATED);
				} else {
					return new ResponseEntity(HttpStatus.BAD_REQUEST);
				}

			}

		}
	}

	@DeleteMapping("/course/{courseId}")
	public ResponseEntity deleteCourse(@PathVariable Long courseId) throws ResourceNotFoundException {
		Course course = courseService.getCourseById(courseId);

		if (course != null) {
			courseService.deleteCourse(course);
			return new ResponseEntity(course, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException(courseId);
		}

	}

	@PutMapping("/course/{courseId}")
	public ResponseEntity updateCourse(@PathVariable Long courseId, @Valid @RequestBody Course updatedCourse,
			BindingResult result) throws ResourceAlreadyExistException, ResourceNotFoundException {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updatedCourse);
		} else {
			Course course = courseService.getCourseById(courseId);
			if (course == null) {
				throw new ResourceNotFoundException(courseId);
			} else if (course.equals(updatedCourse)) {
				throw new ResourceAlreadyExistException("No update is made");
			} else {

				courseService.updateCourse(courseId, updatedCourse);
				return new ResponseEntity(new StudentResponse(StudentMessage.COURSE_UPDATED,StudentStatusCode.RESOURCE_UPDATED.getValue()),HttpStatus.OK);
			}

		}
	}

}
