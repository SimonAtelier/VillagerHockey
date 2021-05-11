package usecases.encaps.displaywinner;

import java.util.List;
import java.util.UUID;

import view.title.TitleViewModel;

public interface DisplayWinnerView {
	
	void displayTitle(List<UUID> viewers);
	
	TitleViewModel getTitleViewModel();
	
}
