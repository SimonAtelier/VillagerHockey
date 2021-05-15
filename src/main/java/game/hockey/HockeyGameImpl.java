package game.hockey;

import game.BaseGame;

public class HockeyGameImpl extends BaseGame {

	public HockeyGameImpl(String name) {
		super(name);
		setGameCycle(new HockeyGameCycle(this));
	}	

}
