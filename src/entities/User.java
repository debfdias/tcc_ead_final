package br.com.saber.tcc.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.ejb.EJB;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name="users")
public class User extends BaseEntity implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String lastname;

	private String email;

	private byte[] password;

	private String token;

	private Profile profile;
	
	private String cpf;
	
	private String phoneNumber;
	 
	private String dType;
	
	public User(City city) {
		this.city = city;
	}
	
	public User() {	
	}
	
	@EJB
	private City city;
	
	private Collection <Log> logs;
	
	private boolean hasChangedDefaultPassword;

	@Size(min = 1, max = 100, message = "Por favor digite o nome, com no máximo 100 caracteres")
	@Column(name = "name", length = 100, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Size(min = 1, max = 100, message = "Por favor digite o sobrenome, com no máximo 100 caracteres")
	@Column(name = "lastname", length = 100, nullable = false)
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Size(min = 1, max = 100, message = "Por favor digite o e-mail, com no máximo 100 caracteres")
	@Column(name = "email", length = 100, nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", nullable = true)
	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public void updatePassword(byte[] password) {
		this.password = password;
	}

	@Column(name = "token", length = 100, nullable = true)
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@NotNull(message = "Por favor, selecione o perfil")
	@ManyToOne
	@JoinColumn(name = "profile_id", nullable = false, updatable = true)
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	@Column(length = 11)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Size(min = 0, max = 100, message = "Por favor digite o telefone, com no máximo 100 caracteres")
	@Column(length = 100, nullable = true)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@ManyToOne
	@JoinColumn(name = "city_id", updatable = true)
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	@OneToMany(mappedBy = "user")
	public Collection <Log> getLogs() {
		return logs;
	}

	public void setLogs(Collection <Log> logs) {
		this.logs = logs;
	}
	
	@Column
	public boolean isHasChangedDefaultPassword() {
		return hasChangedDefaultPassword;
	}

	public void setHasChangedDefaultPassword(boolean hasChangedDefaultPassword) {
		this.hasChangedDefaultPassword = hasChangedDefaultPassword;
	}

	public String buildName() {
		return getName() + " " + getLastname();
	}

	@Override
	public User clone() {
		try {
			return (User) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.getMessage());
		}
	}

	/**
	 * @return the dType
	 */
	public String getdType() {
		return dType;
	}

	/**
	 * @param dType the dType to set
	 */
	public void setdType(String dType) {
		this.dType = dType;
	}
    

}