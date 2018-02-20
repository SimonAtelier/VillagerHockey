package view.title;

import java.util.UUID;

public interface TitleView {

	void display();
	
	void setViewer(UUID viewer);
	
	void setTitle(String title);
	
	void setSubtitle(String subtitle);
	
	void setFadeInTimeInSeconds(int fadeInTimeInSeconds);
	
	void setFadeOutTimeInSeconds(int fadeOutTimeInSeconds);
	
	void setStayTimeInSeconds(int stayTimeInSeconds);
	
}
