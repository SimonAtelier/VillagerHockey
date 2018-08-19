package entities;

public class Location {

	private double x;
	private double y;
	private double z;
	private double pitch;
	private double yaw;
	private String world;
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getZ() {
		return z;
	}
	
	public void setZ(double z) {
		this.z = z;
	}
	
	public double getPitch() {
		return pitch;
	}
	
	public void setPitch(double pitch) {
		this.pitch = pitch;
	}
	
	public double getYaw() {
		return yaw;
	}
	
	public void setYaw(double yaw) {
		this.yaw = yaw;
	}
	
	public String getWorld() {
		return world;
	}
	
	public void setWorld(String world) {
		this.world = world;
	}
	
	public double distanceSquared(double x, double y, double z) {
		double dx = this.x - x;
		double dy = this.y - y;
		double dz = this.z - z;
		return (dx * dx) + (dy * dy) + (dz * dz);
	}

	public double distance(double x, double y, double z) {
		return Math.sqrt(distanceSquared(x, y, z));
	}
	
}
