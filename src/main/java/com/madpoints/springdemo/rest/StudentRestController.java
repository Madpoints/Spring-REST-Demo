package com.madpoints.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madpoints.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	private List<Student> theStudents;
	 
	// @PostConstruct loads data only once
	@PostConstruct
	public void LoadData() {
		
		theStudents = new ArrayList<>();
		
		theStudents.add(new Student("Peter", "Parker"));
		theStudents.add(new Student("Mary-Jane", "Watson"));
		theStudents.add(new Student("Miles", "Morales"));
	}
	

	// endpoint "/students" 
	@GetMapping("/students")
	public List<Student> getStudents() {
	
		return theStudents;
	}
	
	// endpoint "/students/{studentId}" 
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		
		// check studentId against student list size
		if((studentId >= theStudents.size()) || (studentId < 0)) {
			throw new StudentNotFoundException("Student id not found - " + studentId);
		}
		
		return theStudents.get(studentId);
	}
	
	// exception handler
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exception) {
		
		StudentErrorResponse error = new StudentErrorResponse();
		
		error.setStatus((HttpStatus.NOT_FOUND.value()));
		error.setMessage(exception.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	// catchall exception handler
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(Exception exception) {
		
		StudentErrorResponse error = new StudentErrorResponse();
		
		error.setStatus((HttpStatus.BAD_REQUEST.value()));
		error.setMessage(exception.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
