package usecases.joingame;

import java.util.List;
import java.util.UUID;

import usecases.joingame.JoinGame.JoinGameResponse;

public class JoinGamePresenter implements JoinGameResponse {

	private JoinGameView view;
	
	public JoinGamePresenter(JoinGameView view) {
		this.view = view;
	}

	@Override
	public void onNoSuchGame(String name) {
		view.displayNoSuchGame(name);
	}

	@Override
	public void onPlayerAlreadyJoinedAGame() {
		view.displayPlayerAlreadyJoinedAGame();
	}

	@Override
	public void presentPlayerJoin(List<UUID> players, String player) {
		view.displayPlayerJoin(players, player);
	}

	@Override
	public void onPlayerCannotJoin() {
		view.displayCannotJoin();
	}
	
	@Override
	public void onMaximumAmountOfPlayersReached() {
		view.displayMaximumAmountOfPlayersReached();
	}

	@Override
	public void onNoPermission() {
		view.displayNoPermission();
	}
		
}
