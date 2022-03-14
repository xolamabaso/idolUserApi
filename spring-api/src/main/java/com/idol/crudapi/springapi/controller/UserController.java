package com.idol.crudapi.springapi.controller;
//
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//spring boot imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//package imports
import com.idol.crudapi.springapi.model.*;
import com.idol.crudapi.springapi.repository.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1")
public class UserController {

	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/users")//GET All User's
	public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName){
		
		try {
			List<User> users = new ArrayList<User>();
			
			if (firstName == null && lastName == null) {
				userRepo.findAll().forEach(users::add);
			}
			else if (firstName != null && lastName != null) {
				userRepo.findByFirstName(firstName).forEach(users::add);
			}
			else if (lastName != null && firstName != null) {
				userRepo.findByLastName(lastName).forEach(users::add);
			}
			return new ResponseEntity<>(users, HttpStatus.OK);	
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	}	
	
	@PostMapping("/users")//CREATE User
	public ResponseEntity<User> createUser(@RequestBody User user){
		try {
			User _user = userRepo
					.save(new User(user.getFirstName(),user.getLastName(), user.getContactNumber()));
			return new ResponseEntity<>(_user, HttpStatus.CREATED);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/users/{id}")//GET User by ID
	public ResponseEntity<User> getUserById (@PathVariable("id") long id){
		Optional<User> userData = userRepo.findById(id);
		
		if (userData.isPresent()) {
			return new ResponseEntity<>(userData.get(), HttpStatus.FOUND);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/users/{id}")//UPDATE User by ID
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user){
		Optional<User> userData = userRepo.findById(id);
		
		if (userData.isPresent()) {
			User _user = userData.get();
			_user.setFirstName(user.getFirstName());
			_user.setLastName(user.getLastName());
			_user.setContactNumber(user.getContactNumber());

			return new ResponseEntity<>(userRepo.save(_user), HttpStatus.FOUND);
		}
		else{
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}
	
	@DeleteMapping("/users")//DELETE All User's
	public ResponseEntity<HttpStatus> deleteAllUsers(){
		try {
			userRepo.deleteAll(); 
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id){
		try {
			userRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}
}
