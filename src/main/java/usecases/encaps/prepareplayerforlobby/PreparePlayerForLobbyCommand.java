package usecases.encaps.prepareplayerforlobby;

import java.util.UUID;

import context.Context;
import usecases.encaps.prepareplayerforlobby.PreparePlayerForLobby.PreparePlayerForLobbyResponse;

public class PreparePlayerForLobbyCommand {

	public void execute(UUID uniquePlayerId) {
		LobbyView view = new LobbyViewImpl(uniquePlayerId);
		PreparePlayerForLobbyResponse presenter = new PreparePlayerForLobbyPresenter(view);
		PreparePlayerForLobby useCase = new PreparePlayerForLobbyUseCase();
		useCase.setConfiguration(Context.configuration);
		useCase.setPermissionGateway(Context.permissionGateway);
		useCase.setPlayerDataGateway(Context.playerDataGateway);
		useCase.execute(uniquePlayerId, presenter);
	}
	
}
