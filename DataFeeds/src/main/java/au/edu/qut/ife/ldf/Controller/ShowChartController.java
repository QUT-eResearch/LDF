package au.edu.qut.ife.ldf.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import au.com.bytecode.opencsv.CSVReader;
import au.edu.qut.ife.ldf.Model.Chart;
import au.edu.qut.ife.ldf.Model.Feed;
import au.edu.qut.ife.ldf.Model.Location;
import au.edu.qut.ife.ldf.Service.ChartService;
import au.edu.qut.ife.ldf.Service.FeedService;
import au.edu.qut.ife.ldf.Service.LocationService;
import au.edu.qut.ife.ldf.Util.ChartUtil;


@Controller
@RequestMapping("/charts")
public class ShowChartController {

	protected static Logger logger = Logger.getLogger("controller");

	@Autowired
	private LocationService locationService;
	@Autowired
	private FeedService feedService;
	@Autowired
	private ChartService chartService;
	
	private CSVReader reader;
	
	@Value("${webapp.base}")
	private String baseDir;
	@Value("${server.name}")
	private String serverName;
	@Value("${server.port}")
	private String serverPort;
	
	@Autowired
	ServletContext context;
	
	@RequestMapping(value = "/draw", method = RequestMethod.GET)
    public String getAjaxAddPage() {
    	logger.debug("Received request to show AJAX, add page");

    	return "cr800";
	}
	
	@RequestMapping(value = "/csvChart", method = RequestMethod.GET)
    public String getAjaxAddPageCsvChart() {
    	logger.debug("Received request to show AJAX, add page");
    	
    	// This will resolve to /WEB-INF/jsp/ajax-add-page.jsp
    	return "feed";
	}
	


	@RequestMapping(value = "/convert/{idproject}/{idfeed}/{idChart}", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Chart csv2Chart(
		   @PathVariable("idproject") Integer idProject,	
		   @PathVariable("idfeed") Integer idFeed,
		   @PathVariable("idChart") Integer idChart,
		   Model model) throws IOException{
		
		
		Feed feed = new Feed();
		feed = feedService.showFeed(idProject, idFeed);
		
		Location location = new Location();
	   
		location = locationService.showLocation(feed.getIdLocation());
		Chart chart = new Chart();
	    chart = chartService.showChart(idFeed, idChart);
		
	    String fileName = context.getContextPath()+location.getBaseUri() +chart.getUriName();
		String filePath = "http://"+ serverName +":"+ serverPort+ fileName;
		
		URL url = null;
		try {
			url = new URL(filePath);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(url);

		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		 
	    reader = new CSVReader(in);
		
	    List<String[]> array;
	    ChartUtil cu = new ChartUtil();
	    
	    //set properties for start index row
	    int dateColumn = feed.getDateColumn();
	    int timeColumn = feed.getTimeColumn();
	    //int d2 = Integer.parseInt(dateIndex2);
	    int st = feed.getStartRow();
	    
	    //System.out.println("dateColumn:"+d1 +" startRow"+st);
	    
	    try {
	    	array = reader.readAll();
	    	int numbElementsCSV = chart.getNumValues();
	    	
	    	if (numbElementsCSV > 120){numbElementsCSV = 120;}
			String[] minValue = array.get(st);
			System.out.println(minValue[dateColumn]+" "+minValue[timeColumn]+" - "+ feed.getDateFormat()+" "+feed.getTimeFormat());

			chart.setxMin(cu.dateTimeToUTC(minValue[dateColumn]+" "+minValue[timeColumn], feed.getDateFormat()+" "+feed.getTimeFormat()));
			String[] maxValue = array.get(numbElementsCSV-1);
			chart.setxMax(cu.dateTimeToUTC(maxValue[dateColumn]+" "+maxValue[timeColumn], feed.getDateFormat()+" "+feed.getTimeFormat()));
			System.out.println(chart.getxMax());
			System.out.println(chart.getxMin());
			
			StringBuilder tempData = new StringBuilder();
			tempData.append("[");
			String delimiter = "";
	    	for(int i=st;i<numbElementsCSV;i++){
	    		String[] nextLine;
	    		nextLine = array.get(i);
				//System.out.println(nextLine[0]+" "+ nextLine[1]);
				
	    		Long utc = null;
				try {
					utc = cu.dateTimeToUTC(nextLine[dateColumn] +" " +nextLine[timeColumn], feed.getDateFormat()+" "+feed.getTimeFormat());
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String time = String.valueOf(utc);
				chart.setValue(utc.toString());
				String value = "";

				value = nextLine[chart.getFeedColumn()];

				if(value.equals("NA") || value.equals("NAN")){
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

}
