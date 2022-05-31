package com.smart.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.entity.Contact;
import com.smart.entity.User;
import com.smart.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class userController {
	
	@Autowired
	private UserRepository userRepository;
	
	// method for adding common data to response
	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		System.out.println("USERNAME : "+principal.getName());
		
		//get the user using username(Email)
		User user = userRepository.getUserByUserName(principal.getName());
		
		System.out.println(user);
		
		model.addAttribute("user", user);
	};
	
	//dashboard Home
	@RequestMapping("/index")
	public String dashboard(Model model, Principal principal) {
		User user = userRepository.getUserByUserName(principal.getName());
		
		model.addAttribute("title", user.getFirstName() + " " + user.getLastName());
		
		return "normal/user_dashboard";
	}
	
	//open add form handler
	@GetMapping("/add-contact")
	public String openAddContactForm(Model model) {
		model.addAttribute("title", "add contact");
		model.addAttribute("contact", new Contact());
		
		return "normal/add_contact_form";
	}
	
	//processing add contact form
//	@PostMapping("/process-contact")
//	public String processContact(@ModelAttribute Contact contact,
//			Principal principal) {
//		String name = principal.getName();
//		User user = this.userRepository.getUserByUserName(name);
//		
//		contact.setUser(user);
//		
//		user.getContacts().add(contact);
//		
//		this.userRepository.save(user);
//		
//		System.out.println("Data "+ contact);
//		System.out.println("Data to data base");
//		
//		
//		return "normal/add_contact_form";
//	}
	
}
