package au.edu.qut.ife.ldf.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="location", schema="ldf")
@SequenceGenerator(name = "ldf.hibernate_sequence", sequenceName = "ldf.hibernate_sequence", allocationSize=1)
public class Location implements Serializable{
	
	@Id
	@Column(name="idlocation")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ldf.hibernate_sequence")
	private int idLocation;
	
	@Column(name="name")
	private String name;
	
	@Column(name="type")
	private String type;
	
	@Column(name="uri")
	private String uri;
	
	@Column(name="baseUri")
	private String baseUri;

	
	public int getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(int idLocation) {
		this.idLocation = idLocation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getBaseUri() {
		return baseUri;
	}

	public void setBaseUri(String baseUri) {
		this.baseUri = baseUri;
	}
	
	
	
}
