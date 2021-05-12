package usecases.encaps.displaywinner;

import java.util.List;
import java.util.UUID;

import minigame.view.TitleViewModel;

public interface DisplayWinnerView {
	
	void displayTitle(TitleViewModel titleViewModel, List<UUID> viewers);
	
}
