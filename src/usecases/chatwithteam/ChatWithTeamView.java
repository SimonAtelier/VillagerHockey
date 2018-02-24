package usecases.chatwithteam;

import java.util.List;
import java.util.UUID;

public interface ChatWithTeamView {

	void displayNoPermission();
	
	void displayNoTeam();
	
	void displayChatWithTeam(List<UUID> viewers, String message, String player);
	
}
