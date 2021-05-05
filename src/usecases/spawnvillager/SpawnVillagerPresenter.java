package usecases.spawnvillager;

import java.util.List;
import java.util.UUID;

import usecases.spawnvillager.SpawnVillager.SpawnVillagerResponse;
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
		titleViewModel.setTitle(SpawnVillagerViewMessages.SPAWN_VILLAGER_SPECIAL_ROUND_TITLE);
		titleViewModel.setSubtitle(SpawnVillagerViewMessages.SPAWN_VILLAGER_SPECIAL_ROUND_SUBTITLE);
		titleViewModel.setFadeInTimeInSeconds(1);
		titleViewModel.setFadeOutTimeInSeconds(1);
		titleViewModel.setStayTimeInSeconds(2);
		return titleViewModel;
	}

}
