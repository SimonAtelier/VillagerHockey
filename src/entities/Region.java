package entities;

public class Region {

	private Location locationOne;
	private Location locationTwo;

	public boolean contains(Location location) {
		if (!location.getWorld().equals(locationOne.getWorld()))
			return false;
		if (!location.getWorld().equals(locationTwo.getWorld()))
			return false;
		double x = location.getX();
		double y = location.getY();
		double z = location.getZ();
		return x >= getMinX() && x <= getMaxX() && y >= getMinY() && y <= getMaxY() && z >= getMinZ() && z <= getMaxZ();
	}

	public boolean containsIgnoreBorder(Location location) {
		if (!location.getWorld().equals(locationOne.getWorld()))
			return false;
		if (!location.getWorld().equals(locationTwo.getWorld()))
			return false;
		double x = location.getX();
		double y = location.getY();
		double z = location.getZ();
		return x > getMinX() && x < getMaxX() && y > getMinY() && y < getMaxY() && z > getMinZ() && z < getMaxZ();
	}

	public boolean isValid() {
		if (locationOne == null)
			return false;
		if (locationTwo == null)
			return false;
		if (locationOne.getWorld() == null)
			return false;
		if (locationTwo.getWorld() == null)
			return false;
		if (!locationOne.getWorld().equals(locationTwo.getWorld()))
			return false;
		return true;
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
