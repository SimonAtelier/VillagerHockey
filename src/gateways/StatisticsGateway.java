package gateways;

import java.util.UUID;

import entities.Statistics;

public interface StatisticsGateway {

	Statistics findStatistics(UUID player);
	
}
