package usecases.leaveteam;

import java.util.List;
import java.util.UUID;

import usecases.leaveteam.LeaveTeam.LeaveTeamResponse;
import view.ColoredTeamArmourView;

public class LeaveTeamPresenter implements LeaveTeamResponse {

	private LeaveTeamView view;
	private ColoredTeamArmourView teamArmourView;
	
	public LeaveTeamPresenter(LeaveTeamView view, ColoredTeamArmourView teamArmourView) {
		this.view = view;
		this.teamArmourView = teamArmourView;
	}
	
	@Override
	public void presentLeftTeam(UUID player, String team) {
		teamArmourView.hideTeamArmour(player);
		view.displayLeftTeam(player, team);
	}

	@Override
	public void presentLeftTeam(List<UUID> players, String player, String team) {
		view.displayLeftTeam(players, player, team);
	}

}
