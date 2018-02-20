package usecases.kickplayer;

import java.util.List;
import java.util.UUID;

import game.Game;
import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;
import gateways.PlayerGateway;

public class KickPlayerUseCase implements KickPlayer {

	private UUID playerToKick;
	private Game gameOfPlayerToKick;
	private KickPlayerRequest request;
	private KickPlayerResponse response;
	private GameGateway gameGateway;
	private PermissionGateway permissionGateway;
	private PlayerGateway playerGateway;

	@Override
	public void execute(KickPlayerRequest request, KickPlayerResponse response) {
		setRequest(request);
		setResponse(response);

		if (noPermission()) {
			response.onNoPermission();
			return;
		}

		findPlayerToKick();

		if (noSuchPlayer()) {
			response.onPlayerWithSuchNameNotFound(request.getPlayerToKick());
			return;
		}

		if (notIngame()) {
			response.onPlayerIsNotIngame();
			return;
		}

		findGameOfPlayerToKick();
		kickPlayer();
		notifyPlayers();

		response.presentKickMessage(playerToKick, request.getKickMessage());
		response.onPlayerSuccessfullyKicked();
	}

	private void findPlayerToKick() {
		playerToKick = playerGateway.findPlayerUniqueIdByName(request.getPlayerToKick());
	}

	private void findGameOfPlayerToKick() {
		gameOfPlayerToKick = gameGateway.findGameOfPlayer(playerToKick);
	}

	private void notifyPlayers() {
		List<UUID> players = gameOfPlayerToKick.getUniquePlayerIds();
		response.presentPlayerKicked(players, request.getPlayerToKick());
	}

	private void kickPlayer() {;
		Game game = gameGateway.findGameOfPlayer(playerToKick);
		game.leave(playerToKick);
	}

	private boolean notIngame() {
		return !gameGateway.isIngame(playerToKick);
	}

	private boolean noSuchPlayer() {
		return playerToKick == null;
	}

	private boolean noPermission() {
		return !permissionGateway.hasPermission(request.getKicker(), Permissions.KICK_PLAYER);
	}

	private void setRequest(KickPlayerRequest request) {
		this.request = request;
	}

	private void setResponse(KickPlayerResponse response) {
		this.response = response;
	}

	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

	@Override
	public void setPermissionGateway(PermissionGateway permissionGateway) {
		this.permissionGateway = permissionGateway;
	}

	@Override
	public void setPlayerGateway(PlayerGateway playerGateway) {
		this.playerGateway = playerGateway;
	}

}
