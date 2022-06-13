package com.blog.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.UserDto;
import com.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
//	create USER
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,(HttpStatus.CREATED));
		
	}
	
	
//	update USER
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Integer id)
	{
		UserDto updatedUser  = this.userService.updateUser(userDto, id);
		return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);
	}
	
	
//	delete User
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id)
	{
		this.userService.deleteUser( id);
//		return ResponseEntity.ok(Map.of("Message", "User Deleted successfully"));
		return new ResponseEntity<ApiResponse>(new ApiResponse
					("Deleted successfully", true), HttpStatus.OK);	
	}
	
	
//	Get users
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer id){
		return ResponseEntity.ok(this.userService.getUserById(id));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
