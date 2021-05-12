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
		ScoreViewModel scoreViewModel = Context.viewFactory.createScoreViewModel();
		scoreViewModel.setTitle("§eVILLAGER HOCKEY");
		scoreViewModel.addItem(" ");
		scoreViewModel.addItem("Scores");

		GameGateway gameGateway = Context.gameGateway;
		Game game = gameGateway.findGameOfPlayer(viewer);
		
		List<Team> teams = game.getTeams().findAllTeams();
		for (Team team : teams) {
			String messageCode = ColorUtil.toMessageCode(team.getColor());
			String item = messageCode + team.getName() + " §r: " + team.getScore();
			scoreViewModel.addItem(item);
		}
		
		scoreViewModel.addItem("  ");
		scoreViewModel.addItem("§eserver.foo.bar");
		scoreViewModel.setViewer(viewer);
		scoreView.display(scoreViewModel);
	}

	public void update(UUID viewer, List<ScoreResponseItem> scores) {
		display(viewer);
	}

}
