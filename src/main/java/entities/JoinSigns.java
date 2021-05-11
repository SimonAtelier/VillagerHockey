package entities;

import java.util.ArrayList;
import java.util.List;

public class JoinSigns {
	
	private final Object JOIN_SIGN_LIST_LOCK = new Object();
	
	private List<Location> locations;
	
	public JoinSigns() {
		locations = new ArrayList<Location>();
	}

	public void addLocation(Location location) {
		if (location == null)
			return;
		synchronized (JOIN_SIGN_LIST_LOCK) {
			locations.add(location);
		}
	}
	
	public void removeLocation(Location location) {
		List<Location> locationsToRemove = new ArrayList<Location>();
		synchronized (JOIN_SIGN_LIST_LOCK) {
			for (Location l : locations) {
				boolean remove = true;
				remove &= l.getX() == location.getX();
				remove &= l.getY() == location.getY();
				remove &= l.getZ() == location.getZ();
				remove &= l.getWorld().equals(location.getWorld());
				if (remove)
					locationsToRemove.add(l);
			}
			locations.removeAll(locationsToRemove);
		}
	}
		
	public List<Location> getLocations() {
		List<Location> joinSigns = new ArrayList<Location>();
		synchronized (JOIN_SIGN_LIST_LOCK) {
			for (Location joinSign : this.locations) {
				joinSigns.add(joinSign);
			}
		}
		return joinSigns;
	}
	
}
