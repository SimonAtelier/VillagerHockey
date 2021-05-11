package usecases.encaps.displaywinner;

import java.util.List;
import java.util.UUID;

import view.title.TitleView;
import view.title.TitleViewImpl;
import view.title.TitleViewModel;

public class DisplayWinnerViewImpl implements DisplayWinnerView {

	private TitleView titleView;
	
	public DisplayWinnerViewImpl() {
		titleView = new TitleViewImpl();
	}
	
	@Override
	public void displayTitle(List<UUID> viewers) {
		for (UUID viewer : viewers) {
			titleView.display(viewer);
		}
	}

	@Override
	public TitleViewModel getTitleViewModel() {
		return titleView.getTitleViewModel();
	}
	
}
