package game.countdown.lobby;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import context.Context;
import entities.config.MapTitleConfiguration;
import minigame.view.MessageView;
import view.title.TitleView;
import view.title.TitleViewImpl;
import view.title.TitleViewModel;

public class LobbyCountDownViewImpl implements LobbyCountDownView {

	private TitleView titleView;
	
	public LobbyCountDownViewImpl() {
		titleView = new TitleViewImpl();
	}
	
	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}
	
	@Override
	public void displayGameStartsInGivenSeconds(List<UUID> viewers, int timeToWaitInSeconds) {
		String message = LobbyCountDownViewMessages.LOBBY_COUNT_DOWN_TIME_TO_WAIT;
		message = message.replace("$seconds$", timeToWaitInSeconds + "");
		for (UUID viewer : viewers) {
			displayMessage(viewer, message);
		}
	}

	@Override
	public void displayGameStarts(List<UUID> viewers) {
		for (UUID viewer : viewers) {
			displayMessage(viewer, LobbyCountDownViewMessages.LOBBY_COUNT_DOWN_GAME_STARTS);
		}
	}

	@Override
	public void displayCountDownTimeInSeconds(List<UUID> viewers, int seconds) {
		for (UUID viewer : viewers) {
			Player player = Bukkit.getPlayer(viewer);
			player.setLevel(seconds);
		}
	}

	@Override
	public void displayMapTitle(List<UUID> viewers, String title, String subtitle) {
		if (!Context.configuration.isMapTitleEnabled())
			return;
		
		MapTitleConfiguration configuration = Context.configuration;
		
		TitleViewModel model = titleView.getTitleViewModel();
		model.setTitle(title);
		model.setSubtitle(subtitle);
		model.setFadeInTimeInSeconds(configuration.getMapTitleFadeInTimeInSeconds());
		model.setFadeOutTimeInSeconds(configuration.getMapTitleFadeOutTimeInSeconds());
		model.setStayTimeInSeconds(configuration.getMapTitleStayTimeInSeconds());

		for (UUID viewer : viewers) {
			titleView.display(viewer);
		}
	}

}
