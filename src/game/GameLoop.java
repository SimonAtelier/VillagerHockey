package game;

import org.bukkit.Bukkit;

import main.MainPlugin;

public class GameLoop {

	private int taskId;
	private boolean running;
	private Game game;

	public GameLoop(Game game) {
		this.game = game;
	}

	public void start() {
		if (running)
			return;
		running = true;
		loop();
	}

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

}
