package au.edu.qut.ife.ldf.Controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import au.edu.qut.ife.ldf.Model.Chart;
import au.edu.qut.ife.ldf.Model.Location;
import au.edu.qut.ife.ldf.Service.LocationService;

@Controller
@RequestMapping("/admin/locations")
public class LocationController {

	protected static Logger logger = Logger.getLogger("location");

	@Autowired
	private LocationService locationService;
	
	@RequestMapping(value = "/listLocations" , method = RequestMethod.GET)
	public String listLocation(
			Map<String, Object> map
			){
		
		logger.debug("listLocations");
		
		map.put("location", new Location());
		map.put("locationsList", locationService.listLocation());
		map.put("locationU", new Location());
		
		return "locations";
	}
	
	@RequestMapping(value = "/addLocation", method = RequestMethod.POST)
    public String addLocaton(@ModelAttribute("project")
    Location location, BindingResult result) {
 
        locationService.addLocation(location);
 
        return "redirect:/admin/locations/listLocations";
    }
	
	@RequestMapping("/deleteLocation/{idLocation}")
    public String deleteLocation(@PathVariable("idLocation")
    Integer idLocation) {
		String result = "true";
		int i = locationService.removeLocation(idLocation);
		if(i<1){
			result = "false";
		}
        return "redirect:/admin/locations/listLocations?success="+result;

    }
	
	@RequestMapping("/showLocation/{idLocation}")
    public @ResponseBody Location showLocation(
    		@PathVariable("idLocation") Integer idLocation) {
        
		Location location = new Location();
        location = locationService.showLocation(idLocation);
        
        return location;
    }
	
	@RequestMapping(value = "/editLocation/{idLocation}")
	public @ResponseBody Location editChart(
			@PathVariable("idLocation") Integer idLocation){


		Location locationU = locationService.showLocation(idLocation);        
		

		return locationU;
	}
	@RequestMapping(value = "/updateLocation", method = RequestMethod.POST)
    public String updateLocation(
    		@ModelAttribute("locationU") Location location, 
    		BindingResult result) {
 
        locationService.updateLocation(location);
 
        return "redirect:/admin/locations/listLocations";
    }
}
