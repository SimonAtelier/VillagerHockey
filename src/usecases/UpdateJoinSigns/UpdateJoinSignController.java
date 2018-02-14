package usecases.UpdateJoinSigns;

import java.util.UUID;

import context.Context;
import game.Game;
import game.IGame;
import game.Event.GameListenerAdapter;
import game.States.GameState;

public class UpdateJoinSignController extends GameListenerAdapter {

	@Override
	public void onPlayerJoin(Game game, UUID player) {
		updateJoinSigns(game);
	}

	@Override
	public void onPlayerLeave(Game game, UUID player) {
		updateJoinSigns(game);
	}

	@Override
	public void onGameStateChanged(IGame game, GameState from, GameState to) {
		updateJoinSigns(game);
	}

	private void updateJoinSigns(IGame game) {
		UpdateJoinSignsRequestModel requestModel = new UpdateJoinSignsRequestModel();
		requestModel.setGame(game.getName());
		requestModel.setGameState(game.getGameState().toString());
		requestModel.setPlayersCount(game.getPlayersCount());
		requestModel.setMaximumAmountOfPlayers(game.getMaximumAmountOfPlayers());
		
		UpdateJoinSigns useCase = new UpdateJoinSignsUseCase();
		useCase.setGameGateway(Context.gameGateway);
		useCase.setSignGateway(Context.signGateway);
		useCase.execute(requestModel, new UpdateJoinSignsResponseImpl());
	}

}
