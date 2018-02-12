package game.UseCases.RemoveVillagers;

import gateways.GameGateway;

public interface RemoveVillagers {

	void execute(String game);
	
	void setGameGateway(GameGateway gameGateway);
	
}
