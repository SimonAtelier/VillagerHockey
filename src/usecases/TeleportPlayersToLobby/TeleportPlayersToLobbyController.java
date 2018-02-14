package usecases.TeleportPlayersToLobby;

import context.Context;
import usecases.TeleportPlayersToLobby.TeleportPlayersToLobby.TeleportPlayersToLobbyResponse;

public class TeleportPlayersToLobbyController {

	public void onTeleportPlayersToLobby(String game) {
		TeleportPlayersToLobbyView view = new TeleportPlayersToLobbyViewImpl();
		TeleportPlayersToLobbyResponse presenter = new TeleportPlayersToLobbyPresenter(view);
		TeleportPlayersToLobby useCase = new TeleportPlayersToLobbyUseCase();
		useCase.setGameGateway(Context.gameGateway);
		useCase.execute(game, presenter);
	}

}
