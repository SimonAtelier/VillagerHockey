package usecases.hockey.setvillagerspawn;

import java.util.UUID;

import context.Context;
import minigame.view.MessageView;

public class SetVillagerSpawnViewImpl implements SetVillagerSpawnView {

	private UUID viewer;
	
	public SetVillagerSpawnViewImpl(UUID viewer) {
		this.viewer = viewer;
	}
	
	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}
	
	@Override
	public void displayNoPermission() {
		displayMessage(viewer, SetVillagerSpawnViewMessages.SET_VILLAGER_SPAWN_NO_PERMISSION);
	}

	@Override
	public void displayNoSuchGame() {
		displayMessage(viewer, SetVillagerSpawnViewMessages.SET_VILLAGER_SPAWN_NO_SUCH_GAME);
	}

	@Override
	public void displayVillagerSpawnSuccessfullySet(String game) {
		String message = SetVillagerSpawnViewMessages.SET_VILLAGER_SPAWN_SUCCESSFULLY_SET;
		message = message.replace("$game$", game);
		displayMessage(viewer, message);
	}

}
