package game.usecases.prepareplayerforlobby;

import java.util.UUID;

public interface PreparePlayerForLobby {

	void execute(UUID uniquePlayerId);
	
}
