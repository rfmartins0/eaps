package br.com.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "lab_result", uniqueConstraints = @UniqueConstraint(columnNames = { "hash_adn" }))
public class LabResultEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_lab_result")
	private Long id;
	
	@Column(name = "adn")
	private String adn;
	
	@Column(name = "eap")
	private Boolean eap;
	
	@Column(name = "hash_adn")
	private String hashAdn;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdn() {
		return adn;
	}

	public void setAdn(String adn) {
		this.adn = adn;
	}

	public Boolean getEap() {
		return eap;
	}

	public void setEap(Boolean eap) {
		this.eap = eap;
	}

	public String getHashAdn() {
		return hashAdn;
	}

	public void setHashAdn(String hashAdn) {
		this.hashAdn = hashAdn;
	}


	
	

}
