package au.edu.qut.ife.ldf.Service;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import au.edu.qut.ife.ldf.Model.Script;
 
/**
 * Scheduler for handling jobs
 */
@Service
public class SchedulerService {
 
 protected static Logger logger = Logger.getLogger("service");
 
 @Autowired
 @Qualifier("syncWorker")
 private Worker worker;
 
 @Value("${script.active}")
 private String active;

 @Autowired
 private ScriptService scriptService;
  
 /**
  * You can opt for cron expression or fixedRate or fixedDelay
  * See Spring Framework 3 Reference:
  * Chapter 25.5 Annotation Support for Scheduling and Asynchronous Execution
  */
 //@Scheduled(fixedDelay=5000)
 //@Scheduled(fixedRate=5000)

 @Scheduled(cron="${script.cronTime}")
 public void doSchedule() {
  logger.debug("Start schedule");

  if(active.equals("true") || isAnyScript()){
	  System.out.println("Working ");
	  worker.work();
  }
   
  logger.debug("End schedule");
 }
 
 private boolean isAnyScript(){
	 
	 List<Script> scriptList = scriptService.listScript();        
     scriptList.iterator().next().getIdScript();
     Iterator<Script> scriptIterator = scriptList.iterator();
     while(scriptIterator.hasNext()) {
        Script element = scriptIterator.next();

        if(element.getStatus()){
     	   return true;
        }
     }
     return false;
 }
 
}