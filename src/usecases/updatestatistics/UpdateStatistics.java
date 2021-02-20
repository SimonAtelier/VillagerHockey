package usecases.updatestatistics;

import gateways.GameGateway;
import gateways.StatisticsGateway;

public interface UpdateStatistics {

	void execute(UpdateStatisticsRequest request);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setStatisticsGateway(StatisticsGateway statisticsGateway);
	
	public interface UpdateStatisticsRequest {
		
		String getGame();
		
	}
	
}
