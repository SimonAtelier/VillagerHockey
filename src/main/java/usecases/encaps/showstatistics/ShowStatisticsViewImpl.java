package usecases.encaps.showstatistics;

import java.util.UUID;

import context.Context;
import view.message.MessageView;

public class ShowStatisticsViewImpl implements ShowStatisticsView {
	
	private UUID viewer;

	public ShowStatisticsViewImpl(UUID viewer) {
		this.viewer = viewer;
	}
	
	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}

	@Override
	public void displayNoPermission() {
		displayMessage(viewer, ShowStatisticsViewMessages.SHOW_STATISTICS_NO_PERMISSION);
	}

	@Override
	public void displayStatistics(ShowStatisticsResponseModel statistics) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Statistiken von ");
		buffer.append(statistics.getPlayer());
		buffer.append("\n");
		buffer.append("Gespielte Spiele: ");
		buffer.append(statistics.getGamesPlayed());
		buffer.append("\n");
		buffer.append("Gewonnene Spiele: ");
		buffer.append(statistics.getGamesWon());
		buffer.append("\n");
		buffer.append("Verlorene Spiele: ");
		buffer.append(statistics.getGamesLost());
		buffer.append("\n");
		buffer.append("Unentschiedene Spiele: ");
		buffer.append(statistics.getGamesDraw());
		displayMessage(viewer, buffer.toString());
	}

}
