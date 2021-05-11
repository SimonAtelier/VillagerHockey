package game.countdown;

import game.Game;

public class SecondsBasedCountDown implements CountDown {

	private int tickCount;
	private int timeToCountDownInSeconds;
	private int timeLeftInSeconds;
	private boolean running;
	private boolean paused;
	private Game game;
	private CountDownListener listener;
	
	public SecondsBasedCountDown(Game game, int timeToCountDownInSeconds) {
		this.timeToCountDownInSeconds = timeToCountDownInSeconds;
		this.timeLeftInSeconds = timeToCountDownInSeconds;
		this.game = game;
	}
	
	private void fireCountDownOneSecond() {
		listener.onCountDownOneSecond(this, game.getName(), timeLeftInSeconds);
	}
	
	private void fireCountDownStart() {
		listener.onStart(game.getName(), timeLeftInSeconds);
	}
	
	private void fireCountDownStop() {
		listener.onStop(game.getName(), timeLeftInSeconds);
	}
	
	private void fireUpdateIgnoringPause() {
		listener.onUpdateIgnorePaused(this, game.getName(), timeLeftInSeconds);
	}
	
	@Override
	public void tick() {
		tickCount++;
		
		if (timeLeftInSeconds == 0) {
			stop();
		}
		
		if (tickCount == 20) {
			tickCount = 0;
			countDownOneSecond();
		}
		
		fireUpdateIgnoringPause();
	}
	
	private void countDownOneSecond() {
		if (running && !paused) {
			fireCountDownOneSecond();
			timeLeftInSeconds--;
		}
	}
	
	@Override
	public void start() {
		if (running)
			return;
		running = true;
		timeLeftInSeconds = timeToCountDownInSeconds;
		fireCountDownStart();
	}
	
	@Override
	public void stop() {
		if (!running)
			return;
		fireCountDownStop();
		running = false;
	}

	@Override
	public void pause() {
		paused = true;
	}

	@Override
	public void resume() {
		paused = false;
	}

	@Override
	public void setTimeToCountDownInSeconds(int timeToCountDownInSeconds) {
		this.timeToCountDownInSeconds = timeToCountDownInSeconds;
	}

	@Override
	public void setCountDownListener(CountDownListener listener) {
		this.listener = listener;
	}

	@Override
	public boolean isFinished() {
		return timeLeftInSeconds <= 0;
	}

}
