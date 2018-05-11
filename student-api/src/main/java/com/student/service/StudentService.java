package com.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		Student student = studentRepo.findByEmailIdOrMobileNo(
				newStudent.getEmailId(), newStudent.getMobileNo());
		if (student == null) {
			return false;
		} else {
			return true;
		}

	}

	public void save(Student student) {
		if (student != null) {
			studentRepo.save(student);
		}

	}

	@Transactional
	public void updateStudent(Long studentId, Student updatedStudent) {
		Student student = studentRepo.findByStudentId(studentId);
		student.setFirstname(updatedStudent.getFirstname());
		student.setLastname(updatedStudent.getLastname());
		student.setEmailId(updatedStudent.getEmailId());
		student.setEnrolledCourses(updatedStudent.getEnrolledCourses());
		student.setMobileNo(updatedStudent.getMobileNo());
		// studentRepo.save(student);

	}

	public void deleteStudent(Student student) {
		if (student != null) {
			studentRepo.delete(student);
		}
	}

}
