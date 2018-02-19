package com.student.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.student.entity.Student;
import com.student.exception.ResourceAlreadyExistException;
import com.student.exception.ResourceNotFoundException;
import com.student.repository.StudentRepo;
import com.student.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@GetMapping("/student/{studentId}")
	public ResponseEntity<Student>getStudentDetail(@PathVariable Long studentId) throws ResourceNotFoundException{
		Student student=studentService.getStudentById(studentId);
		if(student!=null) {
			return new ResponseEntity<Student>(student,HttpStatus.OK);
		}else {
			throw new ResourceNotFoundException(studentId);
		}
	}
	
	@PostMapping("/student/create")
	public ResponseEntity createStudent(@Valid @RequestBody Student student,BindingResult result) throws ResourceAlreadyExistException {
		if(result.hasErrors()) {
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
		}
		if(studentService.studentExist(student)) {
			throw new ResourceAlreadyExistException("Student already exists .Try adding new one.");
		}else {
			studentService.save(student);
			return new ResponseEntity(HttpStatus.CREATED);
		}
	}
	
	@PutMapping("/student/{studentId}")
	public ResponseEntity updateStudent(@PathVariable Long studentId,@Valid @RequestBody Student updatedStudent)
			throws ResourceAlreadyExistException,ResourceNotFoundException{
		Student student=studentService.getStudentById(studentId);
		if(student==null) {
			throw new ResourceNotFoundException(studentId);
		}
		if(student.equals(updatedStudent)) {
			throw new ResourceAlreadyExistException("No changes are made");
		}
		studentService.updateStudent(studentId,updatedStudent);
		return new ResponseEntity(HttpStatus.OK);
		
	}
	
	@DeleteMapping("/student/{studentId}")
	public ResponseEntity deleteStudent(@PathVariable Long studentId) throws ResourceNotFoundException{
		Student student=studentService.getStudentById(studentId);
		if(student==null) {
			throw new ResourceNotFoundException(studentId);
		}else {
			studentService.deleteStudent(student);
			return new ResponseEntity(HttpStatus.OK);
		}
		
	}
	
	

}
