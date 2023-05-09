package com.alexproject.studentroster.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alexproject.studentroster.models.Dorm;
import com.alexproject.studentroster.models.Student;
import com.alexproject.studentroster.services.DormService;
import com.alexproject.studentroster.services.StudentService;

@Controller
public class ViewController {

	@Autowired
	private DormService dormServ;
	
	@Autowired
	private StudentService studServ;
	
	@GetMapping("/")
	public String index()
	{
		return "redirect:/dorms";
	}
	
	@GetMapping("/dorms")
	public String index(Model mv)
	{
		List<Dorm> allDorms = dormServ.findAllDorm();
		
		mv.addAttribute("allDorms", allDorms);
		
		return "dormDashboard.jsp";
	}

	
	@GetMapping("/dorms/new")
	public String createDormPage(Model mv)
	{
		
		mv.addAttribute("newDorm", new Dorm());
		
		return "createDorm.jsp";
	}
	
	@PostMapping("/createDorm")
	public String createNewDorm(@Valid @ModelAttribute("newDorm") Dorm newDorm, BindingResult result, Model mv)
	{
		if(result.hasErrors())
		{
			return "createDorm.jsp";
		}
		else
		{
			dormServ.createDorm(newDorm);
			return "redirect:/dorms";
		}
	}
	
	@GetMapping("/students/new")
	public String createStudentPage(Model mv)
	{
		List<Dorm> allDorms = dormServ.findAllDorm();
		
		mv.addAttribute("newStudent", new Student());
		mv.addAttribute("allDorms", allDorms);
		
		return "createStudent.jsp";
	}
	
	@PostMapping("/createStudent")
	public String createNewStudent(@Valid @ModelAttribute("newStudent") Student newStudent, BindingResult result, Model mv)
	{
		if(result.hasErrors())
		{
			return "createStudent.jsp";
		}
		else
		{
			List<Dorm> allDorms = dormServ.findAllDorm();
			
			mv.addAttribute("newStudent", new Student());
			mv.addAttribute("allDorms", allDorms);
			studServ.createStudent(newStudent);
			return "redirect:/students/new";
		}
	}
	
	@GetMapping("/dorms/{id}")
	public String viewDorm(@PathVariable("id") Long dormId, Model mv)
	{
		Dorm dormShown = dormServ.findOneDorm(dormId);
		
		List<Dorm> allDorms = dormServ.findAllDorm();
		
		List<Student> allStudents = studServ.findAllStudent();
		List<Student> studentsInDorm = dormShown.getStudents();
		
		mv.addAttribute("dormShown", dormShown);
		mv.addAttribute("allDorms", allDorms);
		mv.addAttribute("allStudents", allStudents);
		mv.addAttribute("studentsInDorm", studentsInDorm);
		
		
		return "dormInfo.jsp";
	}
	
	
	@PutMapping("/dorms/{id}")
	public String reassignStudent(@Valid @ModelAttribute("dormShown") Dorm existingDorm, @RequestParam("students") Student student, BindingResult results, Model mv)
	{		
		dormServ.assignStudent(existingDorm.getId(), student);		
		return "redirect:/dorms/"+existingDorm.getId();
	}
	
	@GetMapping("/dorms/{id}/remove/{studentId}")
	public String removeStudent(@PathVariable("id") Long dormId, @PathVariable("studentId") Long studentId, Model mv)
	{
		System.out.println(studServ.findOneStudent(studentId).getName() + " removed at " + dormServ.findOneDorm(dormId).getName());
		dormServ.removeStudent(dormId, studServ.findOneStudent(studentId));
		return "redirect:/dorms/"+dormId;
	}
	
	
	
}
