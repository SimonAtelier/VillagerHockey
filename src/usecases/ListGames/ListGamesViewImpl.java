package usecases.ListGames;

import java.util.List;
import java.util.UUID;

import context.Context;
import view.MessageView;

public class ListGamesViewImpl implements ListGamesView {

	private UUID viewer;
	
	public ListGamesViewImpl(UUID viewer) {
		this.viewer = viewer;
	}

	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}
	
	@Override
	public void displayNoGamesToList() {
		displayMessage(viewer, ListGamesViewMessages.LIST_GAMES_NO_GAMES_TO_LIST);
	}
	
	@Override
	public void displayNoPermission() {
		displayMessage(viewer, ListGamesViewMessages.LIST_GAMES_NO_PERMISSION);
	}

	@Override
	public void displayGamesList(List<String> games) {
		displayMessage(viewer, createListView(games));
	}
	
	private String createListView(List<String> games) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Games:");
		for (String game : games) {
			buffer.append("\n");
			buffer.append(game);
		}
		return buffer.toString();
	}

}
