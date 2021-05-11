package usecases.api.selectteam;

import java.util.List;
import java.util.UUID;

import game.event.TeamSelectListener;
import usecases.showteams.ShowTeamsResponseItem;

public interface SelectTeamView {
	
	void setSlots(int slots);
	
	void displayTeams(List<ShowTeamsResponseItem> teams);
	
	void displayPlayerIsNotIngame();
	
	void displayAlreadyJoinedATeam();
	
	UUID getViewer();
	
	void setTeamSelectListener(TeamSelectListener listener);
	
}
