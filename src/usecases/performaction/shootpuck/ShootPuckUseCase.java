package usecases.performaction.shootpuck;

import gateways.GameGateway;
import gateways.PlayerGateway;

public class ShootPuckUseCase implements ShootPuck {

	private GameGateway gameGateway;
	private PlayerGateway playerGateway;
	
	@Override
	public void execute(ShootPuckRequest request, ShootPuckResponse response) {
		response.presentShootPuck(request.getPlayer());
	}

	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

	@Override
	public void setPlayerGateway(PlayerGateway playerGateway) {
		this.playerGateway = playerGateway;
	}

}
