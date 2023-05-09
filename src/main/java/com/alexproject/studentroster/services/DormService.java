package com.alexproject.studentroster.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexproject.studentroster.models.Dorm;
import com.alexproject.studentroster.models.Student;
import com.alexproject.studentroster.repositories.DormRepository;

@Service
public class DormService {

	@Autowired
	private DormRepository dormRepo;
	
	// Create
	public Dorm createDorm(Dorm newDorm)
	{
		return dormRepo.save(newDorm);
	}
	
	// Read
	public List<Dorm> findAllDorm()
	{
		return dormRepo.findAll();
	}
	
	public Dorm findOneDorm(Long id)
	{
		return dormRepo.findById(id).orElse(null);
	}
	
	// Update
	public Dorm updateDorm(Dorm updatedDorm)
	{
		return this.dormRepo.save(updatedDorm);
	}
	
	// Delete
	public void deleteDorm(Long id)
	{
		dormRepo.deleteById(id);
	}
	
	
	
	public void assignStudent(Long dormId, Student student)
	{
		Dorm dorm = findOneDorm(dormId);
		student.setDorm(dorm);
		dorm.getStudents().add(student);
		dormRepo.save(dorm);
	}
	
	
	public void removeStudent(Long dormId, Student student)
	{
		Dorm dorm = findOneDorm(dormId);
		student.setDorm(null);
		dorm.getStudents().remove(student);
		dormRepo.save(dorm);
	}
	
	
	
}
