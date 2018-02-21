package game;

import org.bukkit.Bukkit;

import main.MainPlugin;

public class GameLoopImpl implements GameLoop {

	private int taskId;
	private boolean running;
	private Game game;

	@Override
	public void start() {
		if (running)
			return;
		running = true;
		loop();
	}

	@Override
	public void stop() {
		if (!running)
			return;
		running = false;
		Bukkit.getScheduler().cancelTask(taskId);
	}

	private void loop() {
		taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(MainPlugin.getInstance(), new Runnable() {
			@Override
			public void run() {
				game.tick();
			}
		}, 0, 1);
	}

	@Override
	public void setGame(Game game) {
		this.game = game;
	}

}
