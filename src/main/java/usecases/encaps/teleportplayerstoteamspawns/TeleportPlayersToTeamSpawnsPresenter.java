package usecases.encaps.teleportplayerstoteamspawns;

import java.util.List;
import java.util.UUID;

import entities.Location;
import usecases.encaps.teleportplayerstoteamspawns.TeleportPlayersToTeamSpawns.TeleportPlayersToTeamSpawnsResponse;

public class TeleportPlayersToTeamSpawnsPresenter implements TeleportPlayersToTeamSpawnsResponse {

	private TeleportPlayersToTeamSpawnsView view;
	
	public TeleportPlayersToTeamSpawnsPresenter(TeleportPlayersToTeamSpawnsView view) {
		this.view = view;
	}

	@Override
	public void presentLocation(List<UUID> players, List<Location> locations) {
		view.displayLocation(players, locations);
	}

}
