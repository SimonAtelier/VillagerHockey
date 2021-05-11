package usecases.encaps.displaywinner;

import java.util.List;
import java.util.UUID;

import entities.Team;
import game.Game;
import gateways.GameGateway;

public class DisplayWinnerUseCase implements DisplayWinner {

	private GameGateway gameGateway;
	
	@Override
	public void execute(DisplayWinnerRequest request, DisplayWinnerResponse response) {
		Game game = gameGateway.findGameByName(request.getGame());
		
		if (game == null)
			return;
		
		List<UUID> players = game.getUniquePlayerIds();
		
		if (game.getTeams().equalScores()) {
			response.presentDraw(players);
			return;
		}
		
		Team winner = game.getTeams().findTeamWithHighestScore();
		response.presentWinner(players, winner.getName());
	}

	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

}
