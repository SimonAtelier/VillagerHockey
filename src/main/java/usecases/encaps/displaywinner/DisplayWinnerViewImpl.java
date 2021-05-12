package usecases.encaps.displaywinner;

import java.util.List;
import java.util.UUID;

import context.Context;
import minigame.view.TitleView;
import minigame.view.TitleViewModel;

public class DisplayWinnerViewImpl implements DisplayWinnerView {

	private TitleView titleView;
	
	public DisplayWinnerViewImpl() {
		titleView = Context.viewFactory.createTitleView();
	}
	
	@Override
	public void displayTitle(TitleViewModel titleViewModel, List<UUID> viewers) {
		titleView.setTitleViewModel(titleViewModel);
		for (UUID viewer : viewers) {
			titleView.display(viewer);
		}
	}
	
}
