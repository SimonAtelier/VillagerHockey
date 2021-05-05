package usecases.destroypinata;

import java.util.UUID;

import context.Context;
import usecases.destroypinata.DestroyPinata.DestroyPinataResponse;
import view.message.MessageView;
import view.title.TitleView;
import view.title.TitleViewImpl;
import view.title.TitleViewModel;
import view.title.TitleViewModelImpl;

public class DestroyPinataPresenter implements DestroyPinataResponse {

	@Override
	public void onDestroyedPinata(DestroyPinataResponseModel responseModel) {
		broadcastPinataDestroyedMessage(responseModel);
		broadcastPinataDestroyedTitle(responseModel);
	}
	
	private void broadcastPinataDestroyedMessage(DestroyPinataResponseModel responseModel) {
		MessageView messageView = Context.messageView;
		String message = DestroyPinataMessages.DETROY_PINATA_PLAYER_DESTROYED_PINATA;
		message = message.replace("$name$", responseModel.getDestroyerName());
		message = message.replace("$team$", responseModel.getDestroyerTeamName());
		
		for (UUID player : responseModel.getPlayers())
			messageView.displayMessage(player, message);
	}
	
	private void broadcastPinataDestroyedTitle(DestroyPinataResponseModel responseModel) {
		TitleView titleView = new TitleViewImpl();
		TitleViewModel titleViewModel = new TitleViewModelImpl();
		titleViewModel.setTitle("§cPINATA ZERSCHLAGEN!");
		titleViewModel.setSubtitle(responseModel.getPoints() + " Punkte für Team " + responseModel.getDestroyerTeamName() + "!");
		titleViewModel.setFadeInTimeInSeconds(1);
		titleViewModel.setFadeOutTimeInSeconds(1);
		titleViewModel.setStayTimeInSeconds(2);
		titleView.setTitleViewModel(titleViewModel);
		
		for (UUID player : responseModel.getPlayers())
			titleView.display(player);
	}

}
