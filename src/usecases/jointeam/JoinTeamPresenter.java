package usecases.jointeam;

import java.util.List;
import java.util.UUID;

import usecases.jointeam.JoinTeam.JoinTeamResponse;
import view.teamarmour.ColoredTeamArmourView;

public class JoinTeamPresenter implements JoinTeamResponse {

	private JoinTeamView view;
	private ColoredTeamArmourView teamArmourView;
	
	public JoinTeamPresenter(JoinTeamView view, ColoredTeamArmourView teamArmourView) {
		this.view = view;
		this.teamArmourView = teamArmourView;
	}
	
	@Override
	public void onNoPermission() {
		view.displayNoPermission();
	}

	@Override
	public void presentNowInTeam(UUID player, String team, int color) {
		teamArmourView.setColorRGB(color);
		teamArmourView.displayTeamArmour(player);
		view.displayNowInTeam(player, team);
	}

	@Override
	public void presentTeamJoined(List<UUID> players, String player, String team) {
		view.displayJoin(players, player, team);
	}

	@Override
	public void onNoSuchGame(String game) {
		view.displayNoSuchGame(game);
	}

	@Override
	public void onNoSuchTeam(String team) {
		view.displayNoSuchTeam(team);
	}

	@Override
	public void onTeamIsAlreadyFull(String team) {
		view.displayTeamIsAlreadyFull(team);
	}

	@Override
	public void onAlreadyJoinedATeam() {
		view.displayAlreadyJoinedATeam();
	}

}
