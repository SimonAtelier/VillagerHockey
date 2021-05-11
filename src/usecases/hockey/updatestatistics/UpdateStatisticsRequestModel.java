package usecases.hockey.updatestatistics;

import usecases.hockey.updatestatistics.UpdateStatistics.UpdateStatisticsRequest;

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
