package au.edu.qut.ife.ldf.Controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import au.edu.qut.ife.ldf.Model.Chart;
import au.edu.qut.ife.ldf.Model.Feed;
import au.edu.qut.ife.ldf.Model.Location;
import au.edu.qut.ife.ldf.Model.Project;
import au.edu.qut.ife.ldf.Service.ChartService;
import au.edu.qut.ife.ldf.Service.FeedService;
import au.edu.qut.ife.ldf.Service.LocationService;
import au.edu.qut.ife.ldf.Service.ProjectService;


@Controller
@RequestMapping("/admin/feeds")
public class FeedControler {
	
	protected static Logger logger = Logger.getLogger("controller");

	@Autowired
	private FeedService feedService;
	@Autowired
	private LocationService locationService;
	
	@RequestMapping(value = "/showFeeds", method = RequestMethod.GET)
	public String mapFeeds(Model model){

		return "showFeeds";
	}
	
	@RequestMapping(value = "/listFeeds", method = RequestMethod.GET)
	public String listFeeds(
			@RequestParam("idProject") Integer idProject, 
			Map<String, Object> map){
		logger.debug("listFeeds of "+ idProject);
		
		map.put("feed", new Feed());
		map.put("feedsList", feedService.listFeeds(idProject));
		map.put("location", new Location());
		map.put("locationsList", locationService.listLocation());

		map.put("idProject", idProject);
		map.put("chart", new Chart());
		return "feeds";
	}
	
	@RequestMapping(value = "/addFeed/{idProject}", method = RequestMethod.POST)
    public String addFeed(
    		@ModelAttribute("feed") Feed feed,
    		@PathVariable("idProject") Integer idProject,
    		BindingResult result) {
		
		if(result.hasErrors()){
			System.out.println("error");
			return "redirect:/app/feeds/listFeeds?idProject="+idProject;
		}
		
        feedService.addFeed(feed);
 
        return "redirect:/admin/feeds/listFeeds?idProject="+idProject;
    }
	
	@RequestMapping("/deleteFeed/{idProject}/{idFeed}")
    public String deleteContact(
    		@PathVariable("idProject") Integer idProject, 
    		@PathVariable("idFeed") Integer idFeed) {
		
		String result = "true";
		int i = feedService.removeFeed(idProject, idFeed);
		if(i<1){
			result = "false";
		}
        return "redirect:/admin/feeds/listFeeds?idProject="+idProject+"&success="+result;
    }
	
	@RequestMapping(value = "/editFeed/{idProject}/{idFeed}")
	public @ResponseBody Feed editFeed(
			@PathVariable("idProject") Integer idProject,
			@PathVariable("idFeed") Integer idFeed){

		Feed feed = feedService.showFeed(idProject, idFeed);     
		

		return feed;
	}
	@RequestMapping(value = "/updateFeed/{idProject}", method = RequestMethod.POST)
    public String updateFeed(
    		@PathVariable("idProject") Integer idProject,
    		@ModelAttribute("feed") Feed feed, 
    		BindingResult result) {
 
		System.out.println(feed.getIdFeed());
        feedService.updateFeed(feed);
 
        return "redirect:/admin/feeds/listFeeds?idProject="+idProject;
    }
}
