package usecases.PerformAction.ShootPuck;

import java.util.UUID;

import usecases.PerformAction.ShootPuck.ShootPuck.ShootPuckResponse;

public class ShootPuckPresenter implements ShootPuckResponse {

	private ShootPuckView view;
	
	public ShootPuckPresenter(ShootPuckView view) {
		this.view = view;
	}
	
	@Override
	public void presentShootPuck(UUID player) {
		view.displayShootPuck();
	}

}
