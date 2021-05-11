package view.hockeysticks;

import java.util.UUID;

public interface HockeySticksView {

	void displayHockeySticks(UUID uniquePlayerId);
	
	void hideHockeySticks(UUID uniquePlayerId);
	
}
