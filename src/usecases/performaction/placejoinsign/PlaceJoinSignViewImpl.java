package usecases.performaction.placejoinsign;

import java.util.UUID;

import org.bukkit.event.block.SignChangeEvent;

import context.Context;
import view.message.MessageView;

public class PlaceJoinSignViewImpl implements PlaceJoinSignView {
	
	private UUID viewer;
	private SignChangeEvent event;
	
	public PlaceJoinSignViewImpl(UUID viewer, SignChangeEvent event) {
		this.viewer = viewer;
		this.event = event;
	}
	
	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}

	@Override
	public void displayJoinSignSuccessfullySet(String game, ResponseModel responseModel) {
		String message = PlaceJoinSignViewMessages.PLACE_JOIN_SIGN_SUCCESS;
		message = message.replace("$game$", game);
		
		event.setLine(0, responseModel.getLine0());
		event.setLine(1, responseModel.getLine1());
		event.setLine(2, responseModel.getLine2());
		event.setLine(3, responseModel.getLine3());
		
		displayMessage(viewer, message);
	}

	@Override
	public void displayNoPermission() {
		displayMessage(viewer, PlaceJoinSignViewMessages.PLACE_JOIN_SIGN_NO_PERMISSION);
	}

	@Override
	public void displayNoSuchGame(String game) {
		String message = PlaceJoinSignViewMessages.PLACE_JOIN_SIGN_NO_SUCH_GAME;
		message = message.replace("$game$", game);
		displayMessage(viewer, message);
	}

 }
