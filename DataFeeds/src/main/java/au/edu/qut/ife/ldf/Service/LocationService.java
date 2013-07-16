package au.edu.qut.ife.ldf.Service;

import java.util.List;
import au.edu.qut.ife.ldf.Model.Location;

public interface LocationService {
	
	
	public void addLocation(Location location);
    public List<Location> listLocation();
    public int removeLocation(Integer id);
    public Location showLocation(Integer id);

    public void updateLocation(Location location);
}
