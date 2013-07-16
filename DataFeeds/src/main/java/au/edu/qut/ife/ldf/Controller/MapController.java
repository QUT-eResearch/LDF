package au.edu.qut.ife.ldf.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import au.edu.qut.ife.ldf.Model.Chart;
import au.edu.qut.ife.ldf.Model.Feed;
import au.edu.qut.ife.ldf.Model.Folder;
import au.edu.qut.ife.ldf.Model.Project;
import au.edu.qut.ife.ldf.Service.ChartService;
import au.edu.qut.ife.ldf.Service.FeedService;
import au.edu.qut.ife.ldf.Service.ProjectService;


@Controller
@RequestMapping("/map")
public class MapController {
	
	@Autowired
	private ProjectService projectService;
	@Autowired
	private FeedService feedService;
	@Autowired
	private ChartService chartService;
	
	protected static Logger logger = Logger.getLogger("controller");
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
    public String showFolders(Model model)  {
    	logger.debug("Received request to show AJAX, add page");

    	return "map";
    }
	
	@RequestMapping(value = "/asnHome", method = RequestMethod.GET)
    public String showASNHOME(Model model)  {
    	logger.debug("Received request to show AJAX, add page");

    	return "map";
    }
	
	
	@RequestMapping(value = {"/loadProject"}, method = { RequestMethod.GET, RequestMethod.POST })
    public @ResponseBody List<Feed> listFeedsfromProject(@RequestParam(value="idProject", required=true) Integer idProject,
    		Model model)  {
    	logger.debug("Received request to show AJAX, add page");
    	
    	//List <Feed> feeds = new ArrayList<Feed>();
    	
    	return feedService.listFeeds(idProject);
    }
	
	@RequestMapping(value = {"/loadFeeds/{idproject}/{idfeed}"}, method = { RequestMethod.GET, RequestMethod.POST })
    public @ResponseBody List<Chart> listFeedElementsfromProject(
    		@PathVariable("idproject") Integer idProject,	
 		   @PathVariable("idfeed") Integer idFeed,
    		Model model)  {
    	logger.debug("Load Feed Elements");
    	
    	//List <Feed> feeds = new ArrayList<Feed>();
    	
    	return chartService.listCharts(idFeed);
    }
	
	@RequestMapping(value = "/loadProjects", method = { RequestMethod.GET, RequestMethod.POST })
    public @ResponseBody List listFolders(Model model)  {
    	logger.debug("Received request to show AJAX, add page");

    	List <Project> projects = new ArrayList<Project>();

    	projects = projectService.listProject();
	    	
	    	/*Feed feed2 = new Feed();
	    	feed2.setName("Robson Creek");
	    	feed2.setFeedId(2);
	    	feed2.setMb((long) 145.627899);
	    	feed2.setNb((long) -17.103386);
	    	*/
	    		

    	return projects;
    }
	
}


