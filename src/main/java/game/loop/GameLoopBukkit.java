package game.loop;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import game.Game;

public class GameLoopBukkit implements GameLoop {

	private int taskId;
	private boolean running;
	private Game game;
	private JavaPlugin plugin;

	public GameLoopBukkit(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
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
		taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
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
