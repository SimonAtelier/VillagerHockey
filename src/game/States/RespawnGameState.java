package game.States;

import game.Game;
import game.CountDown.CountDown;
import game.CountDown.OnCountDownFinished;
import game.CountDown.SecondsBasedCountDown;
import game.CountDown.Respawn.RespawnCountDownController;
import main.MainPlugin;
import usecases.TeleportPlayersToTeamSpawns.TeleportPlayersToTeamSpawnsController;

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
		respawnCountDown = new SecondsBasedCountDown(MainPlugin.getInstance(), game, respawnTimeInSeconds);
		respawnCountDown.setCountDownListener(controller);
	}
	
	@Override
	public void enterGameState(Game game) {
		super.enterGameState(game);
		initializeCountDown(game);
		game.setCanMove(false);
		new TeleportPlayersToTeamSpawnsController().onTeleportPlayersToTeamSpawns(game.getName());	
		respawnCountDown.start();
	}

	@Override
	public void leaveGameState(Game game) {
		super.leaveGameState(game);
		game.getVillagerSpawner().spawnVillager();
		game.setCanMove(true);
	}

	@Override
	public void onCountDownFinished(Game game) {
		transitionToGameState(game, gameState);
	}

	@Override
	public String getName() {
		return "Respawn";
	}

}
