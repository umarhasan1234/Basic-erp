package com.nrt.responce;
import java.sql.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponce {
	
	private String userToken;
	
	private Set userRole;
	
	private Date userLogin;
	

}