package usecases.achievements.unlockachievement;

import java.util.UUID;

import context.Context;

public class UnlockAchievementViewImpl implements UnlockAchievementView {

	private UUID viewer;
	
	public UnlockAchievementViewImpl(UUID viewer) {
		this.viewer = viewer;
	}

	@Override
	public void displayMessage(String message) {
		Context.messageView.displayMessage(viewer, message);
	}

	@Override
	public void displayConsoleMessage(String message) {
		Context.messageView.displayConsoleMessage(message);
	}
	
}
