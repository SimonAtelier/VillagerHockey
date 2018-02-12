package usecases.ShowTeams;

import java.util.ArrayList;
import java.util.List;

import entities.Team;
import entities.Teams;
import game.Game;
import gateways.GameGateway;

public class ShowTeamsUseCase implements ShowTeams {

	private Game game;
	private ShowTeamsRequest request;
	private GameGateway gameGateway;
	
	@Override
	public void execute(ShowTeamsRequest request, ShowTeamsResponse response) {
		setRequest(request);
		
		if (playerIsNotIngame()) {
			response.onPlayerIsNotIngame();
			return;
		}
		
		findGameOfPlayer();

		response.presentTeams(createResponseItems());
	}
	
	private void findGameOfPlayer() {
		game = gameGateway.getGameOfPlayer(request.getPlayer());
	}
	
	private List<Team> findTeams() {
		Teams teams = game.getTeams();
		return teams.findAllTeams();
	}
	
	private boolean playerIsNotIngame() {
		return !gameGateway.isIngame(request.getPlayer());
	}
		
	private List<ShowTeamsResponseItem> createResponseItems() {
		List<ShowTeamsResponseItem> responseItems = new ArrayList<ShowTeamsResponseItem>();
		for (Team team : findTeams()) {
			responseItems.add(createResponseItem(team, game.getName()));
		}
		return responseItems;
	}
	
	private ShowTeamsResponseItem createResponseItem(Team team, String game) {
		ShowTeamsResponseItem responseItem = new ShowTeamsResponseItem();
		responseItem.setName(team.getName());
		responseItem.setSize(team.size());
		responseItem.setMaxSize(team.getMaximumSize());
		responseItem.setGame(game);
		responseItem.setColor(team.getColor().getRGB());
		return responseItem;
	}
	
	private void setRequest(ShowTeamsRequest request) {
		this.request = request;
	}

	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}
	
}
