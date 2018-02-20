package usecases.selectteam;

import java.util.UUID;

import context.Context;
import game.event.TeamSelectListener;
import usecases.jointeam.JoinTeamController;
import usecases.leaveteam.LeaveTeam;
import usecases.leaveteam.LeaveTeamPresenter;
import usecases.leaveteam.LeaveTeamRequestModel;
import usecases.leaveteam.LeaveTeamUseCase;
import usecases.leaveteam.LeaveTeamView;
import usecases.leaveteam.LeaveTeamViewImpl;
import usecases.leaveteam.LeaveTeam.LeaveTeamResponse;
import view.teamarmour.ColoredTeamArmourView;
import view.teamarmour.ColoredTeamArmourViewImpl;

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
