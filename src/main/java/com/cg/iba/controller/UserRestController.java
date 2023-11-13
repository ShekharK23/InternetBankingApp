package com.cg.iba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.iba.dto.UserRequestSubmitDTO;
import com.cg.iba.dto.UserResponseDTO;
import com.cg.iba.entity.User;
import com.cg.iba.exception.DetailsNotFoundException;
import com.cg.iba.exception.InvalidDetailsException;
import com.cg.iba.service.IUserService;
import com.cg.iba.util.UserDTOMapper;

@RestController
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	IUserService userService;
	
	@Autowired
	UserDTOMapper userDTO;
	
	@PostMapping("/addnewuser")
	public ResponseEntity<UserResponseDTO> addNewUser(@RequestBody UserRequestSubmitDTO userdto) throws InvalidDetailsException {
		User u = userDTO.setUserUsingDTO(userdto);
		User user = userService.addNewUser(u);
		UserResponseDTO dto = userDTO.getUserUsingDTO(user);
		return new ResponseEntity<UserResponseDTO>(dto,HttpStatus.OK);	
	}
	
	@GetMapping("/userlogin")
	public ResponseEntity<UserResponseDTO> signIn(@RequestBody User user) throws InvalidDetailsException {
		//User user = userDTO.setUserUsingDTO(userdto);
		User u = userService.signIn(user);
		UserResponseDTO dto = userDTO.getUserUsingDTO(u);
		return new ResponseEntity<UserResponseDTO>(dto,HttpStatus.OK);		
	}
	
	@PutMapping("/updateuser")
	public ResponseEntity<UserResponseDTO> updateUserInfo(@RequestParam Long id,@RequestBody UserRequestSubmitDTO userdto) throws InvalidDetailsException {
		User user = userDTO.setUserUsingDTO(userdto);
		User u = userService.updateUserInfo(id, user);
		UserResponseDTO dto1 = userDTO.getUserUsingDTO(u);
		return new ResponseEntity<UserResponseDTO>(dto1, HttpStatus.FOUND);		
	}
	
	@DeleteMapping("/removeuser")
	public ResponseEntity<Boolean> removeUserInfo(@RequestParam long userId) throws DetailsNotFoundException {
		boolean status = userService.deleteUserInfo(userId);
		if (status) {
			return new ResponseEntity<Boolean>(status, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
		}
	}
	

}
