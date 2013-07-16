package au.edu.qut.ife.ldf.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.edu.qut.ife.ldf.Model.Location;
import au.edu.qut.ife.ldf.Model.DAO.LocationDAO;
import au.edu.qut.ife.ldf.Service.LocationService;

@Service
public class LocationServiceImpl implements LocationService{

	@Autowired
	private LocationDAO locationDAO;
	
	@Transactional
	public void addLocation(Location location) {
		// TODO Auto-generated method stub
		locationDAO.addLocation(location);
	}

	@Transactional
	public List<Location> listLocation() {
		// TODO Auto-generated method stub
		return locationDAO.listLocation();
	}

	@Transactional
	public int removeLocation(Integer id) {
		return locationDAO.removeLocation(id);
	}
	
	@Transactional
	public Location showLocation(Integer id) {
		// TODO Auto-generated method stub
		return locationDAO.showLocation(id);
	}

	@Transactional
	public void updateLocation(Location location) {
		// TODO Auto-generated method stub
		locationDAO.updateLocation(location);
	}



}
