package usecases.PerformAction.BreakJoinSign;

import java.util.UUID;

import usecases.PerformAction.BreakJoinSign.BreakJoinSign.BreakJoinSignRequest;

public class BreakJoinSignRequestModel implements BreakJoinSignRequest {

	private double x;
	private double y;
	private double z;
	private String world;
	private String firstLine;
	private String secondLine;
	private String thirdLine;
	private String fourthLine;
	private UUID player;
	
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
	
	public String getWorld() {
		return world;
	}
	
	public void setWorld(String world) {
		this.world = world;
	}
	
	public String getFirstLine() {
		return firstLine;
	}
	
	public void setFirstLine(String firstLine) {
		this.firstLine = firstLine;
	}
	
	public String getSecondLine() {
		return secondLine;
	}
	
	public void setSecondLine(String secondLine) {
		this.secondLine = secondLine;
	}
	
	public String getThirdLine() {
		return thirdLine;
	}
	
	public void setThirdLine(String thirdLine) {
		this.thirdLine = thirdLine;
	}
	
	public String getFourthLine() {
		return fourthLine;
	}
	
	public void setFourthLine(String fourthLine) {
		this.fourthLine = fourthLine;
	}

	public UUID getPlayer() {
		return player;
	}

	public void setPlayer(UUID player) {
		this.player = player;
	}
	
}
