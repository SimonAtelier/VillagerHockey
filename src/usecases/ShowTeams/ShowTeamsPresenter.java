package usecases.ShowTeams;

import java.util.List;

import usecases.SelectTeam.SelectTeamView;
import usecases.ShowTeams.ShowTeams.ShowTeamsResponse;

public class ShowTeamsPresenter implements ShowTeamsResponse {

	private SelectTeamView view;
	
	public ShowTeamsPresenter(SelectTeamView view) {
		this.view = view;
	}
	
	private int getSlots(List<ShowTeamsResponseItem> teams) {
		int size = teams.size();
		return ((size / 9) + (size % 9 == 0 ? 0 : 1)) * 9;
	}

	@Override
	public void presentTeams(List<ShowTeamsResponseItem> teams) {
		view.setSlots(getSlots(teams));
		view.displayTeams(teams);
	}

	@Override
	public void onPlayerIsNotIngame() {
		view.displayPlayerIsNotIngame();
	}
	
}
