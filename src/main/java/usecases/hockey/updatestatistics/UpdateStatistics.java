package usecases.hockey.updatestatistics;

import gamestats.GameStatisticGateway;
import gateways.GameGateway;

public interface UpdateStatistics {

	void execute(UpdateStatisticsRequest request);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setGameStatisticGateway(GameStatisticGateway gameStatisticGateway);
	
	public interface UpdateStatisticsRequest {
		
		String getGame();
		
	}
	
}
