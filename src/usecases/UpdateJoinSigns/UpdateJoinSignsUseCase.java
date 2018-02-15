package usecases.UpdateJoinSigns;

import java.util.List;

import entities.Location;
import entities.Sign;
import entities.SignImpl;
import game.Game;
import gateways.GameGateway;
import gateways.SignGateway;

public class UpdateJoinSignsUseCase implements UpdateJoinSigns {

	private Game game;
	private UpdateJoinSignsRequest request;
	private GameGateway gameGateway;
	private SignGateway signGateway;
	
	@Override
	public void execute(UpdateJoinSignsRequest request, UpdateJoinSignsResponse response) {
		setRequest(request);
		
		findGame();
		
		if (gameNotFound()) {
			return;
		}
		
		updateJoinSignsOfGame();
	}
	
	private void updateJoinSignsOfGame() {
		List<Location> locations = game.getJoinSigns().getLocations();
		for (Location location : locations) {
			Sign sign = new SignImpl(location);
			sign.setFirstLine("[vh]");
			sign.setSecondLine(request.getGame());
			sign.setThirdLine(request.getGameState());
			sign.setFourthLine(request.getPlayersCount() + "/" + request.getMaximumAmountOfPlayers());
			signGateway.update(sign);
		}
	}
	
	private void findGame() {
		this.game = gameGateway.findGameByName(request.getGame());
	}
	
	private boolean gameNotFound() {
		return game == null;
	}
	
	private void setRequest(UpdateJoinSignsRequest request) {
		this.request = request;
	}
	
	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

	@Override
	public void setSignGateway(SignGateway signGateway) {
		this.signGateway = signGateway;
	}
	
}
