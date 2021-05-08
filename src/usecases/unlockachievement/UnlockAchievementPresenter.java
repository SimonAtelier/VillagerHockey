package usecases.unlockachievement;

import usecases.unlockachievement.UnlockAchievement.UnlockAchievementResponse;

public class UnlockAchievementPresenter implements UnlockAchievementResponse {

	private UnlockAchievementView view;
	
	public UnlockAchievementPresenter(UnlockAchievementView view) {
		this.view = view;
	}
	
	private void displayMessage(String message) {
		view.displayMessage(message);
	}
	
	@Override
	public void onUnlockedAchievement(UnlockAchievementResponseModel responseModel) {
		String message = UnlockAchievementMessages.UNLOCKED_SELF;
		message = message.replace("$name$", responseModel.getName());
		displayMessage(message);
	}

	@Override
	public void onNoAchievementWithSuchId(String id) {
		String message = UnlockAchievementMessages.NO_ACHIEVEMENT_WITH_SUCH_ID;
		message = message.replace("$id$", id);
		view.displayConsoleMessage(message);
	}

	@Override
	public void onAlreadyUnlocked(String id) {
		String message = UnlockAchievementMessages.ALREADY_UNLOCKED;
		message = message.replace("$id$", id);
		view.displayConsoleMessage(message);
	}

}
