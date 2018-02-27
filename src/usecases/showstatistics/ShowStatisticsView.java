package usecases.showstatistics;

public interface ShowStatisticsView {

	void displayNoPermission();
	
	void displayStatistics(ShowStatisticsResponseModel statistics);
	
}
