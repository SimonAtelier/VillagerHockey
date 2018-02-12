package view;

import java.util.UUID;

public interface TitleBarView {

	void displayTitle(UUID viewer, String title, String subtitle, int timeInSeconds);
	
}
