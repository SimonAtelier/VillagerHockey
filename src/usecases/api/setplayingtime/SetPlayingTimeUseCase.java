package usecases.api.setplayingtime;

import game.Game;
import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;

public class SetPlayingTimeUseCase implements SetPlayingTime {

	private SetPlayingTimeRequest request;
	private Integer parsedPlayingTime;
	private GameGateway gameGateway;
	private PermissionGateway permissionGateway;

	@Override
	public void execute(SetPlayingTimeRequest request, SetPlayingTimeResponse response) {
		setRequest(request);
		
		if (noPermission()) {
			response.onNoPermission();
			return;
		}
		
		if (noSuchGame()) {
			response.onNoSuchGame(request.getGame());
			return;
		}

		parsePlayingTime();

		if (playingTimeNotValid()) {
			response.onPlayingTimeIsNotAValidNumber(request.getPlayingTime());
			return;
		}
		
		applyParsedPlayingTimeToGame();
		response.onPlayingTimeSuccessfullySet(request.getGame(), request.getPlayingTime());
	}
	
	private void parsePlayingTime() {
		try {
			parsedPlayingTime = Integer.parseInt(request.getPlayingTime());
		} catch (NumberFormatException e) {
			parsedPlayingTime = null;
		}
	}
	
	private void applyParsedPlayingTimeToGame() {
		Game game = gameGateway.findGameByName(this.request.getGame());
		game.setPlayingTimeInSeconds(parsedPlayingTime);
	}
	
	private boolean noPermission() {
		return !permissionGateway.hasPermission(request.getPlayer(), Permissions.SET_PLAYING_TIME);
	}
	
	private boolean playingTimeNotValid() {
		return parsedPlayingTime == null;
	}

	private boolean noSuchGame() {
		return !gameGateway.containsGame(request.getGame());
	}

	private void setRequest(SetPlayingTimeRequest request) {
		this.request = request;
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
