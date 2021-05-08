package usecases.unlockachievement;

import achievements.Achievement;
import achievements.AchievementSystem;

public class UnlockAchievementUseCase implements UnlockAchievement {

	private Achievement achievement;
	private UnlockAchievementRequest request;
	private UnlockAchievementResponse response;
	private AchievementSystem achievementSystem;

	@Override
	public void execute(UnlockAchievementRequest request, UnlockAchievementResponse response) {
		setRequest(request);
		setResponse(response);
		findAchievement();
		
		if (noAchievementFound()) {
			getResponse().onNoAchievementWithSuchId(getRequest().getAchievementId());
			return;
		}
		
		if (alreadyUnlocked()) {
			sendAlreadyUnlockedResponse();
			return;
		}
		
		unlock();
		sendUnlockResponse();
	}
	
	private void sendAlreadyUnlockedResponse() {
		getResponse().onAlreadyUnlocked(getRequest().getAchievementId());
	}

	private boolean alreadyUnlocked() {
		return getAchievementSystem().isUnlockedForPlayer(getRequest().getAchievementId(), getRequest().getUniquePlayerId());
	}
	
	private void unlock() {
		getAchievementSystem().unlockAchievementForPlayer(getRequest().getUniquePlayerId(), getRequest().getAchievementId());
	}
	
	private boolean noAchievementFound() {
		return achievement == null;
	}
	
	private void findAchievement() {
		achievement = getAchievementSystem().findAchievementById(getRequest().getAchievementId());
	}
	
	private void sendUnlockResponse() {
		getResponse().onUnlockedAchievement(createResponseModel(achievement));
	}

	private UnlockAchievementResponseModel createResponseModel(Achievement achievement) {
		UnlockAchievementResponseModel responseModel = new UnlockAchievementResponseModel();
		responseModel.setUniquePlayerId(getRequest().getUniquePlayerId());
		responseModel.setPoints(achievement.getPoints());
		responseModel.setName(achievement.getName());
		responseModel.setDescription(achievement.getDescription());
		return responseModel;
	}
	
	private UnlockAchievementRequest getRequest() {
		return request;
	}

	private void setRequest(UnlockAchievementRequest request) {
		this.request = request;
	}

	private UnlockAchievementResponse getResponse() {
		return response;
	}

	private void setResponse(UnlockAchievementResponse response) {
		this.response = response;
	}
	
	private AchievementSystem getAchievementSystem() {
		return achievementSystem;
	}

	@Override
	public void setAchievementSystem(AchievementSystem achievementSystem) {
		this.achievementSystem = achievementSystem;
	}

}
