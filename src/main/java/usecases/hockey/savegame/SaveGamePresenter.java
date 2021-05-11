package usecases.hockey.savegame;

import usecases.hockey.savegame.SaveGame.SaveGameResponse;

public class SaveGamePresenter implements SaveGameResponse {
	
	private SaveGameView view;
	
	public SaveGamePresenter(SaveGameView view) {
		this.view = view;
	}

	@Override
	public void onNoSuchGame(String name) {
		view.displayNoSuchGame(name);
	}

	@Override
	public void onGameSuccessfullySaved() {
		view.displayGameSuccessfullySaved();
	}

	@Override
	public void onNoPermission() {
		view.displayNoPermission();
	}

	@Override
	public void onCannotSaveGameNoLobbySet() {
		view.displayCannotSaveGameNoLobbySet();
	}

	@Override
	public void onCannotSaveGameNoVillagerSpawnSet() {
		view.displayCannotSaveGameNoVillagerSpawnSet();
	}

	@Override
	public void onCannotSavePlayingTimeIsLessOrEqualToZero() {
		view.displayCannotSavePlayingMustBeGreaterThanZero();
	}

	@Override
	public void onCannotSaveNumberOfTeamsIsNotTwo() {
		view.displayCannotSaveNumberOfTeamsIsNotTwo();
	}

	@Override
	public void onCannotSaveNotAllGoalsSet() {
		view.displayCannotSaveNotAllGoalsSet();
	}

	@Override
	public void onCannotSaveSpawnLocationsMissing() {
		view.displayCannotSaveSpawnLocationsMissing();
	}

	@Override
	public void onCannotSaveAmountOfTeamSpawnsIsNotEqual() {
		view.displayCannotSaveAmountOfTeamSpawnsIsNotEqual();
	}

}
