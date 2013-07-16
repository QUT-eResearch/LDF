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
@Table(name="feed", schema="ldf")
@SequenceGenerator(name = "ldf.hibernate_sequence", sequenceName = "ldf.hibernate_sequence", allocationSize=1)
public class Feed implements Serializable{
	
	@Id
	@Column(name="idfeed")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ldf.hibernate_sequence")
	private int idFeed;
	
	@Column(name="idproject")
	private int idProject;
	
	@Column(name="type")
	private String type;
	
	@Column(name="name")
	private String name;
	
	@Column(name="numColumns")
	private int numColumns;
	
	@Column(name="startRow")
	private int startRow;
	
	@Column(name="numRows")
	private int numRows;
	
	@Column(name="headerRows")
	private int headerRows;
	
	@Column(name="timeColumn")
	private int timeColumn;
	
	@Column(name="timeFormat")
	private String timeFormat;
	
	@Column(name="dateFormat")
	private String dateFormat;
	
	@Column(name="dateColumn")
	private int dateColumn;
	
	@Column(name="idlocation")
	private int idLocation;

	public int getIdFeed() {
		return idFeed;
	}

	public void setIdFeed(int idFeed) {
		this.idFeed = idFeed;
	}

	public int getIdProject() {
		return idProject;
	}

	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public void setNumColumns(int numColumns) {
		this.numColumns = numColumns;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getNumRows() {
		return numRows;
	}

	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}

	public int getHeaderRows() {
		return headerRows;
	}

	public void setHeaderRows(int headerRows) {
		this.headerRows = headerRows;
	}



	public int getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(int idLocation) {
		this.idLocation = idLocation;
	}

	public int getTimeColumn() {
		return timeColumn;
	}

	public void setTimeColumn(int timeColumn) {
		this.timeColumn = timeColumn;
	}

	public String getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public int getDateColumn() {
		return dateColumn;
	}

	public void setDateColumn(int dateColumn) {
		this.dateColumn = dateColumn;
	}


	
	
}
