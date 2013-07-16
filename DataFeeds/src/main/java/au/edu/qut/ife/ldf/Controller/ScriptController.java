package au.edu.qut.ife.ldf.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import au.edu.qut.ife.ldf.Model.Chart;
import au.edu.qut.ife.ldf.Model.Location;
import au.edu.qut.ife.ldf.Model.Script;
import au.edu.qut.ife.ldf.Service.ChartService;
import au.edu.qut.ife.ldf.Service.FeedService;
import au.edu.qut.ife.ldf.Service.LocationService;
import au.edu.qut.ife.ldf.Service.ScriptService;


@Controller
@RequestMapping("/map/script")
public class ScriptController {

	@Autowired
	private ChartService chartService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private FeedService feedService;
	@Autowired
	private ScriptService scriptService;
	
	@Value("${script.RExec}")
	private String RExec;
	
	@RequestMapping(value = "/execute2/{idScript}/{idInFile}/{idOutFile}", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody StringBuilder executeScript2(
			@PathVariable("idScript") Integer idScript,
			@PathVariable("idInFile") Integer idInFile,
			@PathVariable("idOutFile") Integer idOutFile
			){
			StringBuilder exitString = new StringBuilder();
			
			//String program = "/usr/bin/Rscript";
			Location script = new Location();
			Location inFile = new Location();
			Location outFile = new Location();
			
			script = locationService.showLocation(idScript);
			inFile = locationService.showLocation(idInFile);
			outFile = locationService.showLocation(idOutFile);
			
			String scriptSource = script.getBaseUri()+script.getUri();
			String inFiletSource = inFile.getBaseUri()+inFile.getUri();
			String outFileSource = outFile.getBaseUri()+outFile.getUri();
			
			String rScript = RExec+" "+scriptSource+" "+inFiletSource+" "+outFileSource;
			
		try {
			//Process p = Runtime.getRuntime().exec(rScript);
			Process p = Runtime.getRuntime().exec("/usr/bin/Rscript /Users/Moy/Desktop/pre_process_ver5.r /Users/Moy/java/ldf7/DataFeeds/src/main/webapp/WEB-INF/static/argonaut-real.csv /Users/Moy/java/ldf7/DataFeeds/src/main/webapp/WEB-INF/static/converted.csv Argon ");
	        BufferedReader in = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
			    System.out.println(line);
			    exitString.append(line);
			}
			//So p does not leak memory
			p.getInputStream().close();
			p.getOutputStream().close();
			p.getErrorStream().close();
			p.destroy();
			return exitString;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return exitString;
		}
		
	}
	@RequestMapping(value = "/execute/{idScript}", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Boolean executeScript(
			@PathVariable("idScript") Integer idScript,
			Model model){

		Script script = new Script();
		script = scriptService.showScript(idScript);
		
	    try {
	        Process p = Runtime.getRuntime().exec(RExec +" "+ script.getScriptLoc() +" "+script.getInputFile()+" "+script.getOutputFile()+" "+ script.getOptions() +"/");
	    	
	        //Process p = Runtime.getRuntime().exec("ls /");
	        BufferedReader in = new BufferedReader(
	                            new InputStreamReader(p.getInputStream()));
	        String line = null;
	        while ((line = in.readLine()) != null) {
	            System.out.println(line);
	            
	        }
	        return true;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	     
	}
	@RequestMapping(value = "/execute3/{scriptFile}/{inFile}/{outFile}/{options}", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String executeScript3(){
        String result = null;

		try {
			//Process p = Runtime.getRuntime().exec("/usr/bin/Rscript /Users/Moy/Desktop/pre_process_ver5.r /Users/Moy/java/ldf8/DataFeeds/src/main/webapp/WEB-INF/static/argonaut-real.csv /Users/Moy/java/ldf8/DataFeeds/src/main/webapp/WEB-INF/static/converted.csv Argon ");
			
	        Process p = Runtime.getRuntime().exec("ls /");
	        BufferedReader in = new BufferedReader(
	                            new InputStreamReader(p.getInputStream()));
	        
	        StringBuilder res = new StringBuilder();

	        String line = null;
	        while ((line = in.readLine()) != null) {
	        	
	        	res.append(line);
	        	res.append("\n");
	            System.out.println(line);
	        }
	        result = res.toString();
	        return result;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return result;
	    }
	     
	}
	
}
