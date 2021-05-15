package game.loop;

import game.Game;

public interface GameLoop {

	void start();
	
	void stop();
	
	void setGame(Game game);
	
}
