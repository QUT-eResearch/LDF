package au.edu.qut.ife.ldf.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import au.edu.qut.ife.ldf.Model.Script;
 
/**
 * A synchronous worker
 */
@Component("syncWorker")
public class SyncWorker implements Worker {
 
 protected static Logger logger = Logger.getLogger("service");
	@Autowired
	private ScriptService scriptService;
	
	@Value("${script.RExec}")
	private String RExec;
	
	public void execute(Integer idScript){

		Script script = new Script();
		script = scriptService.showScript(idScript);
		
		try {
			//String scriptToExecute = "#!"+RExec +" cat(\"Loading script...\n\") source(\""+ script.getScriptLoc() +"\"" +
			//				"pre_process_ver6(\""+script.getInputFile()+" , "+script.getOutputFile()+" \",\"" +
			//				" "+ script.getOptions() +" \") cat(\"\nScript finished\n\")";
			String scriptToExecute = RExec +" "+ script.getScriptLoc() +" " 
									+ script.getInputFile()+" "+script.getOutputFile() + " "
									+ script.getOptions();
			Process p = Runtime.getRuntime().exec(scriptToExecute);
	    	
	        System.out.println(scriptToExecute);
	        BufferedReader in = new BufferedReader(
	                            new InputStreamReader(p.getInputStream()));
	        System.out.println(p.getErrorStream());
	        String line = null;
	        while ((line = in.readLine()) != null) {
	            System.out.println(line);
	            
	        }
	    } catch (Exception e) {
	        e.printStackTrace();

	    }
	}
	
 
 public void work() {
  String threadName = Thread.currentThread().getName();
  logger.debug("   " + threadName + " has began working.");
        try {
         logger.debug("working...");
            Thread.sleep(100000); // simulates work
            List<Script> scriptList = scriptService.listScript();        
            scriptList.iterator().next().getIdScript();
            Iterator<Script> scriptIterator = scriptList.iterator();
            while(scriptIterator.hasNext()) {
               Script element = scriptIterator.next();

               if(element.getStatus()){
            	   System.out.print("Working in: " + element.getIdScript() + " ");
            	   execute(element.getIdScript());
            	   
               }
            }

        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        logger.debug("   " + threadName + " has completed work.");
 }
  
}