package usecases.chatingame;

import java.util.List;
import java.util.UUID;

public interface ChatIngameView {

	void displayNoPermission();
	
	void displayNoTeam();
	
	void displayChatWithTeam(List<UUID> viewers, String message, String player);
	
	void displayChatWithAll(List<UUID> viewers, String message, String player);
	
}
