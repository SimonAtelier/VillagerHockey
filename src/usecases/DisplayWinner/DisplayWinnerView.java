package usecases.DisplayWinner;

import java.util.List;
import java.util.UUID;

public interface DisplayWinnerView {

	void displayWinner(List<UUID> viewers, String team);

	void displayDraw(List<UUID> viewers);
	
}
