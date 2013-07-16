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
@Table(name="project", schema="ldf")
@SequenceGenerator(name = "ldf.hibernate_sequence", sequenceName = "ldf.hibernate_sequence", allocationSize=1)
public class Project implements Serializable{
	
	@Id
	@Column(name="idproject")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ldf.hibernate_sequence")
	private int idProject;
	
	@Column(name="latitude")
	private double latitude;
	
	@Column(name="longitude")
	private double longitude;
	
	@Column(name="name")
	private String name;
	
	@Column(name="details")
	private String details;

	public int getIdProject() {
		return idProject;
	}

	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	
}
