package usecases.forcestart;

import java.util.UUID;

import game.Game;
import game.states.RespawnGameState;
import game.states.RunningGameState;
import game.states.WaitingGameState;
import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;

public class ForceStartUseCase implements ForceStart {

	private UUID player;
	private Game game;
	private PermissionGateway permissionGateway;
	private GameGateway gameGateway;
	
	@Override
	public void execute(UUID player, ForceStartResponse response) {
		setPlayer(player);
		
		if (noPermission()) {
			response.onNoPermission();
			return;
		}
		
		if (playerIsNotIngame()) {
			return;
		}
		
		findGame();
		
		if (game.getGameState().getClass() != WaitingGameState.class) {
			return;
		}
		
		game.getGameState().transitionToGameState(game, new RespawnGameState(new RunningGameState()));
		
		response.displayForcedStart(game.getName());
	}
	
	private void findGame() {
		game = gameGateway.findGameOfPlayer(player);
	}
	
	private boolean playerIsNotIngame() {
		return !gameGateway.isIngame(player);
	}
	
	private boolean noPermission() {
		return !permissionGateway.hasPermission(player, Permissions.FORCE_START);
	}
	
	private void setPlayer(UUID player) {
		this.player = player;
	}

	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

	@Override
	public void setPermissionGateway(PermissionGateway permissionGateway) {
		this.permissionGateway = permissionGateway;
	}

}
