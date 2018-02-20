package usecases.savegame;

import java.util.UUID;

import context.Context;
import view.MessageView;

public class SaveGameViewImpl implements SaveGameView {

	private UUID viewer;

	public SaveGameViewImpl(UUID viewer) {
		this.viewer = viewer;
	}

	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}

	@Override
	public void displayNoSuchGame(String name) {
		String message = SaveGameViewMessages.SAVE_GAME_NO_SUCH_GAME;
		message = message.replace("$game$", name);
		displayMessage(viewer, message);
	}

	@Override
	public void displayGameSuccessfullySaved() {
		displayMessage(viewer, SaveGameViewMessages.SAVE_GAME_SUCCESSFULLY_SAVED_GAME);
	}

	@Override
	public void displayNoPermission() {
		displayMessage(viewer, SaveGameViewMessages.SAVE_GAME_NO_PERMISSION);
	}

	@Override
	public void displayCannotSaveGameNoLobbySet() {
		displayMessage(viewer, SaveGameViewMessages.SAVE_GAME_CANNOT_SAVE_NO_LOBBY_SET);
	}

	@Override
	public void displayCannotSaveGameNoVillagerSpawnSet() {
		displayMessage(viewer, SaveGameViewMessages.SAVE_GAME_CANNOT_SAVE_NO_VILLAGER_SPAWN_SET);
	}

	@Override
	public void displayCannotSavePlayingMustBeGreaterThanZero() {
		displayMessage(viewer, SaveGameViewMessages.SAVE_GAME_CANNOT_SAVE_PLAYING_TIME_MUST_BE_GREATER_THAN_ZERO);
	}

	@Override
	public void displayCannotSaveNumberOfTeamsIsNotTwo() {
		displayMessage(viewer, SaveGameViewMessages.SAVE_GAME_CANNOT_SAVE_NUMBER_OF_TEAMS_IS_NOT_TWO);
	}

	@Override
	public void displayCannotSaveNotAllGoalsSet() {
		displayMessage(viewer, SaveGameViewMessages.SAVE_GAME_CANNOT_SAVE_NOT_ALL_GOALS_SET);
	}

	@Override
	public void displayCannotSaveSpawnLocationsMissing() {
		displayMessage(viewer, SaveGameViewMessages.SAVE_GAME_CANNOT_SAVE_SPAWN_LOCATIONS_MISSING);
	}

	@Override
	public void displayCannotSaveAmountOfTeamSpawnsIsNotEqual() {
		displayMessage(viewer, SaveGameViewMessages.SAVE_GAME_CANNOT_SAVE_AMOUNT_OF_TEAM_SPAWNS_IS_NOT_EQUAL);
	}

}
