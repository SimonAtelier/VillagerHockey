package usecases.api.chatingame;

import java.util.List;
import java.util.UUID;

import context.Context;
import view.message.MessageView;

public class ChatIngameViewImpl implements ChatIngameView {

	private UUID viewer;
	
	public ChatIngameViewImpl(UUID viewer) {
		this.viewer = viewer;
	}
	
	private void displayConsoleMessage(String message) {
		MessageView messageView = Context.messageView;
		messageView.displayConsoleMessage(message);
	}
	
	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}
	
	@Override
	public void displayNoPermission() {
		displayMessage(viewer, ChatIngameViewMessages.CHAT_INGAME_NO_PERMISSION);
	}

	@Override
	public void displayNoTeam() {
		String message = ChatIngameViewMessages.CHAT_INGAME_NO_TEAM;
		message = message.replace("$label$", Context.configuration.getChatWithAllLabel());
		displayMessage(viewer, message);
	}

	@Override
	public void displayChatWithTeam(List<UUID> viewers, String message, String player) {
		String formatedMessage = ChatIngameViewMessages.CHAT_INGAME_TEAM_CHAT_MESSAGE_FORMAT;
		formatedMessage = formatedMessage.replace("$message$", message);
		formatedMessage = formatedMessage.replace("$player$", player);
		for (UUID viewer : viewers) {
			displayMessage(viewer, formatedMessage);
		}
		displayConsoleMessage(formatedMessage);
	}

	@Override
	public void displayChatWithAll(List<UUID> viewers, String message, String player) {
		String formatedMessage = ChatIngameViewMessages.CHAT_INGAME_ALL_CHAT_MESSAGE_FORMAT;
		formatedMessage = formatedMessage.replace("$message$", message);
		formatedMessage = formatedMessage.replace("$player$", player);
		for (UUID viewer : viewers) {
			displayMessage(viewer, formatedMessage);
		}
		displayConsoleMessage(formatedMessage);
	}

}
