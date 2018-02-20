package usecases.displaywinner;

import java.util.List;
import java.util.UUID;

import usecases.displaywinner.DisplayWinner.DisplayWinnerResponse;

public class DisplayWinnerPresenter implements DisplayWinnerResponse {

	private DisplayWinnerView view;
	
	public DisplayWinnerPresenter(DisplayWinnerView view) {
		this.view = view;
	}
	
	@Override
	public void presentWinner(List<UUID> viewers, String team) {
		view.displayWinner(viewers, team);
	}

	@Override
	public void presentDraw(List<UUID> viewers) {
		view.displayDraw(viewers);
	}

}
