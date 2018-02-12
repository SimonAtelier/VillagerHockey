package usecases.DisplayTeamScored;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import entities.Team;
import game.Game;
import gateways.GameGateway;

public class DisplayTeamScoredUseCase implements DisplayTeamScored {

	private GameGateway gameGateway;
	
	@Override
	public void execute(DisplayTeamScoredRequest request, DisplayTeamScoredResponse response) {
		Game game = gameGateway.findGameByName(request.getGame());
		
		List<UUID> viewers = game.getUniquePlayerIds();
		response.presentTeamScored(viewers, request.getTeam());
		
		List<ScoreResponseItem> responseItems = new ArrayList<ScoreResponseItem>();
		List<Team> teams = game.getTeams().findAllTeams();
		for (Team team : teams) {
			ScoreResponseItem responseItem = new ScoreResponseItem();
			responseItem.setTeam(team.getName());
			responseItem.setScore(team.getScore());
			responseItems.add(responseItem);
		}
		
		response.presentScore(viewers, responseItems);		
	}

	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

}
