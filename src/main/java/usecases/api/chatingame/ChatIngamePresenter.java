package usecases.api.chatingame;

import java.util.List;
import java.util.UUID;

import usecases.api.chatingame.ChatIngame.ChatIngameResponse;

public class ChatIngamePresenter implements ChatIngameResponse {

	private ChatIngameView view;
	
	public ChatIngamePresenter(ChatIngameView view) {
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

	@Override
	public void onChatWithAll(List<UUID> viewers, String message, String player) {
		view.displayChatWithAll(viewers, message, player);
	}

}
