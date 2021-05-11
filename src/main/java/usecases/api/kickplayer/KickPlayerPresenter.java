package usecases.api.kickplayer;

import java.util.List;
import java.util.UUID;

import usecases.api.kickplayer.KickPlayer.KickPlayerResponse;

public class KickPlayerPresenter implements KickPlayerResponse {

	private KickPlayerView view;
	
	public KickPlayerPresenter(KickPlayerView view) {
		this.view = view;
	}
	
	@Override
	public void onPlayerWithSuchNameNotFound(String name) {
		view.displayPlayerWithSuchNameNotFound(name);
	}

	@Override
	public void onPlayerIsNotIngame() {
		view.displayPlayerIsNotIngame();
	}

	@Override
	public void onPlayerSuccessfullyKicked() {
		view.displayPlayerSucccessfullyKicked();
	}
	
	@Override
	public void onNoPermission() {
		view.displayNoPermission();
	}

	@Override
	public void presentKickMessage(UUID receiver, String message) {
		view.displayKickMessage(receiver, message);
	}

	@Override
	public void presentPlayerKicked(List<UUID> players, String kickedPlayer) {
		view.displayPlayerKicked(players, kickedPlayer);
	}
	
}
