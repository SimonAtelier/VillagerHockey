package game.CountDown.Respawn;

import java.util.List;
import java.util.UUID;

public interface RespawnCountDownView {

	void displayRespawnTime(List<UUID> viewers, int respawnTimeInSeconds);
	
	void displayRespawnTimeOver(List<UUID> viewers, int respawnTimeInSeconds);
	
}
