package usecases.UpdateJoinSigns;

import java.util.UUID;

import context.Context;
import game.Game;
import game.Event.GameStateChangeListener;
import game.Event.JoinListener;
import game.Event.LeaveListener;
import game.States.GameState;

public class UpdateJoinSignController implements JoinListener, LeaveListener, GameStateChangeListener {

	private Game game;
	
	public UpdateJoinSignController(Game game) {
		this.game = game;
		enable();
		updateJoinSigns(game);
	}
	
	private void enable() {
		game.addJoinListener(this);
		game.addLeaveListener(this);
		game.addGameStateChangeListener(this);
	}
	
	@Override
	public void onPlayerJoin(Game game, UUID player) {
		updateJoinSigns(game);
	}

	@Override
	public void onPlayerLeave(Game game, UUID player) {
		updateJoinSigns(game);
	}

	@Override
	public void onGameStateChanged(Game game, GameState from, GameState to) {
		updateJoinSigns(game);
	}

	private void updateJoinSigns(Game game) {
		UpdateJoinSignsRequestModel requestModel = new UpdateJoinSignsRequestModel();
		requestModel.setGame(game.getName());
		requestModel.setGameState(game.getGameState().toString());
		requestModel.setPlayersCount(game.getPlayersCount());
		requestModel.setMaximumAmountOfPlayers(game.getMaximumAmountOfPlayers());
		
		UpdateJoinSigns useCase = new UpdateJoinSignsUseCase();
		useCase.setGameGateway(Context.gameGateway);
		useCase.setSignGateway(Context.signGateway);
		useCase.setJoinSignGateway(Context.joinSignGateway);
		useCase.execute(requestModel, new UpdateJoinSignsResponseImpl());
	}

}
