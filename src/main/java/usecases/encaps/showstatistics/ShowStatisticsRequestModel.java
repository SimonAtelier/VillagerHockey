package usecases.encaps.showstatistics;

import java.util.UUID;

import usecases.encaps.showstatistics.ShowStatistics.ShowStatisticsRequest;

public class ShowStatisticsRequestModel implements ShowStatisticsRequest {

	private UUID player;

	public UUID getPlayer() {
		return player;
	}

	public void setPlayer(UUID player) {
		this.player = player;
	}
	
}
