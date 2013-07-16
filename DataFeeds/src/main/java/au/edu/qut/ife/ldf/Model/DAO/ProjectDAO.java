package au.edu.qut.ife.ldf.Model.DAO;

import java.util.List;
import au.edu.qut.ife.ldf.Model.Project;

public interface ProjectDAO {
	
	public void addProject(Project project);
	public List<Project> listProject();
	public int removeProject(Integer id);
	public Project showProject(Integer idProject);
	public void updateProject(Project project);

}
