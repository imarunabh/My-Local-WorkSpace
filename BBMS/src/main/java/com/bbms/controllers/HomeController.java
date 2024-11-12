package com.bbms.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bbms.dao.DonorRepository;
import com.bbms.entities.Donor;
import com.bbms.service.EmailService;


@Controller
public class HomeController {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private DonorRepository donorRepository;
	
	@GetMapping("/home")
	public String home() {
		
		return "home";
	}
	
	
	@GetMapping("/register_page")
	public String registerPage() {
		return "register";
	}
	
	
	@PostMapping("/register")
	public String register(@ModelAttribute Donor donor) {
		System.out.println(donor);
		this.donorRepository.save(donor);
		
		String subject="Registration Successfull";
		String message=""
				+ "<div style='border:1px solid #e2e2e2; padding:20px'>"
				+ "<h1>"
				+ "Registration successfull with email : "
				+ "<b>"
				+ donor.getEmail()
				+ "</b>"
				+ "A great thanks to you for blood donation"
				+ "</h1>"
				+ "</div>";
	 String to = donor.getEmail();
	 boolean flag = this.emailService.sendEmail(subject,message,to);
	 System.out.println(flag);
		
		return "redirect:/show";
	}
	
	@GetMapping("/show")
	public String showUsers(Model m) {
		List<Donor> donors=this.donorRepository.findAll();
		System.out.println(donors);
		m.addAttribute("donors",donors);
		
		return "show_donor";
	}
	
	@GetMapping("/delete/{Id}")
	public String deleteDonor(@PathVariable("Id") Integer Id){
		Donor donor = this.donorRepository.getById(Id);
		System.out.println(donor);
		this.donorRepository.delete(donor);
		return "redirect:/show";
		
	}
	
	@PostMapping("/update-donor/{Id}")
	public String updateDonor(@PathVariable("Id") Integer Id,Model m) {
		Donor donor= this.donorRepository.getById(Id);
		m.addAttribute("donor",donor);
		return "update_form";
		
	}
	@PostMapping("/process-update")
	public String processUpdate(@ModelAttribute Donor donor){
		System.out.println(donor);
		Donor donor1=this.donorRepository.getById(donor.getId());
		this.donorRepository.save(donor);
		//System.out.println(olddonor);
		
		//donor1.setAddress(olddonor.getAddress());
		//donor1.setAge(olddonor.getAge());
		//donor1.setEmail(olddonor.getEmail());
		//donor1.setName(olddonor.getName());
		//this.donorRepository.delete(olddonor);
		
		return "redirect:/show";
		
	}
	@RequestMapping("show_blood")
	public String showBlood(Model model) {
		int count1=this.donorRepository.countByBlood("A+");
		int count2=this.donorRepository.countByBlood("A-");
		int count3=this.donorRepository.countByBlood("B+");
		int count4=this.donorRepository.countByBlood("B-");
		int count5=this.donorRepository.countByBlood("AB+");
		int count6=this.donorRepository.countByBlood("AB-");
		int count7=this.donorRepository.countByBlood("O+");
		int count8=this.donorRepository.countByBlood("O-");
		System.out.println(count1);
		System.out.println(count2);
		List<Integer> list = new ArrayList<>();
		list.add(count1);
		list.add(count2);
		list.add(count3);
		list.add(count4);
		list.add(count5);
		list.add(count6);
		list.add(count7);
		list.add(count8);
		model.addAttribute("list",list);
		
		
		return "blood_details";
	}
	

	
	

}
