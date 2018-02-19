package usecases.PerformAction.BreakJoinSign;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import context.Context;
import usecases.PerformAction.BreakJoinSign.BreakJoinSign.BreakJoinSignRequest;
import usecases.PerformAction.BreakJoinSign.BreakJoinSign.BreakJoinSignResponse;

public class BreakJoinSignEventListener implements Listener {
	
	@EventHandler
	public void on(BlockBreakEvent e) {
		if (!(e.getBlock().getState() instanceof Sign))
			return;

		BreakJoinSignRequest request = createRequest(e);
		BreakJoinSign useCase = new BreakJoinSignUseCase();
		BreakJoinSignView view = new BreakJoinSignViewImpl(e.getPlayer().getUniqueId(), e);
		BreakJoinSignResponse presenter = new BreakJoinSignPresenter(view);
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPermissionGateway(Context.permissionGateway);
		useCase.setJoinSignGateway(Context.joinSignGateway);
		useCase.execute(request, presenter);
	}
	
	private BreakJoinSignRequest createRequest(BlockBreakEvent e) {
		Sign sign = (Sign) e.getBlock().getState();
		Location location = sign.getLocation();
		
		BreakJoinSignRequestModel requestModel = new BreakJoinSignRequestModel();
		requestModel.setX(location.getX());
		requestModel.setY(location.getY());
		requestModel.setZ(location.getZ());
		requestModel.setFirstLine(sign.getLine(0));
		requestModel.setSecondLine(sign.getLine(1));
		requestModel.setThirdLine(sign.getLine(2));
		requestModel.setFourthLine(sign.getLine(3));
		requestModel.setWorld(location.getWorld().getName());
		requestModel.setPlayer(e.getPlayer().getUniqueId());
		
		return requestModel;
	}

}
