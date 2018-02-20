package usecases.addteam;

import java.util.UUID;

import usecases.addteam.AddTeam.AddTeamRequest;

public class AddTeamRequestModel implements AddTeamRequest {
	
	private UUID player;
	private String game;
	private String name;
	private String color;
	
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
}
