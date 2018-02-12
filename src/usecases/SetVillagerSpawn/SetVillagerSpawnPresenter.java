package usecases.SetVillagerSpawn;

import usecases.SetVillagerSpawn.SetVillagerSpawn.SetVillagerSpawnResponse;

public class SetVillagerSpawnPresenter implements SetVillagerSpawnResponse {

	private SetVillagerSpawnView view;
	
	public SetVillagerSpawnPresenter(SetVillagerSpawnView view) {
		this.view = view;
	}
	
	@Override
	public void onNoPermission() {
		view.displayNoPermission();
	}
	
	@Override
	public void onNoSuchGame() {
		view.displayNoSuchGame();
	}

	@Override
	public void onVillagerSpawnSuccessfullySet(String game) {
		view.displayVillagerSpawnSuccessfullySet(game);
	}

}
