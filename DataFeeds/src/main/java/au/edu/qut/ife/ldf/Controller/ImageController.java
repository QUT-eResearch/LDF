package au.edu.qut.ife.ldf.Controller;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import au.edu.qut.ife.ldf.Model.Chart;
import au.edu.qut.ife.ldf.Model.Feed;
import au.edu.qut.ife.ldf.Model.Location;
import au.edu.qut.ife.ldf.Service.ChartService;
import au.edu.qut.ife.ldf.Service.FeedService;
import au.edu.qut.ife.ldf.Service.LocationService;


@Controller
@RequestMapping("/image")
public class ImageController {


	protected static Logger logger = Logger.getLogger("controller");

	@Autowired
	private LocationService locationService;
	@Autowired
	private FeedService feedService;
	@Autowired
	private ChartService chartService;
	
	@RequestMapping(value = "/getImage/{idproject}/{idfeed}/{idChart}", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String loadImage( 
			@PathVariable("idproject") Integer idProject,	
			@PathVariable("idfeed") Integer idFeed,
			@PathVariable("idChart") Integer idChart,
			Model model) throws FileNotFoundException{
		
		String fileLocation = null;
		Feed feed = new Feed();
		feed = feedService.showFeed(idProject, idFeed);
		
		Location location = new Location();

		location = locationService.showLocation(feed.getIdLocation());
		Chart chart = new Chart();
	    chart = chartService.showChart(idFeed, idChart);
		
	    fileLocation = location.getBaseUri() + chart.getUriName();
	    //File isFile = new File(location.getBaseUri() + chart.getUriName());
	    
	    
	   return fileLocation;
	    
	}
}
