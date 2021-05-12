package usecases.hockey.destroypinata;

import java.util.UUID;

import context.Context;
import minigame.view.MessageView;
import minigame.view.TitleView;
import minigame.view.TitleViewModel;
import usecases.hockey.destroypinata.DestroyPinata.DestroyPinataResponse;

public class DestroyPinataPresenter implements DestroyPinataResponse {

	private DestroyPinataResponseModel responseModel;
	
	@Override
	public void onDestroyedPinata(DestroyPinataResponseModel responseModel) {
		setResponseModel(responseModel);
		broadcastPinataDestroyedMessage();
		broadcastPinataDestroyedTitle();
	}
	
	private void broadcastPinataDestroyedMessage() {
		MessageView messageView = Context.messageView;
		String message = createPinataDestroyedMessage();
		
		for (UUID player : responseModel.getPlayers())
			messageView.displayMessage(player, message);
	}
	
	private void broadcastPinataDestroyedTitle() {
		TitleView titleView = Context.viewFactory.createTitleView();
		titleView.setTitleViewModel(createTitleViewModel());
		
		for (UUID player : getResponseModel().getPlayers())
			titleView.display(player);
	}
	
	private TitleViewModel createTitleViewModel() {
		TitleViewModel titleViewModel = Context.viewFactory.createTitleViewModel();
		titleViewModel.setTitle(DestroyPinataMessages.DESTROY_PINATA_TITLE);
		titleViewModel.setSubtitle(createSubtitle());
		titleViewModel.setFadeInTimeInSeconds(1);
		titleViewModel.setStayTimeInSeconds(2);
		titleViewModel.setFadeOutTimeInSeconds(1);
		return titleViewModel;
	}
	
	private String createPinataDestroyedMessage() {
		String message = DestroyPinataMessages.DETROY_PINATA_PLAYER_DESTROYED_PINATA;
		message = message.replace("$name$", getResponseModel().getDestroyerName());
		message = message.replace("$team$", getResponseModel().getDestroyerTeamName());
		return message;
	}
	
	private String createSubtitle() {
		String subtitle = DestroyPinataMessages.DESTROY_PINATA_SUBTITLE;
		subtitle = subtitle.replace("$points$", getResponseModel().getPoints() + "");
		subtitle = subtitle.replace("$team$", getResponseModel().getDestroyerTeamName());
		return subtitle;
	}
	
	private DestroyPinataResponseModel getResponseModel() {
		return responseModel;
	}
	
	private void setResponseModel(DestroyPinataResponseModel responseModel) {
		this.responseModel = responseModel;
	}

}
