package usecases.LeaveTeam;

import java.util.List;
import java.util.UUID;

public interface LeaveTeamView {

	void displayLeftTeam(UUID viewer, String team);
	
	void displayLeftTeam(List<UUID> viewer, String player, String team);
	
}
