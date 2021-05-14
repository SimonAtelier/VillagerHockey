package game.event;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import game.Game;
import game.states.base.GameState;

public class GameChangeSupport {

	private Game game;
	private List<GameStateChangeListener> gameStateChangeListeners;
	private List<JoinListener> joinListeners;
	private List<LeaveListener> leaveListeners;
	private List<TeamSelectListener> teamSelectListeners;
	private List<TeamScoreListener> teamScoreListeners;

	public GameChangeSupport(Game game) {
		this.game = game;
		gameStateChangeListeners = new ArrayList<GameStateChangeListener>();
		joinListeners = new ArrayList<JoinListener>();
		leaveListeners = new ArrayList<LeaveListener>();
		teamSelectListeners = new ArrayList<TeamSelectListener>();
		teamScoreListeners = new ArrayList<TeamScoreListener>();
	}

	public void addGameStateChangeListener(GameStateChangeListener listener) {
		if (listener == null)
			return;
		gameStateChangeListeners.add(listener);
	}

	public void removeGameStateChangeListener(GameStateChangeListener listener) {
		gameStateChangeListeners.remove(listener);
	}

	public void addJoinListener(JoinListener listener) {
		if (listener == null)
			return;
		joinListeners.add(listener);
	}

	public void removeJoinListener(JoinListener listener) {
		joinListeners.remove(listener);
	}

	public void addLeaveListener(LeaveListener listener) {
		if (listener == null)
			return;
		leaveListeners.add(listener);
	}

	public void removeLeaveListener(LeaveListener listener) {
		leaveListeners.remove(listener);
	}

	public void addTeamSelectListener(TeamSelectListener listener) {
		if (listener == null)
			return;
		teamSelectListeners.add(listener);
	}

	public void removeTeamSelectListener(TeamSelectListener listener) {
		teamSelectListeners.remove(listener);
	}

	public void addTeamScoreListener(TeamScoreListener listener) {
		if (listener == null)
			return;
		teamScoreListeners.add(listener);
	}

	public void removeTeamScoreListener(TeamScoreListener listener) {
		teamScoreListeners.remove(listener);
	}

	public void fireGameStateChanged(GameState from, GameState to) {
		for (GameStateChangeListener listener : gameStateChangeListeners) {
			listener.onGameStateChanged(game, from, to);
		}
	}

	public void firePlayerJoin(UUID player) {
		for (JoinListener listener : joinListeners) {
			listener.onPlayerJoin(game, player);
		}
	}

	public void firePlayerLeave(UUID player) {
		for (LeaveListener listener : leaveListeners) {
			listener.onPlayerLeave(game, player);
		}
	}

	public void fireTeamSelected(UUID player, String team) {
		for (TeamSelectListener listener : teamSelectListeners) {
			listener.onTeamSelected(player, game.getName(), team);
		}
	}
	
	public void fireTeamScored(String team) {
		for (TeamScoreListener listener : teamScoreListeners) {
			listener.onTeamScored(game.getName(), team);
		}
	}

}
