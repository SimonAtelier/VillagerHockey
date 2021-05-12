package usecases.api.jointeam;

import java.util.UUID;

import context.Context;
import game.event.TeamSelectListener;
import minigame.view.ColoredTeamArmourView;
import usecases.api.jointeam.JoinTeam.JoinTeamResponse;

public class JoinTeamController implements TeamSelectListener {
	
	private UUID player;
	private String game;
	private String team;
	
	@Override
	public void onTeamSelected(UUID player, String game, String team) {		
		setPlayer(player);
		setGame(game);
		setTeam(team);
		executeUseCase();
	}
	
	private void executeUseCase() {
		createUseCase().execute(createRequestModel(), createResponse());
	}
	
	private JoinTeam createUseCase() {
		JoinTeam useCase = new JoinTeamUseCase();
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPermissionGateway(Context.permissionGateway);
		useCase.setPlayerGateway(Context.playerGateway);
		return useCase;
	}
	
	private JoinTeamResponse createResponse() {
		ColoredTeamArmourView teamArmourView = Context.viewFactory.createColoredTeamArmourView();
		JoinTeamView view = new JoinTeamViewImpl(getPlayer());
		JoinTeamResponse presenter = new JoinTeamPresenter(view, teamArmourView);
		return presenter;
	}
	
	private JoinTeamRequestModel createRequestModel() {
		JoinTeamRequestModel requestModel = new JoinTeamRequestModel();
		requestModel.setGame(getGame());
		requestModel.setTeam(getTeam());
		requestModel.setPlayer(getPlayer());
		return requestModel;
	}

	private UUID getPlayer() {
		return player;
	}

	private void setPlayer(UUID player) {
		this.player = player;
	}

	private String getGame() {
		return game;
	}

	private void setGame(String game) {
		this.game = game;
	}

	private String getTeam() {
		return team;
	}

	private void setTeam(String team) {
		this.team = team;
	}
		 
}
