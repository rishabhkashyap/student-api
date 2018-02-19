package com.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {

	public Student findByStudentId(Long studentId);
	
	public Student findByFirstnameAndLastnameAndEmailIdAndMobileNo(String firstname,
			String lastname,
			String emailId,
			String mobileNo);

}
