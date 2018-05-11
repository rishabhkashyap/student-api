package com.student.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.student.constants.StudentMessage;
import com.student.constants.StudentStatusCode;
import com.student.entity.Student;
import com.student.entity.StudentResponse;
import com.student.exception.ResourceAlreadyExistException;
import com.student.exception.ResourceNotFoundException;
import com.student.repository.StudentRepo;
import com.student.service.StudentService;

@RestController
public class StudentController {
	
	
	
	@Autowired
	StudentService studentService;
	
	
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//	    CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true);
//	    binder.registerCustomEditor(LocalDate.class, editor);
//	}
//	
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
			throw new ResourceAlreadyExistException("Mobile number or email id  already exist.");
		}else {
			studentService.save(student);
			return new ResponseEntity<StudentResponse>(new StudentResponse(StudentMessage.STUDENT_RECORD_CREATED,StudentStatusCode.RESOURCE_CREATED.toString() ),
					HttpStatus.CREATED);
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
