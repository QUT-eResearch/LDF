package au.edu.qut.ife.ldf.Model.DAO;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import au.edu.qut.ife.ldf.Model.Location;

public interface LocationDAO {
	
	public void addLocation(Location location);
	public List<Location> listLocation();
	public int removeLocation(Integer id);
	public Location showLocation(Integer id);
	public void updateLocation(Location location);

}
