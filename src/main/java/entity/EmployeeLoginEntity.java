package entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EmployeeLoginEntity {
	
		@JsonProperty("email")
	    private String email;

	    @JsonProperty("password")
	    private String password;

	    @JsonProperty("full_name")
	    private String fullName;

	    @JsonProperty("department")
	    private String department;

	    @JsonProperty("title")
	    private String title;
	
}
