package usecases.KickPlayer;

import java.util.UUID;

import usecases.KickPlayer.KickPlayer.KickPlayerRequest;

public class KickPlayerRequestModel implements KickPlayerRequest {
	
	private String playerToKick;
	private String kickMessage;
	private UUID kicker;
	
	public String getPlayerToKick() {
		return playerToKick;
	}
	
	public void setPlayerToKick(String playerToKick) {
		this.playerToKick = playerToKick;
	}
	
	public String getKickMessage() {
		return kickMessage;
	}
	
	public void setKickMessage(String kickMessage) {
		this.kickMessage = kickMessage;
	}

	public UUID getKicker() {
		return kicker;
	}

	public void setKicker(UUID kicker) {
		this.kicker = kicker;
	}

}
