package game.states;

import game.Game;
import game.countdown.CountDown;
import game.countdown.OnCountDownFinished;
import game.countdown.SecondsBasedCountDown;
import game.countdown.respawn.RespawnCountDownController;
import usecases.teleportplayerstoteamspawns.TeleportPlayersToTeamSpawnsController;

public class RespawnGameState extends AbstractGameState implements OnCountDownFinished {

	private int respawnTimeInSeconds;
	private CountDown respawnCountDown;
	private GameState gameState;
	
	public RespawnGameState(GameState gameState) {
		this.gameState = gameState;
		respawnTimeInSeconds = 3;
	}
	
	private void initializeCountDown(Game game) {
		RespawnCountDownController controller = new RespawnCountDownController();
		controller.setOnCountDownFinished(this);
		respawnCountDown = new SecondsBasedCountDown(game, respawnTimeInSeconds);
		respawnCountDown.setCountDownListener(controller);
	}
	
	@Override
	public void onTick(Game game) {
		respawnCountDown.tick();
	}
	
	@Override
	public void enterGameState(Game game) {
		initializeCountDown(game);
		new TeleportPlayersToTeamSpawnsController().onTeleportPlayersToTeamSpawns(game.getName());	
		respawnCountDown.start();
	}

	@Override
	public void leaveGameState(Game game) {
		game.getVillagerSpawner().spawnVillager();
	}

	@Override
	public void onCountDownFinished(Game game) {
		if (game.getGameState().getClass() != RespawnGameState.class)
			return;
		transitionToGameState(game, gameState);
	}

	@Override
	public String getName() {
		return "Respawn";
	}

}
