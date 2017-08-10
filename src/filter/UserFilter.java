package br.com.saber.tcc.filter;

import br.com.saber.tcc.entities.User;

/**
 * @author Victor Lira
 *
 */
public class UserFilter extends BaseFilter {

	private int id;
    private String email;
    private String token;
    private String cpf;

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
        	User usuario = (User) o;
            return usuario.getEmail().equals(getEmail()) && usuario.getId() == getId();
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        if (getEmail() == null) {
            return String.valueOf(getId()).hashCode();
        }
        else {
            return getEmail().hashCode() + String.valueOf(getId()).hashCode();
        }
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
