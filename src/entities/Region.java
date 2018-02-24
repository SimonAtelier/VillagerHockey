package entities;

public class Region {

	private Location locationOne;
	private Location locationTwo;

	public boolean contains(Location location) {
		double x = location.getX();
		double y = location.getY();
		double z = location.getZ();
		return equalWorlds(location) && x >= getMinX() && x <= getMaxX() && y >= getMinY() && y <= getMaxY()
				&& z >= getMinZ() && z <= getMaxZ();
	}

	public boolean containsIgnoreBorder(Location location) {
		double x = location.getX();
		double y = location.getY();
		double z = location.getZ();
		return equalWorlds(location) && x > getMinX() && x < getMaxX() && y > getMinY() && y < getMaxY()
				&& z > getMinZ() && z < getMaxZ();
	}

	private boolean equalWorlds(Location location) {
		return location.getWorld().equals(locationOne.getWorld()) && location.getWorld().equals(locationTwo.getWorld());
	}

	public boolean isValid() {
		boolean valid = true;
		valid |= locationOne != null;
		valid |= locationTwo != null;
		valid |= locationOne.getWorld() != null;
		valid |= locationTwo.getWorld() != null;
		valid |= locationOne.getWorld().equals(locationTwo.getWorld());
		return valid;
	}

	@Override
	public String toString() {
		return "Region [locationOne=" + locationOne + ", locationTwo=" + locationTwo + "]";
	}

	public double getMinX() {
		return getXOne() < getXTwo() ? getXOne() : getXTwo();
	}

	public double getMaxX() {
		return getXOne() > getXTwo() ? getXOne() : getXTwo();
	}

	public double getMinY() {
		return getYOne() < getYTwo() ? getYOne() : getYTwo();
	}

	public double getMaxY() {
		return getYOne() > getYTwo() ? getYOne() : getYTwo();
	}

	public double getMinZ() {
		return getZOne() < getZTwo() ? getZOne() : getZTwo();
	}

	public double getMaxZ() {
		return getZOne() > getZTwo() ? getZOne() : getZTwo();
	}

	private double getXOne() {
		return locationOne.getX();
	}

	private double getXTwo() {
		return locationTwo.getX();
	}

	private double getYOne() {
		return locationOne.getY();
	}

	private double getYTwo() {
		return locationTwo.getY();
	}

	private double getZOne() {
		return locationOne.getZ();
	}

	private double getZTwo() {
		return locationTwo.getZ();
	}

	public Location getLocationOne() {
		return locationOne;
	}

	public void setLocationOne(Location locationOne) {
		this.locationOne = locationOne;
	}

	public Location getLocationTwo() {
		return locationTwo;
	}

	public void setLocationTwo(Location locationTwo) {
		this.locationTwo = locationTwo;
	}

}
