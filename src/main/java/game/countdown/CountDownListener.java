package game.countdown;

public interface CountDownListener {

	void onStart(String game, int timeLeftInSeconds);
	
	void onStop(String game, int timeLeftInSeconds);
	
	void onCountDownOneSecond(CountDown countdown, String game, int timeLeftInSeconds);
	
	void onUpdateIgnorePaused(CountDown countdown, String game, int timeLeftInSeconds);
	
}
