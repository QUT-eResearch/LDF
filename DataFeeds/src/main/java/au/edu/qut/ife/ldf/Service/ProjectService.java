package au.edu.qut.ife.ldf.Service;

import java.util.List;

import au.edu.qut.ife.ldf.Model.Location;
import au.edu.qut.ife.ldf.Model.Project;

public interface ProjectService {
	
	public void addProject(Project project);
    public List<Project> listProject();
    public int removeProject(Integer id);
    public Project showProject(Integer idProject);
    public void updateProject(Project project);
}
