package usecases.spawnvillager;

import java.util.List;
import java.util.UUID;

import context.Context;
import usecases.spawnvillager.SpawnVillager.SpawnVillagerResponse;
import view.message.MessageView;
import view.title.TitleView;
import view.title.TitleViewImpl;
import view.title.TitleViewModel;
import view.title.TitleViewModelImpl;

public class SpawnVillagerPresenter implements SpawnVillagerResponse {

	@Override
	public void onSpecialRound(List<UUID> players) {
//		MessageView messageView = Context.messageView;
//		for (UUID viewer : players) {
//			messageView.displayMessage(viewer, "SPECIAL ROUND! PIG PINATA!");
//		}
		
		TitleView titleView = new TitleViewImpl();
		titleView.setTitleViewModel(createSpecialRoundTitleViewModel());
		
		for (UUID viewer : players)
			titleView.display(viewer);
	}
	
	private TitleViewModel createSpecialRoundTitleViewModel() {
		TitleViewModel titleViewModel = new TitleViewModelImpl();
		titleViewModel.setTitle("SPECIAL ROUND!");
		titleViewModel.setSubtitle("Funny Pig Pinata!");
		titleViewModel.setTitleFadeInTimeInSeconds(1);
		titleViewModel.setTitleFadeOutTimeInSeconds(1);
		titleViewModel.setTitleStayTimeInSeconds(2);
		return titleViewModel;
	}

}
