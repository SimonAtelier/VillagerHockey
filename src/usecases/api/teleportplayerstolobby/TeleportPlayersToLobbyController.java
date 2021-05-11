package usecases.api.teleportplayerstolobby;

import context.Context;
import usecases.api.teleportplayerstolobby.TeleportPlayersToLobby.TeleportPlayersToLobbyResponse;

public class TeleportPlayersToLobbyController {

	public void onTeleportPlayersToLobby(String game) {
		TeleportPlayersToLobbyView view = new TeleportPlayersToLobbyViewImpl();
		TeleportPlayersToLobbyResponse presenter = new TeleportPlayersToLobbyPresenter(view);
		TeleportPlayersToLobby useCase = new TeleportPlayersToLobbyUseCase();
		useCase.setGameGateway(Context.gameGateway);
		useCase.execute(game, presenter);
	}

}
