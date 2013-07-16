package au.edu.qut.ife.ldf.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="chart", schema="ldf")
@SequenceGenerator(name = "ldf.hibernate_sequence", sequenceName = "ldf.hibernate_sequence", allocationSize=1)
public class Chart implements Serializable{

	@Id
	@Column(name="idchart")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ldf.hibernate_sequence")
	private int idChart;
	
	@Column(name="idfeed")
	private int idFeed;
	
	@Column(name="type")
	private String type;
	
	@Column(name="name")
	private String name;
	
	@Column(name="uriName")
	private String uriName;
	
	@Column(name="shortName")
	private String shortName;
	
	@Column(name="xMinv")
	private long xMin;
	
	@Column(name="xMaxv")
	private long xMax;
	
	@Column(name="time")
	private String time;
	
	@Column(name="maxTime")
	private String maxTime;
	
	@Column(name="minTime")
	private String minTime;
	
	@Column(name="minXAxis")
	private int minXAxis;
	
	@Column(name="numValues")
	private int numValues;
	
	@Column(name="feedColumn")
	private int feedColumn;

	private String value;
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public int getIdChart() {
		return idChart;
	}
	public void setIdChart(int idChart) {
		this.idChart = idChart;
	}
	public int getIdFeed() {
		return idFeed;
	}
	public void setIdFeed(int idFeed) {
		this.idFeed = idFeed;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUriName() {
		return uriName;
	}
	public void setUriName(String uriName) {
		this.uriName = uriName;
	}
	public long getxMin() {
		return xMin;
	}
	public void setxMin(long xMin) {
		this.xMin = xMin;
	}
	public long getxMax() {
		return xMax;
	}
	public void setxMax(long xMax) {
		this.xMax = xMax;
	}
	public String getMaxTime() {
		return maxTime;
	}
	public void setMaxTime(String maxTime) {
		this.maxTime = maxTime;
	}
	public String getMinTime() {
		return minTime;
	}
	public void setMinTime(String minTime) {
		this.minTime = minTime;
	}
	public int getMinXAxis() {
		return minXAxis;
	}
	public void setMinXAxis(int minXAxis) {
		this.minXAxis = minXAxis;
	}
	public int getNumValues() {
		return numValues;
	}
	public void setNumValues(int numValues) {
		this.numValues = numValues;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getFeedColumn() {
		return feedColumn;
	}
	public void setFeedColumn(int feedColumn) {
		this.feedColumn = feedColumn;
	}


	
	
	
}
