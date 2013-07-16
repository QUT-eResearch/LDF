package au.edu.qut.ife.ldf.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.edu.qut.ife.ldf.Model.Project;
import au.edu.qut.ife.ldf.Model.DAO.ProjectDAO;
import au.edu.qut.ife.ldf.Service.ProjectService;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	private ProjectDAO projectDAO;
	
	@Transactional
	public void addProject(Project project) {
		// TODO Auto-generated method stub
		projectDAO.addProject(project);
		
	}

	@Transactional
	public List<Project> listProject() {
		// TODO Auto-generated method stub
		return projectDAO.listProject();
	}

	@Transactional
	public int removeProject(Integer id) {
		// TODO Auto-generated method stub
		return projectDAO.removeProject(id);
		
	}
	
	@Transactional
	public Project showProject(Integer idProject) {
		// TODO Auto-generated method stub
		return projectDAO.showProject(idProject);
		
	}

	@Transactional
	public void updateProject(Project project) {
		// TODO Auto-generated method stub
		projectDAO.updateProject(project);
	}

}
