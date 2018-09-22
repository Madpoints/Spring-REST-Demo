package com.madpoints.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

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
		
		return theStudents.get(studentId);
	}
	
}
