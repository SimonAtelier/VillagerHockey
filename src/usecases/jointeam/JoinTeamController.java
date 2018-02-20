package usecases.jointeam;

import java.util.UUID;

import context.Context;
import game.event.TeamSelectListener;
import usecases.jointeam.JoinTeam.JoinTeamResponse;
import view.teamarmour.ColoredTeamArmourView;
import view.teamarmour.ColoredTeamArmourViewImpl;

public class JoinTeamController implements TeamSelectListener {
	
	@Override
	public void onTeamSelected(UUID player, String game, String team) {			
		JoinTeamRequestModel requestModel = new JoinTeamRequestModel();
		requestModel.setGame(game);
		requestModel.setTeam(team);
		requestModel.setPlayer(player);
		
		ColoredTeamArmourView teamArmourView = new ColoredTeamArmourViewImpl();
		JoinTeamView view = new JoinTeamViewImpl(player);
		
		JoinTeamResponse presenter = new JoinTeamPresenter(view, teamArmourView);
		
		JoinTeam useCase = new JoinTeamUseCase();
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPermissionGateway(Context.permissionGateway);
		useCase.setPlayerGateway(Context.playerGateway);
		useCase.execute(requestModel, presenter);
	}
		
}
