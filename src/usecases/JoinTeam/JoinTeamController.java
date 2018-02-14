package usecases.JoinTeam;

import java.util.UUID;

import context.Context;
import game.Event.TeamSelectListener;
import usecases.JoinTeam.JoinTeam.JoinTeamResponse;
import view.ColoredTeamArmourView;
import view.impl.ColoredTeamArmourViewImpl;

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
