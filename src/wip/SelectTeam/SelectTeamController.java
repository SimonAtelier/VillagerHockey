package wip.SelectTeam;

import java.util.UUID;

import context.Context;
import game.TeamSelectListener;
import usecases.JoinTeam.JoinTeamController;
import usecases.LeaveTeam.LeaveTeam;
import usecases.LeaveTeam.LeaveTeam.LeaveTeamResponse;
import usecases.LeaveTeam.LeaveTeamPresenter;
import usecases.LeaveTeam.LeaveTeamRequestModel;
import usecases.LeaveTeam.LeaveTeamUseCase;
import usecases.LeaveTeam.LeaveTeamView;
import usecases.LeaveTeam.LeaveTeamViewImpl;
import view.ColoredTeamArmourView;
import view.impl.ColoredTeamArmourViewImpl;

public class SelectTeamController implements TeamSelectListener {

	@Override
	public void onTeamSelected(UUID player, String game, String team) {
		executeLeaveTeamUseCase(player);
		new JoinTeamController().onTeamSelected(player, game, team);
	}
	
	private void executeLeaveTeamUseCase(UUID uniquePlayerId) {
		ColoredTeamArmourView teamArmourView = new ColoredTeamArmourViewImpl();
		
		LeaveTeamView view = new LeaveTeamViewImpl();
		LeaveTeamResponse presenter = new LeaveTeamPresenter(view, teamArmourView);
		LeaveTeamRequestModel request = new LeaveTeamRequestModel();
		request.setPlayer(uniquePlayerId);
		
		LeaveTeam useCase = new LeaveTeamUseCase();
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPlayerGateway(Context.playerGateway);
		useCase.execute(request, presenter);
	}

}
