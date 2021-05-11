package usecases.api.listgames;

import java.util.List;
import java.util.UUID;

import context.Context;
import minigame.view.MessageView;

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
	public void displayGamesList(List<GameListItem> games) {
		displayMessage(viewer, createListView(games));
	}
	
	private String createListView(List<GameListItem> games) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Games:");
		for (GameListItem listItem : games) {
			buffer.append("\n");
			buffer.append(listItem.getGameName());
			buffer.append(" : ");
			buffer.append(listItem.getGameState());
			buffer.append(" : (");
			buffer.append(listItem.getPlayersCount());
			buffer.append("/");
			buffer.append(listItem.getMaxPlayers());
			buffer.append(")");
		}
		return buffer.toString();
	}

}
