package usecases.performaction.shootpuck;

import java.util.UUID;

import usecases.performaction.shootpuck.ShootPuck.ShootPuckResponse;

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
