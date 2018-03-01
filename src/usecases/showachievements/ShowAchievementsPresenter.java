package usecases.showachievements;

import java.util.List;

import usecases.showachievements.ShowAchievements.ShowAchievementsResponse;

public class ShowAchievementsPresenter implements ShowAchievementsResponse {

	private ShowAchievementsView view;
	
	public ShowAchievementsPresenter(ShowAchievementsView view) {
		this.view = view;
	}

	@Override
	public void presentAchievements(List<String> achievements) {
		view.displayAchievements(achievements);
	}

}
