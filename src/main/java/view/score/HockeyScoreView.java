package view.score;

import java.util.List;
import java.util.UUID;

import context.Context;
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
//		Player player = Bukkit.getPlayer(viewer);
//		ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
//		Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
//		player.setScoreboard(scoreboard);
		scoreView.hide(viewer);
	}

	public void display(UUID viewer) {
		ScoreViewModel scoreViewModel = Context.viewFactory.createScoreViewModel();
		scoreViewModel.setTitle("§eVILLAGER HOCKEY");
		scoreViewModel.addItem("");
		scoreViewModel.addItem("Scores");
		
		GameGateway gameGateway = Context.gameGateway;
		Game game = gameGateway.findGameOfPlayer(viewer);
		List<Team> teams = game.getTeams().findAllTeams();
		for (Team team : teams) {
			String item = team.getName() + " : " + team.getScore();
			scoreViewModel.addItem(item);
		}
		
		scoreViewModel.addItem("");
		scoreViewModel.addItem("");
		scoreViewModel.addItem("§eserver.foo.bar");
		scoreViewModel.setViewer(viewer);
		scoreView.display(scoreViewModel);
		
		//		GameGateway gameGateway = Context.gameGateway;
//		Game game = gameGateway.findGameOfPlayer(viewer);
//		List<Team> teams = game.getTeams().findAllTeams();
//
//		Player player = Bukkit.getPlayer(viewer);
//		ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
//		Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
//		Objective objective = scoreboard.getObjective("aaa") != null ? scoreboard.getObjective("aaa")
//				: scoreboard.registerNewObjective("aaa", "bbb");
//		objective.setDisplayName("Punktestand");
//		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
//
//		for (Team team : teams) {
//			objective.getScore(team.getName()).setScore(0);
//		}
//
//		player.setScoreboard(scoreboard);
	}

	public void update(UUID viewer, List<ScoreResponseItem> scores) {
		ScoreViewModel scoreViewModel = Context.viewFactory.createScoreViewModel();
		scoreViewModel.setTitle("§eVILLAGER HOCKEY");
		scoreViewModel.addItem("");
		scoreViewModel.addItem("Scores");
		
		GameGateway gameGateway = Context.gameGateway;
		Game game = gameGateway.findGameOfPlayer(viewer);
		List<Team> teams = game.getTeams().findAllTeams();
		for (Team team : teams) {
			String item = team.getName() + " : " + team.getScore();
			scoreViewModel.addItem(item);
		}
		
		scoreViewModel.addItem("");
		scoreViewModel.addItem("");
		scoreViewModel.addItem("§eserver.foo.bar");
		scoreViewModel.setViewer(viewer);
		scoreView.display(scoreViewModel);
		
//		Player player = Bukkit.getPlayer(viewer);
//		if (player.getScoreboard() != null) {
//			Objective objective = player.getScoreboard().getObjective("aaa") != null
//					? player.getScoreboard().getObjective("aaa")
//					: player.getScoreboard().registerNewObjective("aaa", "bbb");
//			for (ScoreResponseItem score : scores) {
//				objective.getScore(score.getTeam()).setScore(score.getScore());
//			}
//		}
	}

}
