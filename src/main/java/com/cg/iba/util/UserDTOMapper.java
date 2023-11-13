package com.cg.iba.util;

import org.springframework.stereotype.Component;

import com.cg.iba.dto.UserRequestSubmitDTO;
import com.cg.iba.dto.UserResponseDTO;
import com.cg.iba.entity.User;

@Component
public class UserDTOMapper {

	public User setUserUsingDTO(UserRequestSubmitDTO dto)
	{
		User u = new User();
		
		u.setPassword(dto.getPassword());
		u.setRole(dto.getRole());
		
		return u;
	}
	
	public UserResponseDTO getUserUsingDTO(User u) {

		UserResponseDTO dto = new UserResponseDTO();

		dto.setUserId(u.getUserId());
		dto.setPassword(u.getPassword());
		dto.setRole(u.getRole());

		return dto;
	}
}
