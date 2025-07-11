/**
 * 
 */
package com.example._60_dto.FormClass;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.lang.NonNull;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * 
 */
public class AccountCreateForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message="Required")
	@Size(min=1, max=50, message="Name is too long")
	@Pattern(regexp="[a-zA-Z0-9]*")
	private String name;
	
	@NonNull
	@Size(min=9, max=11)
	private String tel;
	
	@NonNull
	@DateTimeFormat(pattern="yyyy/mm/dd")
	private String dateOfSignup;

	@NonNull
	@Size(min=9, max=256)
	private String email;

}
