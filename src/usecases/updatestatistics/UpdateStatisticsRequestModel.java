package usecases.updatestatistics;

import usecases.updatestatistics.UpdateStatistics.UpdateStatisticsRequest;

public class UpdateStatisticsRequestModel implements UpdateStatisticsRequest {
	
	private String game;

	@Override
	public String getGame() {
		return game;
	}
	
	public void setGame(String game) {
		this.game = game;
	}
	
}
