package game.CountDown;

import org.bukkit.Bukkit;

import game.Game;
import main.MainPlugin;

public class SecondsBasedCountDown implements CountDown {

	private int taskId;
	private int timeToCountDownInSeconds;
	private int timeLeftInSeconds;
	private boolean running;
	private boolean paused;
	private MainPlugin plugin;
	private Game game;
	private CountDownListener listener;
	
	public SecondsBasedCountDown(MainPlugin plugin, Game game, int timeToCountDownInSeconds) {
		this.timeToCountDownInSeconds = timeToCountDownInSeconds;
		this.timeLeftInSeconds = timeToCountDownInSeconds;
		this.plugin = plugin;
		this.game = game;
	}
	
	private void fireCountDownOneSecond() {
		listener.onCountDownOneSecond(this, game.getName(), timeLeftInSeconds);
	}
	
	private void fireCountDownStart() {
		listener.onStart(game.getName(), timeLeftInSeconds);
	}
	
	private void fireCountDownStop() {
		listener.onStop(game.getName(), timeLeftInSeconds);
	}
	
	private void fireUpdateIgnoringPause() {
		listener.onUpdateIgnorePaused(this, game.getName(), timeLeftInSeconds);
	}
	
	@Override
	public void start() {
		if (running)
			return;
		running = true;
		timeLeftInSeconds = timeToCountDownInSeconds;
		fireCountDownStart();
		taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

			@Override
			public void run() {
				if (timeLeftInSeconds == 0) {
					stop();
				}

				if (running && !paused) {
					fireCountDownOneSecond();
					timeLeftInSeconds--;
				}
				
				fireUpdateIgnoringPause();
			}

		}, 0, 20 * 1);
	}

	@Override
	public void stop() {
		if (!running)
			return;
		fireCountDownStop();
		running = false;
		Bukkit.getScheduler().cancelTask(taskId);
	}

	@Override
	public void pause() {
		paused = true;
	}

	@Override
	public void resume() {
		paused = false;
	}

	@Override
	public void setTimeToCountDownInSeconds(int timeToCountDownInSeconds) {
		this.timeToCountDownInSeconds = timeToCountDownInSeconds;
	}

	@Override
	public void setCountDownListener(CountDownListener listener) {
		this.listener = listener;
	}

	@Override
	public boolean isFinished() {
		return timeLeftInSeconds <= 0;
	}

}
