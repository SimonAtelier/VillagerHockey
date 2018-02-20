package usecases.performaction.shootpuck;

import java.util.UUID;

import context.Context;
import view.MessageView;

public class ShootPuckViewImpl implements ShootPuckView {

	private UUID viewer;
	
	public ShootPuckViewImpl(UUID viewer) {
		this.viewer = viewer;
	}
	
	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}
	
	@Override
	public void displayShootPuck() {
		
	}

}
