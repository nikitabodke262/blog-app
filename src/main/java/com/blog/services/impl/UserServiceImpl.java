package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.payloads.UserDto;
import com.blog.repository.UserRepo;
import com.blog.services.UserService;
import com.blog.exceptions.*;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired	
	ModelMapper modelMapper;
	
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		
		return this.usertoDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {

		User user = this.userRepo.findById(userId).orElseThrow(() 
					-> new ResourceNotFoundException( "User", " id ", userId));
//		user.setId(userDto.getId());
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		
		User updatedUser = this.userRepo.save(user);
		UserDto userToDto = this.usertoDto(updatedUser);
		return userToDto;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()
							-> new ResourceNotFoundException( "User", " id ", userId));
		
		
		return this.usertoDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List <User> list = this.userRepo.findAll();
		List <UserDto> userDto = list.stream().map(l -> 
							this.usertoDto(l)).collect(Collectors.toList());
		return userDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(
				()-> new ResourceNotFoundException( "User", " id ", userId));
		this.userRepo.delete(user);
		
	}
	
	public User dtoToUser(UserDto userDto) {
		User user= this.modelMapper.map(userDto,User.class);
		return user;
	}

	public UserDto usertoDto(User user) {
		
		UserDto userDto = this.modelMapper.map(user,UserDto.class);
		return userDto;
	}
	
}

//public User dtoToUser(UserDto userDto) {
//	User user= new User();
//	user.setId(userDto.getId());
//	user.setAbout(userDto.getAbout());
//	user.setEmail(userDto.getEmail());
//	user.setName(userDto.getName());
//	user.setPassword(userDto.getPassword());
//	
//	return user;
//}
//
//public UserDto usertoDto(User user) {
//	
//	UserDto userDto = new UserDto();
//	userDto.setAbout(user.getAbout());
//	userDto.setEmail(user.getEmail());
//	userDto.setId(user.getId());
//	userDto.setName(user.getName());
//	userDto.setPassword(user.getPassword());
//return userDto;
//}
