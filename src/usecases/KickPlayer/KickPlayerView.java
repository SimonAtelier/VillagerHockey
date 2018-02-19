package usecases.KickPlayer;

import java.util.List;
import java.util.UUID;

public interface KickPlayerView {

	void displayPlayerWithSuchNameNotFound(String name);
	
	void displayPlayerIsNotIngame();
	
	void displayPlayerSucccessfullyKicked();
	
	void displayNoPermission();
	
	void displayKickMessage(UUID kickedPlayer, String reason);
	
	void displayPlayerKicked(List<UUID> viewers, String player);	
	
}
