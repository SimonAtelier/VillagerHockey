package usecases.UpdateJoinSigns;

import java.util.UUID;

import context.Context;
import game.Game;
import game.GameListenerAdapter;
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
	public void onGameStateChanged(Game game, GameState from, GameState to) {
		updateJoinSigns(game);
	}

	private void updateJoinSigns(Game game) {
		UpdateJoinSignsRequestModel requestModel = new UpdateJoinSignsRequestModel();
		requestModel.setGame(game.getName());
		requestModel.setGameState(game.getGameState().toString());
		requestModel.setPlayersCount(game.getPlayersCount());
		requestModel.setMaximumAmountOfPlayers(game.getTeams().getMaximumAmountOfPlayers());
		
		UpdateJoinSigns useCase = new UpdateJoinSignsUseCase();
		useCase.setGameGateway(Context.gameGateway);
		useCase.setSignGateway(Context.signGateway);
		useCase.execute(requestModel, new UpdateJoinSignsResponseImpl());
	}

}
