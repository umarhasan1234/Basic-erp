package com.nrt.responce;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponce {
	
	private String userToken;
	
	private String userRole;
	
	private Date userLogin;
	

}