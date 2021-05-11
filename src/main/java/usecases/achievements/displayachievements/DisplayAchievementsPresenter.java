package usecases.achievements.displayachievements;

import java.util.List;

import usecases.achievements.displayachievements.DisplayAchievements.DisplayAchievementsResponse;

public class DisplayAchievementsPresenter implements DisplayAchievementsResponse {

	private DisplayAchievementsView view;
	
	public DisplayAchievementsPresenter(DisplayAchievementsView view) {
		this.view = view;
	}
	
	@Override
	public void onDisplay(List<AchievementResponseItem> responseItems) {
		view.display(responseItems);
	}

	@Override
	public void onNoPermission() {
		view.displayMessage(DisplayAchievementsMessages.DISPLAY_ACHIEVEMENTS_NO_PERMISSION);
	}

}
