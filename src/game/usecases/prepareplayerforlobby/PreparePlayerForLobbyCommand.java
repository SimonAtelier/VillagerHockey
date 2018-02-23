package game.usecases.prepareplayerforlobby;

import java.util.UUID;

import context.Context;
import game.usecases.prepareplayerforlobby.PreparePlayerForLobby.PreparePlayerForLobbyResponse;

public class PreparePlayerForLobbyCommand {

	public void execute(UUID uniquePlayerId) {
		LobbyMenuView view = new LobbyMenuViewImpl(uniquePlayerId);
		PreparePlayerForLobbyResponse presenter = new PreparePlayerForLobbyPresenter(view);
		PreparePlayerForLobby useCase = new PreparePlayerForLobbyUseCase();
		useCase.setConfiguration(Context.configuration);
		useCase.setPermissionGateway(Context.permissionGateway);
		useCase.execute(uniquePlayerId, presenter);
	}
	
}
