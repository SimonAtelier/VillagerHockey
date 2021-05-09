package usecases.displayachievements;

import java.util.ArrayList;
import java.util.List;

import achievements.AchieveCondition;
import achievements.Achievement;
import achievements.AchievementSystem;
import gamestats.GameStatistic;
import gamestats.GameStatisticGateway;
import gateways.PermissionGateway;
import gateways.Permissions;

public class DisplayAchievementsUseCase implements DisplayAchievements {

	private DisplayAchievementsRequest request;
	private DisplayAchievementsResponse response;
	private PermissionGateway permissionGateway;
	private AchievementSystem achievementSystem;
	private GameStatisticGateway gameStatisticGateway;

	@Override
	public void execute(DisplayAchievementsRequest request, DisplayAchievementsResponse response) {
		setRequest(request);
		setResponse(response);

		if (playerHasNoPermission()) {
			sendPlayerHasNoPermissionResponse();
			return;
		}

		loadGameStatistic();
		sendSuccessResponse();
	}

	private void loadGameStatistic() {
		GameStatistic gameStatistic = gameStatisticGateway.findByPlayerId(getRequest().getUniquePlayerId());
		if (gameStatistic == null) {
			gameStatisticGateway.createStatisticsForPlayer(getRequest().getUniquePlayerId());
		}
	}

	private void sendSuccessResponse() {
		List<AchievementResponseItem> responseItems = createResponseItems();
		getResponse().onDisplay(responseItems);
	}

	private void sendPlayerHasNoPermissionResponse() {
		getResponse().onNoPermission();
	}

	private boolean playerHasNoPermission() {
		return !getPermissionGateway().hasPermission(getRequest().getUniquePlayerId(),
				Permissions.DISPLAY_ACHIEVEMENTS);
	}

	private List<AchievementResponseItem> createResponseItems() {
		List<AchievementResponseItem> responseItems = new ArrayList<AchievementResponseItem>();
		for (Achievement achievement : findAllAchievements()) {
			AchievementResponseItem responseItem = new AchievementResponseItem();
			responseItem.setPoints(achievement.getPoints());
			responseItem.setProgress(achievement.isProgress());
			responseItem.setActivationValuesSum(achievement.getActivationValuesSum());
			responseItem.setName(achievement.getName());
			responseItem.setCurrentProgress(calculateCurrentProgressValue(achievement));
			responseItem.setDescription(achievement.getDescription());
			responseItem.setUnlocked(
					getAchievementSystem().isUnlockedForPlayer(achievement.getId(), getRequest().getUniquePlayerId()));
			responseItems.add(responseItem);
		}
		return responseItems;
	}
	
	private int calculateCurrentProgressValue(Achievement achievement) {
		if (!achievement.isProgress())
			return 0;
		
		int progessValue = 0;
		
		GameStatistic gameStatistic = findGameStatistic();
		
		for (AchieveCondition condition : achievement.getAchieveConditions())
			progessValue += gameStatistic.getValue(condition.getPropertyKey());
		
		return progessValue;
	}
	
	private GameStatistic findGameStatistic() {
		return gameStatisticGateway.findByPlayerId(getRequest().getUniquePlayerId());
	}

	private List<Achievement> findAllAchievements() {
		return getAchievementSystem().findAllAchievements();
	}

	private DisplayAchievementsRequest getRequest() {
		return request;
	}

	private void setRequest(DisplayAchievementsRequest request) {
		this.request = request;
	}

	private DisplayAchievementsResponse getResponse() {
		return response;
	}

	private void setResponse(DisplayAchievementsResponse response) {
		this.response = response;
	}

	private AchievementSystem getAchievementSystem() {
		return achievementSystem;
	}

	@Override
	public void setAchievementSystem(AchievementSystem achievementSystem) {
		this.achievementSystem = achievementSystem;
	}

	public GameStatisticGateway getGameStatisticsGateway() {
		return gameStatisticGateway;
	}

	@Override
	public void setGameStatisticsGateway(GameStatisticGateway gameStatisticGateway) {
		this.gameStatisticGateway = gameStatisticGateway;
	}

	private PermissionGateway getPermissionGateway() {
		return permissionGateway;
	}

	@Override
	public void setPermissionGateway(PermissionGateway permissionGateway) {
		this.permissionGateway = permissionGateway;
	}

}
