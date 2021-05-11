package usecases.api.teleportplayerstolobby;

import java.util.List;
import java.util.UUID;

import entities.Location;
import usecases.api.teleportplayerstolobby.TeleportPlayersToLobby.TeleportPlayersToLobbyResponse;

public class TeleportPlayersToLobbyPresenter implements TeleportPlayersToLobbyResponse {

	private TeleportPlayersToLobbyView view;

	public TeleportPlayersToLobbyPresenter(TeleportPlayersToLobbyView view) {
		this.view = view;
	}

	@Override
	public void presentLocation(List<UUID> players, Location location) {
		view.displayLocation(players, location);
	}

}
