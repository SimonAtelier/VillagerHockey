package game.event;

import java.util.UUID;

public interface TeamSelectListener {
	
	void onTeamSelected(UUID player, String game, String team);
	
}
