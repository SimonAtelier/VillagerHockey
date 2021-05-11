package usecases.encaps.addteamspawn;

import java.util.UUID;

import usecases.encaps.addteamspawn.AddTeamSpawn.AddTeamSpawnRequest;

public class AddTeamSpawnRequestModel implements AddTeamSpawnRequest {

	private double x;
	private double y;
	private double z;
	private double pitch;
	private double yaw;
	private String world;
	private String game;
	private String team;
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
	
	public String getGame() {
		return game;
	}
	
	public void setGame(String game) {
		this.game = game;
	}
	
	public String getTeam() {
		return team;
	}
	
	public void setTeam(String team) {
		this.team = team;
	}

	public UUID getPlayer() {
		return player;
	}

	public void setPlayer(UUID player) {
		this.player = player;
	}
	
}
