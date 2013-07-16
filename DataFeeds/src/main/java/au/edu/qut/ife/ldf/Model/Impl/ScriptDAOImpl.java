package au.edu.qut.ife.ldf.Model.Impl;

import java.util.List;

import au.edu.qut.ife.ldf.Model.Script;
import au.edu.qut.ife.ldf.Model.DAO.ScriptDAO;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScriptDAOImpl implements ScriptDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	public Script showScript(Integer idScript) {
		// TODO Auto-generated method stub
		Script script = (Script) sessionFactory.getCurrentSession().createQuery("from Script where idScript = "+idScript).uniqueResult();	
		
		if(null != script){
			return script;
		}
		return null;
	}

	public List<Script> listScript() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Script").list();
	}

	public void updateScript(Script script) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(script);
	}

	public int removeScript(Integer idScript) {
		// TODO Auto-generated method stub
		Script script = (Script) sessionFactory.getCurrentSession().load(Script.class, idScript);
		if(null != script){
				sessionFactory.getCurrentSession().delete(script);
			return 1;
		}
		return 0;
	}

	public void addScript(Script script) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(script);
	}
}
