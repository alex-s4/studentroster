package com.alexproject.studentroster.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexproject.studentroster.models.Student;
import com.alexproject.studentroster.repositories.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studRepo;
	
	// Create
	public Student createStudent(Student newStudent)
	{
		return studRepo.save(newStudent);
	}
	
	// Read
	public List<Student> findAllStudent()
	{
		return studRepo.findAll();
	}
	
	public Student findOneStudent(Long id)
	{
		return studRepo.findById(id).orElse(null);
	}
	
	// Update
	public Student updateStudent(Student updatedStudent)
	{
		return this.studRepo.save(updatedStudent);
	}
	
	// Delete
	public void deleteStudent(Long id)
	{
		studRepo.deleteById(id);
	}

}
