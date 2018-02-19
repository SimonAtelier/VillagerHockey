package usecases.UpdateJoinSigns;

import java.util.List;

import entities.Location;
import entities.Sign;
import entities.SignImpl;
import game.Game;
import gateways.GameGateway;
import gateways.JoinSignGateway;
import gateways.SignGateway;

public class UpdateJoinSignsUseCase implements UpdateJoinSigns {

	private Game game;
	private UpdateJoinSignsRequest request;
	private GameGateway gameGateway;
	private SignGateway signGateway;
	private JoinSignGateway joinSignGateway;
	
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
		for (Location location : findJoinSignLocations()) {
			Sign sign = new SignImpl(location);
			sign.setFirstLine("[vh]");
			sign.setSecondLine(request.getGame());
			sign.setThirdLine(request.getGameState());
			sign.setFourthLine(request.getPlayersCount() + "/" + request.getMaximumAmountOfPlayers());
			signGateway.update(sign);
		}
	}
	
	private List<Location> findJoinSignLocations() {
		return joinSignGateway.findJoinSignLocations(request.getGame());
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

	@Override
	public void setJoinSignGateway(JoinSignGateway joinSignGateway) {
		this.joinSignGateway = joinSignGateway;
	}
	
}
