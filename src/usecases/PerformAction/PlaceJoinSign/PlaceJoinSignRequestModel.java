package usecases.PerformAction.PlaceJoinSign;

import java.util.UUID;

import usecases.PerformAction.PlaceJoinSign.PlaceJoinSign.PlaceJoinSignRequest;

public class PlaceJoinSignRequestModel implements PlaceJoinSignRequest {

	private double x;
	private double y;
	private double z;
	private UUID player;
	private String game;
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
	
	public UUID getPlayer() {
		return player;
	}
	
	public void setPlayer(UUID player) {
		this.player = player;
	}
	
	public String getGame() {
		return game;
	}
	
	public void setGame(String game) {
		this.game = game;
	}
	
	public String getWorld() {
		return world;
	}
	
	public void setWorld(String world) {
		this.world = world;
	}
	
}
