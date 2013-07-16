package au.edu.qut.ife.ldf.Controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import au.edu.qut.ife.ldf.Service.ArithmeticService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles and retrieves the main requests
 */
@Controller
@RequestMapping("/ajax")
public class AjaxController {

	protected static Logger logger = Logger.getLogger("controller");
	
	@Resource(name="springService")
	private ArithmeticService springService;
	
	/**
	 * Handles and retrieves the AJAX Add page
	 */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAjaxAddPage() {
    	logger.debug("Received request to show AJAX, add page");
    	
    	// This will resolve to /WEB-INF/jsp/ajax-add-page.jsp
    	return "ajax-add-page";
	}
 
    /**
     * Handles request for adding two numbers
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody Integer add(@RequestParam(value="inputNumber1", required=true) Integer inputNumber1,
    							@RequestParam(value="inputNumber2", required=true) Integer inputNumber2,
    							Model model) {
		logger.debug("Received request to add two numbers");
		
		// Delegate to service to do the actual adding
		Integer sum = springService.add(inputNumber1, inputNumber2);
		
		// @ResponseBody will automatically convert the returned value into JSON format
		// You must have Jackson in your classpath
		return sum;
	}
}
