package wip.ScoreView;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import context.Context;
import entities.Team;
import game.Game;
import gateways.GameGateway;
import usecases.DisplayTeamScored.ScoreResponseItem;

public class ScoreView {
	
	public void hide(UUID viewer) {
		Player player = Bukkit.getPlayer(viewer);
		ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
		Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
		player.setScoreboard(scoreboard);
	}

	public void display(UUID viewer) {
		GameGateway gameGateway = Context.gameGateway;
		Game game = gameGateway.getGameOfPlayer(viewer);
		List<Team> teams = game.getTeams().findAllTeams();

		Player player = Bukkit.getPlayer(viewer);
		ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
		Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
		Objective objective = scoreboard.getObjective("aaa") != null ? scoreboard.getObjective("aaa")
				: scoreboard.registerNewObjective("aaa", "bbb");
		objective.setDisplayName("Punktestand");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);

		for (Team team : teams) {
			objective.getScore(team.getName()).setScore(0);
		}

		player.setScoreboard(scoreboard);
	}

	public void update(UUID viewer, List<ScoreResponseItem> scores) {
		Player player = Bukkit.getPlayer(viewer);
		if (player.getScoreboard() != null) {
			Objective objective = player.getScoreboard().getObjective("aaa") != null
					? player.getScoreboard().getObjective("aaa")
					: player.getScoreboard().registerNewObjective("aaa", "bbb");
			for (ScoreResponseItem score : scores) {
				objective.getScore(score.getTeam()).setScore(score.getScore());
			}
		}
	}

}
