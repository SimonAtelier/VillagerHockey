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
	
}
