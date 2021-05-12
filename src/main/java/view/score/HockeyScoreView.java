package view.score;

import java.util.List;
import java.util.UUID;

import context.Context;
import entities.ColorUtil;
import entities.Team;
import game.Game;
import gateways.GameGateway;
import minigame.view.ScoreView;
import minigame.view.ScoreViewModel;
import usecases.hockey.displayteamscored.ScoreResponseItem;

public class HockeyScoreView {
	
	private ScoreView scoreView;
	
	public HockeyScoreView() {
		scoreView = Context.viewFactory.createScoreView();
	}
	
	public void hide(UUID viewer) {
		scoreView.hide(viewer);
	}

	public void display(UUID viewer) {
		
		String title = Context.configuration.getScoreboardTitle();
		String serverAdress = Context.configuration.getScoreboardServerAdress();
		ScoreViewModel scoreViewModel = Context.viewFactory.createScoreViewModel();
		scoreViewModel.setTitle(title);
		scoreViewModel.addItem(" ");

		GameGateway gameGateway = Context.gameGateway;
		Game game = gameGateway.findGameOfPlayer(viewer);
		
		List<Team> teams = game.getTeams().findAllTeams();
		Team teamOfPlayer = game.getTeams().findTeamOfPlayer(viewer);
		
		for (Team team : teams) {
			String messageCode = ColorUtil.toMessageCode(team.getColor());			
			String item = messageCode + team.getName() + " §r: " + team.getScore();
			if (teamOfPlayer != null)
				item += team.getName().equals(teamOfPlayer.getName()) ? "  §7YOU" : "";
			scoreViewModel.addItem(item);
		}
		
		scoreViewModel.addItem("  ");
		scoreViewModel.addItem(serverAdress);
		scoreViewModel.setViewer(viewer);
		scoreView.display(scoreViewModel);
	}

	public void update(UUID viewer, List<ScoreResponseItem> scores) {
		display(viewer);
	}
	
}
