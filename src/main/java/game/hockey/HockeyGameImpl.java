package game.hockey;

import game.AbstractGame;

public class HockeyGameImpl extends AbstractGame {

	public HockeyGameImpl(String name) {
		super(name);
		setGameCycle(new HockeyGameCycle(this));
	}	

}
