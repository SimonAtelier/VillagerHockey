package usecases.performaction.placejoinsign;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import context.Context;
import usecases.performaction.placejoinsign.PlaceJoinSign.PlaceJoinSignRequest;
import usecases.performaction.placejoinsign.PlaceJoinSign.PlaceJoinSignResponse;

public class PlaceJoinSignEventListener implements Listener {
	
	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		String firstLine = e.getLine(0);
		
		if (!firstLine.trim().equals("vh"))
			return;
		
		PlaceJoinSignRequest request = createRequest(e);
		PlaceJoinSign useCase = new PlaceJoinSignUseCase();
		PlaceJoinSignView view = new PlaceJoinSignViewImpl(e.getPlayer().getUniqueId(), e);
		PlaceJoinSignResponse presenter = new PlaceJoinSignPresenter(view);
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPermissionGateway(Context.permissionGateway);
		useCase.setJoinSignGateway(Context.joinSignGateway);
		useCase.execute(request, presenter);
	}
	
	private PlaceJoinSignRequest createRequest(SignChangeEvent e) {
		Location location = e.getBlock().getState().getLocation();
		PlaceJoinSignRequestModel requestModel = new PlaceJoinSignRequestModel();
		requestModel.setPlayer(e.getPlayer().getUniqueId());
		requestModel.setX(location.getX());
		requestModel.setY(location.getY());
		requestModel.setZ(location.getZ());
		requestModel.setWorld(location.getWorld().getName());
		requestModel.setGame(e.getLine(1));
		return requestModel;
	}
	
}
