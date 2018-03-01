package usecases.showachievements;

import java.util.List;

import achievement.AchievementGateway;

public class ShowAchievementsUseCase implements ShowAchievements {

	private AchievementGateway achievementGateway;
	
	@Override
	public void execute(ShowAchievementsRequest request, ShowAchievementsResponse response) {
		List<String> achievements = achievementGateway.findAllAchievements(request.getPlayer());
		response.presentAchievements(achievements);
	}

	@Override
	public void setAchievementGateway(AchievementGateway achievementGateway) {
		this.achievementGateway = achievementGateway;
	}

}
