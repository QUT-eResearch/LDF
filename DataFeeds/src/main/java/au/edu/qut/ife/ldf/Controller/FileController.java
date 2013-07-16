package au.edu.qut.ife.ldf.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.org.apache.bcel.internal.generic.NEW;

import sun.util.calendar.BaseCalendar.Date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;


import au.edu.qut.ife.ldf.Model.Chart;
import au.edu.qut.ife.ldf.Model.Cr800;

@Controller
@RequestMapping("/loadFile")
public class FileController {

	private CSVReader reader;
	
	@Value("${file.location}")
	private String fileLocation;
	
	@Value("${file.date.index.one}")
	private String dateIndex1;
	
	@Value("${file.date.index.two}")
	private String dateIndex2;
	
	@Value("${file.date.format}")
	private String dateFormat;
	
	@Value("${file.start}")
	private String start;
	
	@RequestMapping(value = "/convert", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Chart csv2Chart(@RequestParam(value="field", required=false) int field,
				@RequestParam(value="fieldName", required=false) String name,
				@RequestParam(value="shortName", required=false) String shortName,
				Model model) throws FileNotFoundException{
		reader = new CSVReader(new FileReader(fileLocation));
		
	    List<String[]> array;
	    
	    //set properties for start index row
	    int d1 = Integer.parseInt(dateIndex1);
	    //int d2 = Integer.parseInt(dateIndex2);
	    int st = Integer.parseInt(start);
	    
	    Chart chart = new Chart();
	    try {
	    	array = reader.readAll();
	    	int numbElementsCSV = array.size();
			String[] minValue = array.get(st);
			chart.setxMin(dateTimeToUTC(minValue[d1]));
			String[] maxValue = array.get(numbElementsCSV-1);
			chart.setxMax(dateTimeToUTC(maxValue[d1]));
			
			chart.setName(name);
			chart.setShortName(shortName);
			
			StringBuilder tempData = new StringBuilder();
			tempData.append("[");
			String delimiter = "";
	    	for(int i=st;i<numbElementsCSV;i++){
	    		String[] nextLine;
	    		nextLine = array.get(i);
				//System.out.println(nextLine[0]+" "+ nextLine[1]);
				
	    		Long utc = null;
				try {
					utc = dateTimeToUTC(nextLine[d1]);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String time = String.valueOf(utc);
				chart.setValue(utc.toString());
				String value = "";

				value = nextLine[field];

				if(value.equals("NAN")){
					tempData.append(delimiter).append("[" + String.valueOf(utc) + ", null]");
				}
				else{
					tempData.append(delimiter).append("[" + String.valueOf(utc) + ", "+ value +"]");
				}
				delimiter = ",";
				//System.out.println(value);
	    		}
	    	tempData.append("]");
			chart.setValue(tempData.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	    
	    return chart;
 }

	@RequestMapping(value = "/bean", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Chart mapJavaBeanExample(@RequestParam(value="field", required=false) String field,
			@RequestParam(value="fieldName", required=false) String fieldName,
			Model model) throws FileNotFoundException{
		System.out.println("\n**** File Parsed ****");

		ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
		strat.setType(Cr800.class);
		String[] columns = new String[] { "Date","Time","Record","Temperature","Level","Velocity1","Velocity2","Velocity_X","Flow"};
		strat.setColumnMapping(columns);

		CsvToBean csv = new CsvToBean();

		String csvFilename = fileLocation;
		System.out.println(fileLocation);
		
		CSVReader csvReader = new CSVReader(new FileReader(csvFilename));

		List list = csv.parse(strat, csvReader);
		
		StringBuilder tempData = new StringBuilder();
		tempData.append("[");
		String delimiter = "";
		Chart chart = new Chart();
		
		
		for (Object object : list) {
			Cr800 data = (Cr800) object;
			
			Long utc = dateTimeToUTC(data.getDate()+" "+ data.getTime());
			if(utc == null){
				continue;
			}
			String time = String.valueOf(utc);
			String value = "";
			if(field.equals("Temperature")){
				value = data.getTemperature();
			}
			if(field.equals("Level")){
				value = data.getLevel();
			}
			if(field.equals("Velocity1")){
				value = data.getVelocity1();
			}
			if(field.equals("Velocity2")){
				value = data.getVelocity1();
			}
			if(field.equals("Velocity_X")){
				value = data.getVelocity_X();
			}
			if(field.equals("Flow")){
				value = data.getFlow();
			}
			
			
			if(value.equals("NAN")){
				tempData.append(delimiter).append("[" + time+ ", null]");
			}
			else{
				tempData.append(delimiter).append("[" + time+ ", "+ value +"]");
			}
			delimiter = ",";
			
			
		}
		tempData.append("]");
		chart.setValue(tempData.toString());
		

		Cr800 minValue = (Cr800) list.get(1);
		chart.setxMin(dateTimeToUTC(minValue.getDate()+" "+ minValue.getTime()));
		Cr800 maxValue = (Cr800) list.get(list.size() - 1);
		chart.setxMax(dateTimeToUTC(maxValue.getDate()+" "+ maxValue.getTime()));
		
		chart.setName(fieldName);
		
		
		return chart;
	}

	private Long dateTimeToUTC(String text){
		//Sample Format: dd/MM/yy hh:mm:ss a
		SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.US);
		
		format.setTimeZone(TimeZone.getTimeZone("UTC"));

		Long millis = null;
		try {
			java.util.Date date;
			//System.out.println(text);
			date = format.parse(text);
			millis = date.getTime();
			return millis;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(text);
			e.printStackTrace();
			return millis;
		}
		
	}
	
}