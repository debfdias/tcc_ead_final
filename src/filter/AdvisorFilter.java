package br.com.saber.tcc.filter;

public class AdvisorFilter extends BaseFilter {

	private int id;
	private String advisorEmail;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getAdvisorEmail() {
		return advisorEmail;
	}

	public void setAdvisorEmail(String advisorEmail) {
		this.advisorEmail = advisorEmail;
	}
}
