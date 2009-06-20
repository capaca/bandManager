package com.bandManager.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;

@Entity
@SuppressWarnings("serial")
public class Usuario implements UserDetails, Serializable{

	private int id;
	private Perfil perfil;
	private String username;
	private String password;
	private boolean enabled;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	
	public Usuario(){
		init();
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario(String username, String password){
		this.username = username;
		this.password = password;
		this.perfil = new Perfil();
		init();
	}
	
	public Usuario(String username, String password, Perfil perfil){
		this.username = username;
		this.password = password;
		this.perfil = perfil;
		init();
	}
	
	private void init(){
		enabled = true;
		accountNonExpired = true;
		accountNonLocked = true;
		credentialsNonExpired = true;
	}
	
	@Column(unique=true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@OneToOne(cascade = CascadeType.ALL)
	public Perfil getPerfil() {
		return perfil;
	}
	
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}
	
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	@Transient
	public GrantedAuthority[] getAuthorities() {
		GrantedAuthority[] grantedAuthorities = new GrantedAuthorityImpl[1];
		grantedAuthorities[0] = new GrantedAuthorityImpl("ROLE_USUARIO");
		return grantedAuthorities;
	}

}
