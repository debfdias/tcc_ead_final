package tcc.com.saber.tcc.integration;

public class JsonMoodle {

	
	private String name;
	private String lastname;
	private String email;
	private String phone;
	
	private String cpf;
	private String dtype;
	private Boolean haschangedDefaultValue;
	private int City_id;
	private int Profile_id;
	private boolean selectedTo;
	
	public String getName() {
		return name;
	}
	public String getLastname() {
		return lastname;
	}
	public String getEmail() {
		return email;
	}
	public String getPhone() {
		return phone;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCpf() {
		return cpf;
	}
	public String getDtype() {
		return dtype;
	}
	public Boolean getHaschangedDefaultValue() {
		return haschangedDefaultValue;
	}
	public int getCity_id() {
		return City_id;
	}
	public int getProfile_id() {
		return Profile_id;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public void setDtype(String dtype) {
		this.dtype = dtype;
	}
	public void setHaschangedDefaultValue(Boolean haschangedDefaultValue) {
		this.haschangedDefaultValue = haschangedDefaultValue;
	}
	public void setCity_id(int city_id) {
		City_id = city_id;
	}
	public void setProfile_id(int profile_id) {
		Profile_id = profile_id;
	}
	public boolean isSelectedTo() {
		return selectedTo;
	}
	public void setSelectedTo(boolean selectedTo) {
		this.selectedTo = selectedTo;
	}
	
	
}
