package au.edu.qut.ife.ldf.Controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVReader;
import au.edu.qut.ife.ldf.Model.Chart;
import au.edu.qut.ife.ldf.Model.Feed;
import au.edu.qut.ife.ldf.Model.Location;
import au.edu.qut.ife.ldf.Model.Project;
import au.edu.qut.ife.ldf.Service.ArithmeticService;
import au.edu.qut.ife.ldf.Service.ChartService;
import au.edu.qut.ife.ldf.Service.FeedService;
import au.edu.qut.ife.ldf.Service.LocationService;
import au.edu.qut.ife.ldf.Service.ProjectService;
import au.edu.qut.ife.ldf.Util.ChartUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles and retrieves the main requests
 */
@Controller
@RequestMapping("/admin/charts")
public class ChartController {

	protected static Logger logger = Logger.getLogger("controller");
	
	@Autowired
	private LocationService locationService;
	@Autowired
	private FeedService feedService;
	@Autowired
	private ChartService chartService;
	

	@RequestMapping(value = "/editChart/{idProject}/{idFeed}/{idChart}", method = { RequestMethod.GET, RequestMethod.POST })
	public String editChart(
			@PathVariable("idProject") Integer idProject,
			@PathVariable("idFeed") Integer idFeed, 
			@PathVariable("idChart") Integer idChart,
			@ModelAttribute Chart chart,
			Map<String, Object> map){
		
		map.put("chart", chartService.showChart(idFeed, idChart));

		return "editChart";
	}
	@RequestMapping(value = "/deleteChart/{idProject}/{idFeed}/{idChart}", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteChart(
			@PathVariable("idProject") Integer idProject,
			@PathVariable("idFeed") Integer idFeed, 
			@PathVariable("idChart") Integer idChart,
			@ModelAttribute Chart chart,
			Map<String, Object> map){
		
		chartService.removeChart(idFeed, idChart);

		return "redirect:/admin/feeds/listFeeds?idProject="+idProject;
	}
	
	@RequestMapping(value = "/viewChart/{idProject}/{idFeed}/{idChart}", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Chart viewChart(
			@PathVariable("idProject") Integer idProject,
			@PathVariable("idFeed") Integer idFeed, 
			@PathVariable("idChart") Integer idChart,
			@ModelAttribute(value="chartU") Chart chart,
			ModelMap model
			){
		
		chart = chartService.showChart(idFeed, idChart);
		
		System.out.println(chart.getIdChart());
		
		return chart;
	}
	@RequestMapping(value = "/updateChart/{idProject}", method = RequestMethod.POST)
    public String updateChart(
    		@PathVariable("idProject") Integer idProject,
    		@ModelAttribute("chart") Chart chart, 
    		BindingResult result) {
 
        chartService.updateChart(chart);
 
        return "redirect:/admin/feeds/listFeeds?idProject="+idProject;
    }
	
	@RequestMapping(value = "/addChart/{idProject}/{idFeed}", method = RequestMethod.POST)
    public String addChart(
    		@PathVariable("idProject") Integer idProject,
    		@PathVariable("idFeed") Integer idFeed, 
    		@ModelAttribute("chart") Chart chart, 
    		BindingResult result) {
 
        chartService.addChart(chart);
        
        return "redirect:/admin/feeds/listFeeds?idProject="+idProject;
    }
	
	@RequestMapping(value = "/listCharts/{idfeed}", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<Chart> listCharts( 
			@PathVariable("idfeed") Integer idFeed,
			Model model){
		
		logger.debug("listCharts");

		return chartService.listCharts(idFeed);
	}
	
}
