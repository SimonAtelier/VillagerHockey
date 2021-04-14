package usecases.showstatistics;

import usecases.showstatistics.ShowStatistics.ShowStatisticsResponse;

public class ShowStatisticsPresenter implements ShowStatisticsResponse {

	private ShowStatisticsView view;
	
	public ShowStatisticsPresenter(ShowStatisticsView view) {
		this.view = view;
	}

	@Override
	public void onNoPermission() {
		view.displayNoPermission();
	}

	@Override
	public void onPresentStatistics(ShowStatisticsResponseModel statistics) {
		view.displayStatistics(statistics);
		view.displayAchievements();
	}

}
