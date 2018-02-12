package usecases.JoinTeam;

import java.util.List;
import java.util.UUID;

public interface JoinTeamView {

	void displayNoPermission();
	
	void displayNowInTeam(UUID player, String team);
		
	void displayJoin(List<UUID> players, String player, String team);
	
	void displayNoSuchGame(String game);
	
	void displayNoSuchTeam(String team);
	
	void displayTeamIsAlreadyFull(String team);
	
	void displayAlreadyJoinedATeam();
		
}
