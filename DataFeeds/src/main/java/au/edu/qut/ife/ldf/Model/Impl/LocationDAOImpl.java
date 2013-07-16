package au.edu.qut.ife.ldf.Model.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import au.edu.qut.ife.ldf.Model.Location;
import au.edu.qut.ife.ldf.Model.DAO.LocationDAO;

@Repository
public class LocationDAOImpl implements LocationDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addLocation(Location location) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(location);
	}

	public List<Location> listLocation() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Location").list();
	}

	public int removeLocation(Integer id) {
		// TODO Auto-generated method stub
		Location location = (Location) sessionFactory.getCurrentSession().load(Location.class, id);
		if(null != location){
			
			if(sessionFactory.getCurrentSession().createQuery("from Feed f where f.idLocation = "+id).list().isEmpty()){

				sessionFactory.getCurrentSession().delete(location);
				return 1;
			}
			
		}
		return 0;
	}

	public Location showLocation(Integer id) {
		// TODO Auto-generated method stub
		Location location = (Location) sessionFactory.getCurrentSession().createQuery("from Location where idLocation = "+id).uniqueResult();

		if(null != location){
			return location;
		}
		return null;
	}

	public void updateLocation(Location location) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(location);

	}


}
