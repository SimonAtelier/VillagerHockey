package usecases.api.listgames;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import game.Game;
import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;

public class ListGamesUseCase implements ListGames {

	private UUID player;
	private List<String> games;
	private GameGateway gameGateway;
	private PermissionGateway permissionGateway;
	
	@Override
	public void execute(UUID player, ListGamesResponse response) {
		setPlayer(player);
		
		if (noPermission()) {
			response.onNoPermission();
			return;
		}
		
		findGames();
		
		if (noGamesFound()) {
			response.onNoGamesToList();
			return;
		}
		
		response.present(createGameListItems());
	}
	
	private List<GameListItem> createGameListItems() {
		List<GameListItem> gameListItems = new ArrayList<GameListItem>();
		
		for (String gameName : games) {
			Game game = gameGateway.findGameByName(gameName);
			GameListItem gameListItem = new GameListItem();
			gameListItem.setPlayersCount(game.getPlayersCount());
			gameListItem.setMaxPlayers(game.getTeams().getMaximumAmountOfPlayers());
			gameListItem.setGameName(gameName);
			gameListItem.setGameState(game.getGameState().getName());
			gameListItems.add(gameListItem);
		}
		
		return gameListItems;
	}
	
	private boolean noGamesFound() {
		return games.isEmpty();
	}
	
	private void findGames() {
		games = gameGateway.findAllGameNames();
	}
	
	private boolean noPermission() {
		return !permissionGateway.hasPermission(player, Permissions.LIST_GAMES);
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
