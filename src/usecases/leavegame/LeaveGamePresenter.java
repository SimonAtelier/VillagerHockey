package usecases.leavegame;

import java.util.List;
import java.util.UUID;

import usecases.leavegame.LeaveGame.LeaveGameResponse;

public class LeaveGamePresenter implements LeaveGameResponse {

	private LeaveGameView view;
	
	public LeaveGamePresenter(LeaveGameView view) {
		this.view = view;
	}
	
	@Override
	public void onPlayerIsNotIngame() {
		view.displayPlayerIsNotIngame();
	}

	@Override
	public void presentPlayerLeave(List<UUID> players, String player) {
		view.displayPlayerLeave(players, player);
	}
	
}
