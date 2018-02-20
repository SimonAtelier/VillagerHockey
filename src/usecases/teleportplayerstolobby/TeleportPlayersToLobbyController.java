package usecases.teleportplayerstolobby;

import context.Context;
import usecases.teleportplayerstolobby.TeleportPlayersToLobby.TeleportPlayersToLobbyResponse;

public class TeleportPlayersToLobbyController {

	public void onTeleportPlayersToLobby(String game) {
		TeleportPlayersToLobbyView view = new TeleportPlayersToLobbyViewImpl();
		TeleportPlayersToLobbyResponse presenter = new TeleportPlayersToLobbyPresenter(view);
		TeleportPlayersToLobby useCase = new TeleportPlayersToLobbyUseCase();
		useCase.setGameGateway(Context.gameGateway);
		useCase.execute(game, presenter);
	}

}
