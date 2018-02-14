package usecases.SelectTeam;

import java.util.List;
import java.util.UUID;

import game.Event.TeamSelectListener;
import usecases.ShowTeams.ShowTeamsResponseItem;

public interface SelectTeamView {
	
	void setSlots(int slots);
	
	void displayTeams(List<ShowTeamsResponseItem> teams);
	
	void displayPlayerIsNotIngame();
	
	void displayAlreadyJoinedATeam();
	
	UUID getViewer();
	
	void setTeamSelectListener(TeamSelectListener listener);
	
}
