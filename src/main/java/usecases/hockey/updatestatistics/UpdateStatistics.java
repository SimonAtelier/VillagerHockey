package usecases.hockey.updatestatistics;

import gamestats.GameStatisticGateway;
import gateways.GameGateway;
import gateways.StatisticsGateway;

public interface UpdateStatistics {

	void execute(UpdateStatisticsRequest request);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setStatisticsGateway(StatisticsGateway statisticsGateway);
	
	void setGameStatisticGateway(GameStatisticGateway gameStatisticGateway);
	
	public interface UpdateStatisticsRequest {
		
		String getGame();
		
	}
	
}
