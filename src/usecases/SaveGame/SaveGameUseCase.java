package usecases.SaveGame;

import java.util.UUID;

import game.Game;
import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;

public class SaveGameUseCase implements SaveGame {

	private String name;
	private UUID player;
	private Game game;
	private GameGateway gameGateway;
	private PermissionGateway permissionGateway;
	
	@Override
	public void execute(UUID player, String name, SaveGameResponse response) {		
		this.player = player;
		this.name = name;
		
		if (noPermission()) {
			response.onNoPermission();
			return;
		}
				
		if (noSuchGame()) {
			response.onNoSuchGame(name);
			return;
		}
		
		Game game = gameGateway.findGameByName(name);
				
		if (game == null) {
			response.onInternalError();
			return;
		}
		
		setGame(game);
		
		if (noLobbySet()) {
			response.onCannotSaveGameNoLobbySet();
			return;
		}
		
		if (noVillagerSpawnSet()) {
			response.onCannotSaveGameNoVillagerSpawnSet();
			return;
		}
		
		if (playingTimeIsLessOrEqualToZero()) {
			response.onCannotSavePlayingTimeIsLessOrEqualToZero();
			return;
		}
		
		if (numberOfTeamsIsLessThanTwo()) {
			response.onCannotSaveNumberOfTeamsIsLessThanTwo();
			return;
		}
		
		gameGateway.saveGame(name);
		response.onGameSuccessfullySaved();
	}
	
	private boolean numberOfTeamsIsLessThanTwo() {
		return game.getTeams().getNumberOfTeams() < 2;
	}
	
	private boolean noSuchGame() {
		return !gameGateway.containsGame(name);
	}
	
	private boolean noPermission() {
		return !permissionGateway.hasPermission(player, Permissions.SAVE_GAME);
	}
	
	private boolean playingTimeIsLessOrEqualToZero() {
		return game.getPlayingTimeInSeconds() <= 0;
	}
	
	private boolean noLobbySet() {
		return !game.isLobbySet();
	}
	
	private boolean noVillagerSpawnSet() {
		return !game.isVillageSpawnLocationSet();
	}
	
	private void setGame(Game game) {
		this.game = game;
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
