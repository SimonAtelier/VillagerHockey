package usecases.hockey.pinata;

import game.Game;
import gateways.GameGateway;
import gateways.PlayerGateway;

public class PinataUseCase implements Pinata {

	private PinataRequest request;
	private PinataResponse response;
	private PlayerGateway playerGateway;
	private GameGateway gameGateway;
	
	@Override
	public void execute(PinataRequest request, PinataResponse response) {
		setRequest(request);
		setResponse(response);
		
		Game game = getGameGateway().findGameByName(getRequest().getGameName());
		if (game == null)
			return;
		
		getResponse().onPresent(game.getUniquePlayerIds());
	}

	private PinataRequest getRequest() {
		return request;
	}

	private void setRequest(PinataRequest request) {
		this.request = request;
	}

	private PinataResponse getResponse() {
		return response;
	}

	private void setResponse(PinataResponse response) {
		this.response = response;
	}
	
	private PlayerGateway getPlayerGateway() {
		return playerGateway;
	}

	@Override
	public void setPlayerGateway(PlayerGateway playerGateway) {
		this.playerGateway = playerGateway;
	}

	private GameGateway getGameGateway() {
		return gameGateway;
	}
	
	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}
	
}
