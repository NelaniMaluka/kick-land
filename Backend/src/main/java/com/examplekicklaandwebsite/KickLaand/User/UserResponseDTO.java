package com.examplekicklaandwebsite.KickLaand.User;

public class UserResponseDTO {
	private Integer id;
    private String username;
    private String email;
    private String surname;
    private String phoneNumber;
    private String address;
    
	public UserResponseDTO(Integer id, String username, String email, String surname, String phoneNumber,
			String address) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
    
    
}
