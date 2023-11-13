package com.cg.iba.util;

import org.springframework.stereotype.Component;

import com.cg.iba.dto.NomineeRequestSubmitDTO;
import com.cg.iba.entity.Nominee;

@Component
public class NomineeDTOMapper {

	public Nominee setNomineeUsingDTO(NomineeRequestSubmitDTO dto)
	{
		Nominee n = new Nominee();
		
		n.setName(dto.getName());
		n.setGovtId(dto.getGovtId());
		n.setGovtIdType(dto.getGovtIdType());
		n.setPhoneNo(dto.getPhoneNo());
		n.setRelation(dto.getRelation());
		
		return n;
	}
}
