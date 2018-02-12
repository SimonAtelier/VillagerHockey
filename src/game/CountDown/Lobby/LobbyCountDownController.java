package game.CountDown.Lobby;

import java.util.List;
import java.util.UUID;

import context.Context;
import game.CountDown.CountDownListener;
import game.States.RunningGameState;
import game.Game;
import game.CountDown.CountDown;
import main.MainPlugin;

public class LobbyCountDownController implements CountDownListener {

	private LobbyCountDownView view;

	public LobbyCountDownController() {
		view = new LobbyCountDownViewImpl();
	}
	
	@Override
	public void onUpdateIgnorePaused(CountDown countdown, String game, int timeLeftInSeconds) {
	}

	@Override
	public void onStart(String game, int timeLeftInSeconds) {
		List<UUID> players = getPlayers(game);
		view.displayGameStartsInGivenSeconds(players, timeLeftInSeconds);
	}

	@Override
	public void onStop(String game, int timeLeftInSeconds) {
		Game game1 = Context.gameGateway.findGameByName(game);
		List<UUID> players = getPlayers(game);

		game1.setGameState(new RunningGameState());
		game1.warmUp();
		
		view.displayGameStarts(players);
		view.displayCountDownTimeInSeconds(players, 0);
	}

	@Override
	public void onCountDownOneSecond(CountDown countDown, String game, int timeLeftInSeconds) {
		List<UUID> players = getPlayers(game);

		if (shouldDisplayMapTitle(timeLeftInSeconds)) {
			view.displayMapTitle(players, "VillagerHockey", game, 2);
		}

		if (timeLeftInSeconds == 2) {
			Game game1 = Context.gameGateway.findGameByName(game);
			game1.getVillagerSpawner().removeVillager();
		}

		if (shouldDisplayGameStartsInGivenSeconds(timeLeftInSeconds)) {
			view.displayGameStartsInGivenSeconds(players, timeLeftInSeconds);
		}

		view.displayCountDownTimeInSeconds(players, timeLeftInSeconds);
	}

	private List<UUID> getPlayers(String gameName) {
		Game game = Context.gameGateway.findGameByName(gameName);
		return game.getUniquePlayerIds();
	}

	private boolean shouldDisplayMapTitle(int timeLeftInSeconds) {
		return timeLeftInSeconds == 1 && MainPlugin.getInstance().getConfiguration().isMapTitleEnabled();
	}

	private boolean shouldDisplayGameStartsInGivenSeconds(int timeLeftInSeconds) {
		switch (timeLeftInSeconds) {
		case 60:
		case 30:
		case 15:
		case 10:
		case 5:
		case 3:
		case 2:
		case 1:
			return true;
		default:
			return false;
		}
	}

}
