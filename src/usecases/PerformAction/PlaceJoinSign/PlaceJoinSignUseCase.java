package usecases.PerformAction.PlaceJoinSign;

import entities.Location;
import game.Game;
import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;

public class PlaceJoinSignUseCase implements PlaceJoinSign {

	private GameGateway gameGateway;
	private PermissionGateway permissionGateway;
	private PlaceJoinSignRequest request;

	@Override
	public void execute(PlaceJoinSignRequest request, PlaceJoinSignResponse response) {
		this.request = request;

		if (noPermission()) {
			response.onNoPermission();
			return;
		}

		if (noSuchGame()) {
			response.onNoSuchGame(request.getGame());
			return;
		}

		Game game = gameGateway.findGameByName(request.getGame());
		game.getJoinSigns().addLocation(createLocationFromRequest());
		response.onJoinSignSuccessfullySet(request.getGame(), createResponseModel(game));
	}

	private ResponseModel createResponseModel(Game game) {
		ResponseModel responseModel = new ResponseModel();
		responseModel.setLine0("[vh]");
		responseModel.setLine1(game.getName());
		responseModel.setLine2(game.getGameState().toString());
		responseModel.setLine3(game.getPlayersCount() + "/" + game.getTeams().getMaximumAmountOfPlayers());
		return responseModel;
	}

	private Location createLocationFromRequest() {
		Location location = new Location();
		location.setX(request.getX());
		location.setY(request.getY());
		location.setZ(request.getZ());
		location.setWorld(request.getWorld());
		return location;
	}

	private boolean noPermission() {
		return !permissionGateway.hasPermission(request.getPlayer(), Permissions.PLACE_JOIN_SIGN);
	}

	private boolean noSuchGame() {
		return gameGateway.findGameByName(request.getGame()) == null;
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
