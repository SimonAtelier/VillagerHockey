package usecases.hockey.spawnvillager;

import usecases.hockey.spawnvillager.SpawnVillager.SpawnVillagerRequest;

public class SpawnVillagerRequestModel implements SpawnVillagerRequest {

	private boolean passenger;
	private String game;
	private String passengerName;

	public boolean isPassenger() {
		return passenger;
	}

	public void setPassenger(boolean passenger) {
		this.passenger = passenger;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

}
