package usecases.achievements.displayachievements;

import java.util.List;

public interface DisplayAchievementsView {

	void display(List<AchievementResponseItem> responseItems);
	
	void displayMessage(String message);
	
}
