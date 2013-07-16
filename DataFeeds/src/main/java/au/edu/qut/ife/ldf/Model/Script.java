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
@Table(name="script", schema="ldf")
@SequenceGenerator(name = "ldf.hibernate_sequence", sequenceName = "ldf.hibernate_sequence", allocationSize=1)
public class Script implements Serializable{

	@Id
	@Column(name="idscript")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ldf.hibernate_sequence")
	private int idScript;
	
	@Column(name="scriptloc")
	private String scriptLoc;

	@Column(name="inputfile")
	private String inputFile;
	
	@Column(name="outputfile")
	private String outputFile;
	
	@Column(name="options")
	private String options;
	
	@Column(name="status")
	private Boolean status;

	public int getIdScript() {
		return idScript;
	}

	public void setIdScript(int idScript) {
		this.idScript = idScript;
	}

	public String getScriptLoc() {
		return scriptLoc;
	}

	public void setScriptLoc(String scriptLoc) {
		this.scriptLoc = scriptLoc;
	}

	public String getInputFile() {
		return inputFile;
	}

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public String getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
}
