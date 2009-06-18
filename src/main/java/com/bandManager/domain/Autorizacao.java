package com.bandManager.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.GrantedAuthorityImpl;

@Entity
@SuppressWarnings("serial")
public class Autorizacao extends GrantedAuthorityImpl {

	private int id;
	private Role role;
	
	public Autorizacao(Role role){
		super(role.toString());
	}
	
	@Id
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
}
