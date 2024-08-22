package com.OnlineShopping.cart24.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.OnlineShopping.cart24.dao.ContactDao;
import com.OnlineShopping.cart24.model.Contact;



@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:3000")
public class ContactController {

	
	@Autowired
	private ContactDao contactDao;
	
	
	@PostMapping("/contact")
	public void getstds(@RequestBody Contact con) {

		System.out.println(con);
		contactDao.save(con);

	}
	
	@PostMapping("contactedUser")
	public ResponseEntity<List<Contact>> getAllUsers() {
		
        System.out.println("request came for getting all Contacted users++");
		
		List<Contact> users = contactDao.findAll();
		
		ResponseEntity<List<Contact>> response = new ResponseEntity<>(users, HttpStatus.OK);
		
		System.out.println("response sent");
		
		return response;
		
	}
	
	@PostMapping("remove")
	public ResponseEntity<?> deleteUser(@RequestParam("id") int id) {
		System.out.println("recieved request for remove USER");
		System.out.println("ID===" + id);

		Optional<Contact> optionalUser = contactDao.findById(id);

		Contact user = new Contact();

		if (optionalUser.isPresent()) {
			user = optionalUser.get();
		}
         this.contactDao.delete(user);
		
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	}
	
	
	
	
}
