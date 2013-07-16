package au.edu.qut.ife.ldf.Controller;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.servlet.ModelAndView;

import au.edu.qut.ife.ldf.Model.Feed;
import au.edu.qut.ife.ldf.Model.Folder;
import au.edu.qut.ife.ldf.Model.Location;
import au.edu.qut.ife.ldf.Model.Project;
import au.edu.qut.ife.ldf.Model.Script;
import au.edu.qut.ife.ldf.Service.FeedService;
import au.edu.qut.ife.ldf.Service.ProjectService;
import au.edu.qut.ife.ldf.Service.ScriptService;


@Controller
@RequestMapping("/admin")
public class AdminController {

protected static Logger logger = Logger.getLogger("controller");

	@Autowired
	private ProjectService projectService;
	@Autowired
	private ScriptService scriptService;

	@RequestMapping(value = {"/listFolders"}, method = { RequestMethod.GET, RequestMethod.POST })
    public @ResponseBody List listFolders(
    		Model model)  {

    	logger.debug("Received request to show AJAX, add page");

    	Folder folder = new Folder();
    	List <Folder> folders = new ArrayList<Folder>();
    	
    	folder.setFolderId(1);
    	folder.setLocation("/Users/Moy/dropbox/");
    	folder.setName("fnq");
    	
    	folders.add(folder);
    	Folder folder1 = new Folder();
    	folder1.setFolderId(2);
    	folder1.setLocation("/Users/Moy/dropbox/");
    	folder1.setName("SEQ");
    	folders.add(folder1);


    	return folders;
    }
	@RequestMapping(value = {"/listFolder"}, method = { RequestMethod.GET })
    public @ResponseBody List listFolder(@RequestParam("folderId") Integer folderId,
    		Model model)  {

    	logger.debug("list folder: "+ folderId);
    	System.out.println(folderId);
    	Folder folder = new Folder();
    	List <Folder> folders = new ArrayList<Folder>();
    	
    	folder.setFolderId(1);
    	folder.setLocation("/Users/Moy/dropbox/");
    	folder.setName("fnq");
    	
    	folders.add(folder);
    	Folder folder1 = new Folder();
    	folder1.setFolderId(2);
    	folder1.setLocation("/Users/Moy/dropbox/");
    	folder1.setName("SEQ");
    	folders.add(folder1);


    	return folders;
    }
	
	
	@RequestMapping(value = "/folders", method = RequestMethod.GET)
    public String showFolders(Model model)  {
    	logger.debug("showFolders");

    	return "admin";
    }
	
	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public String projects(Model model){
		logger.debug("listProjects");
		
		return "listProjects";
	}
	
	@RequestMapping(value = "/listProjects", method = RequestMethod.GET)
	public String listProjects(Map<String, Object> map){
		logger.debug("listProjects");
		
		map.put("project", new Project());
		map.put("projectList", projectService.listProject());

		return "projects";
	}
	
	@RequestMapping(value = "/addProject", method = RequestMethod.POST)
    public String addProject(@ModelAttribute("project")
    Project project, BindingResult result) {
 
        projectService.addProject(project);
 
        return "redirect:/admin/listProjects";
    }
	
	@RequestMapping("/deleteProject/{projectId}")
    public String deleteContact(@PathVariable("projectId")
    Integer projectId) {
 
        
        String result = "true";
		int i = projectService.removeProject(projectId);
		if(i<1){
			result = "false";
		}
		System.out.println(result);
        return "redirect:/admin/listProjects?success="+result;
    }
	
	@RequestMapping(value = "/editProject/{idProject}")
	public @ResponseBody Project editFeed(
			@PathVariable("idProject") Integer idProject){

		Project project = projectService.showProject(idProject);     
		

		return project;
	}
	
	@RequestMapping(value = "/updateProject", method = RequestMethod.POST)
    public String updateFeed(
    		@ModelAttribute("project") Project project, 
    		BindingResult result) {
 
		projectService.updateProject(project);
        return "redirect:/admin/listProjects";
    }
	
	@RequestMapping(value = "/listScripts", method = RequestMethod.GET)
	public String listScripts(Map<String, Object> map){
		logger.debug("listScripts");
		
		map.put("script", new Script());
		map.put("scriptList", scriptService.listScript());
		map.put("scriptU", new Script());
		
		return "scripts";
	}
	
	@RequestMapping(value = "/editScripts/{idScript}")
	public @ResponseBody Script editScripts(
			@PathVariable("idScript") Integer idScript){
		
		Script script = scriptService.showScript(idScript);
		
	return script;
	}
	
	@RequestMapping(value = "/updateScript", method = RequestMethod.POST)
    public String updateScript(
    		@ModelAttribute("scriptU") Script script, 
    		BindingResult result) {
 
		scriptService.updateScript(script);
        return "redirect:/admin/listScripts";
    }
	
	@RequestMapping("/deleteScript/{idScript}")
    public String deleteScript(@PathVariable("idScript")
    Integer idScript) {
 
        
        String result = "true";
		int i = scriptService.removeScript(idScript);
		if(i<1){
			result = "false";
		}
		System.out.println(result);
        return "redirect:/admin/listScripts?success="+result;
    }
	
	@RequestMapping(value = "/addScript", method = RequestMethod.POST)
    public String addScript(@ModelAttribute("script")
    	Script script, 
    	BindingResult result) {
 
        scriptService.addScript(script);
 
        return "redirect:/admin/listScripts";
    }	
    
}

