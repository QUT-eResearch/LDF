package au.edu.qut.ife.ldf.Model.Impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import au.edu.qut.ife.ldf.Model.Location;
import au.edu.qut.ife.ldf.Model.Project;
import au.edu.qut.ife.ldf.Model.DAO.ProjectDAO;

@Repository
public class ProjectDAOImpl implements ProjectDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addProject(Project project) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(project);
	}

	public List<Project> listProject() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Project").list();
	}

	public int removeProject(Integer id) {
		// TODO Auto-generated method stub
		Project project = (Project) sessionFactory.getCurrentSession().load(Project.class, id);
		if(null != project){
			if(sessionFactory.getCurrentSession().createQuery("from Feed f where f.idProject = "+id).list().isEmpty()){
				sessionFactory.getCurrentSession().delete(project);
			return 1;
			}
		}
		return 0;
	}
	public void updateProject(Project project) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(project);

	}

	public Project showProject(Integer idProject) {
		// TODO Auto-generated method stub
		Project project = (Project) sessionFactory.getCurrentSession().createQuery("from Project where idProject = "+idProject).uniqueResult();

		if(null != project){
			return project;
		}
		return null;
	}

}
