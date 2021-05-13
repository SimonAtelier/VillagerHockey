package usecases.encaps.prepareplayerforgame;

import game.Game;
import gateways.GameGateway;

public class PreparePlayerForGameUseCase implements PreparePlayerForGame {

	private Game game;
	private PreparePlayerForGameRequest request;
	private PreparePlayerForGameResponse response;
	private GameGateway gameGateway;
	
	@Override
	public void execute(PreparePlayerForGameRequest request, PreparePlayerForGameResponse response) {
		setRequest(request);
		setResponse(response);
		
		findGame();
		
		if (game == null) {
			return;
		}
		
		sendResponse();
	}
	
	private void sendResponse() {
		getResponse().presentHockeySticks(getRequest().getUniquePlayerId());
		getResponse().presentScores(getRequest().getUniquePlayerId());
	}
	
	private void findGame() {
		game = getGameGateway().findGameByName(getRequest().getGameName());
	}
	
	private PreparePlayerForGameRequest getRequest() {
		return request;
	}

	private void setRequest(PreparePlayerForGameRequest request) {
		this.request = request;
	}

	private PreparePlayerForGameResponse getResponse() {
		return response;
	}

	private void setResponse(PreparePlayerForGameResponse response) {
		this.response = response;
	}
	
	private GameGateway getGameGateway() {
		return gameGateway;
	}
	
	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

}
