package entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EmployeeResponseEntity {
	
		@JsonProperty("id")
	    private Integer id;

	    @JsonProperty("email")
	    private String email;

	    @JsonProperty("password_hash")
	    private String passwordHash;

	    @JsonProperty("full_name")
	    private String fullName;

	    @JsonProperty("department")
	    private String department;

	    @JsonProperty("title")
	    private String title;

	    @JsonProperty("create_at")
	    private String createAt;

	    @JsonProperty("update_at")
	    private String updateAt;

}
