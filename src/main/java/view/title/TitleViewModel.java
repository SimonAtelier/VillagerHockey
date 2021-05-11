package view.title;

public interface TitleViewModel {

	String getTitle();
	
	void setTitle(String title);
	
	String getSubtitle();
	
	void setSubtitle(String subtitle);
	
	int getFadeInTimeInSeconds();
	
	void setFadeInTimeInSeconds(int fadeInTimeInSeconds);
	
	int getStayTimeInSeconds();
	
	void setStayTimeInSeconds(int stayTimeInSeconds);
	
	int getFadeOutTimeInSeconds();
	
	void setFadeOutTimeInSeconds(int fadeOutTimeInSeconds);
	
}
