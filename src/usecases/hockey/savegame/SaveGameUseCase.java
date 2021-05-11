package usecases.hockey.savegame;

import java.util.UUID;

import game.Game;
import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;
import usecases.savegame.validation.AllGoalsShouldBeSetRule;
import usecases.savegame.validation.AmountOfTeamSpawnsShouldBeEqualRule;
import usecases.savegame.validation.LobbyShouldBeSetRule;
import usecases.savegame.validation.NumberOfTeamsShouldBeTwoRule;
import usecases.savegame.validation.PlayingTimeShouldNotBeLessOrEqualToZeroRule;
import usecases.savegame.validation.SaveGameValidator;
import usecases.savegame.validation.TeamSpawnLocationsShouldBeSetRule;
import usecases.savegame.validation.VillagerSpawnLocationShouldBeSetRule;

public class SaveGameUseCase implements SaveGame {

	private String name;
	private UUID player;
	private Game game;
	private SaveGameResponse response;
	private GameGateway gameGateway;
	private PermissionGateway permissionGateway;
	
	@Override
	public void execute(SaveGameRequest request, SaveGameResponse response) {	
		this.response = response;
		this.player = request.getUniquePlayerId();
		this.name = request.getGameName();
		
		if (noPermission()) {
			response.onNoPermission();
			return;
		}
				
		if (noSuchGame()) {
			response.onNoSuchGame(name);
			return;
		}
		
		findGame();

		if (cannotSaveGame())
			return;
		
		save();
		response.onGameSuccessfullySaved();
	}
	
	private boolean cannotSaveGame() {
		return gameIsInvalid();
	}
	
	private boolean gameIsInvalid() {
		SaveGameValidator validator = new SaveGameValidator();
		validator.addValidationRule(new NumberOfTeamsShouldBeTwoRule());
		validator.addValidationRule(new LobbyShouldBeSetRule());
		validator.addValidationRule(new VillagerSpawnLocationShouldBeSetRule());
		validator.addValidationRule(new PlayingTimeShouldNotBeLessOrEqualToZeroRule());
		validator.addValidationRule(new AmountOfTeamSpawnsShouldBeEqualRule());
		validator.addValidationRule(new AllGoalsShouldBeSetRule());
		validator.addValidationRule(new TeamSpawnLocationsShouldBeSetRule());
		validator.setGame(game);
		validator.setResponse(response);
		validator.validate();
		return validator.isInvalid();
	}
	
	private void save() {
		gameGateway.saveGame(name);
	}
	
	private void findGame() {
		game = gameGateway.findGameByName(name);
	}
	
	private boolean noSuchGame() {
		return !gameGateway.containsGame(name);
	}
	
	private boolean noPermission() {
		return !permissionGateway.hasPermission(player, Permissions.SAVE_GAME);
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
