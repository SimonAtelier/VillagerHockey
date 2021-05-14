package game.states.hockey;

import game.Game;
import game.countdown.CountDown;
import game.countdown.OnCountDownFinished;
import game.countdown.SecondsBasedCountDown;
import game.countdown.respawn.RespawnCountDownController;
import game.states.base.AbstractGameState;
import game.states.base.GameState;
import usecases.encaps.teleportplayerstoteamspawns.TeleportPlayersToTeamSpawnsController;
import usecases.hockey.spawnvillager.SpawnVillagerController;

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
	public void onTick() {
		respawnCountDown.tick();
		new TeleportPlayersToTeamSpawnsController().onTeleportPlayersToTeamSpawns(getGame().getName());
	}
	
	@Override
	public void enterGameState() {
		initializeCountDown(getGame());
		respawnCountDown.start();	
	}

	@Override
	public void leaveGameState() {
		new SpawnVillagerController().onSpawnVillager(getGame().getName());
	}

	@Override
	public void onCountDownFinished(Game game) {
		if (game.getGameState().getClass() != RespawnGameState.class)
			return;
		if (specialRound()) {
			transitionToGameState(createRandomSpecialRound());
		} else {
			transitionToGameState(gameState);
		}
	}
	
	private boolean specialRound() {
		return ((int) (Math.random() * 6)) == 5;
	}
	
	private GameState createRandomSpecialRound() {
		int random = (int) (Math.random() * 3);
		switch (random) {
		case 0:
			return new PoloState(gameState);
		case 1:
			return new BoatBoogieState(gameState);
		case 2:
			return new PinataState(gameState);
		default:
			return new PoloState(gameState);
		}
	}

	@Override
	public String getName() {
		return "Respawn";
	}

}
