package com.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.entity.Student;
import com.student.repository.StudentRepo;

@Service
public class StudentService {

	@Autowired
	private StudentRepo studentRepo;

	public Student getStudentById(Long studentId) {
		return studentRepo.findByStudentId(studentId);
	}

	public boolean studentExist(Student newStudent) {
		Student student = studentRepo.findByFirstnameAndLastnameAndEmailIdAndMobileNo(
				newStudent.getFirstname(), newStudent.getLastname(),  newStudent.getEmailId(),
				newStudent.getMobileNo());
		if (student == null) {
			return false;
		} else {
			return student.equals(newStudent);
		}

	}

	public void save(Student student) {
		if (student != null) {
			studentRepo.save(student);
		}

	}

	public void updateStudent(Long studentId, Student updatedStudent) {
		Student student = studentRepo.findByStudentId(studentId);
		student.setFirstname(updatedStudent.getFirstname());
		student.setLastname(updatedStudent.getLastname());
		student.setEmailId(updatedStudent.getEmailId());
		student.setEnrolledCourses(updatedStudent.getEnrolledCourses());
		student.setMobileNo(updatedStudent.getMobileNo());
		studentRepo.save(student);

	}
	
	public void deleteStudent(Student student) {
		if(student!=null) {
			studentRepo.delete(student);
		}
	}

}
