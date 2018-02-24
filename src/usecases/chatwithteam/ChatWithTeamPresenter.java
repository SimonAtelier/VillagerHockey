package usecases.chatwithteam;

import java.util.List;
import java.util.UUID;

import usecases.chatwithteam.ChatWithTeam.ChatWithTeamResponse;

public class ChatWithTeamPresenter implements ChatWithTeamResponse {

	private ChatWithTeamView view;
	
	public ChatWithTeamPresenter(ChatWithTeamView view) {
		this.view = view;
	}

	@Override
	public void onNoPermission() {
		view.displayNoPermission();
	}

	@Override
	public void onPlayerHasNoTeam() {
		view.displayNoTeam();
	}

	@Override
	public void onChatWithTeam(List<UUID> viewers, String message, String player) {
		view.displayChatWithTeam(viewers, message, player);
	}

}
