package com.madpoints.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madpoints.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	// /students endpoint
	@GetMapping("/students")
	public List<Student> getStudents() {
		
		List<Student> theStudents = new ArrayList<>();
		
		theStudents.add(new Student("Peter", "Parker"));
		theStudents.add(new Student("Mary-Jane", "Watson"));
		theStudents.add(new Student("Miles", "Morales"));
		
		return theStudents;
	}

}
