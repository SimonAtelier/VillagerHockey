package gateways;

import java.util.UUID;

import entities.Statistics;
import gateways.impl.GatewayException;

public interface StatisticsGateway {

	Statistics findStatistics(UUID player);
	
	void saveStatistics(UUID player, Statistics statistics) throws GatewayException;
	
}
