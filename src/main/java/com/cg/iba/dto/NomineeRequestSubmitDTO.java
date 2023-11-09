package com.cg.iba.dto;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.stereotype.Component;

import com.cg.iba.entity.Relation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NomineeRequestSubmitDTO {

    private String name;
    private String govtId; 
    private String govtIdType; 
    private String phoneNo;
    
	@Enumerated(EnumType.STRING)
	@Column(name="relation")
    private Relation relation; 
}
