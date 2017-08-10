package br.com.saber.tcc.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Profiles")
public class Profile extends BaseEntity implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 1L;

	private String name;
	
	private Collection<Role> roles;
	
	private UserFunction userFunction;
	
	private Collection<User> users;

	@Size(min = 1, max = 100, message = "Por favor digite o nome, com no máximo 100 caracteres")
	@Column(name = "name", length = 100, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	@OneToMany(mappedBy = "profile")
	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	@Enumerated(EnumType.STRING)
	public UserFunction getUserFunction() {
		return userFunction;
	}

	public void setUserFunction(UserFunction userFunction) {
		this.userFunction = userFunction;
	}

	@Override
	public Profile clone() {
		try {
			return (Profile) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.getMessage());
		}
	}
}