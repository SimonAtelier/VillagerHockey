package game.usecases.prepareplayerforlobby;

public class PreparePlayerForLobbyResponseModel {

	private boolean canForceStart;
	private boolean canSelectTeam;
	
	public boolean isCanForceStart() {
		return canForceStart;
	}
	
	public void setCanForceStart(boolean canForceStart) {
		this.canForceStart = canForceStart;
	}
	
	public boolean isCanSelectTeam() {
		return canSelectTeam;
	}
	
	public void setCanSelectTeam(boolean canSelectTeam) {
		this.canSelectTeam = canSelectTeam;
	}
	
}
