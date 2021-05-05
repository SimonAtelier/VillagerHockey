package usecases.prepareplayerforlobby;

public class PreparePlayerForLobbyResponseModel {

	private int experience;
	private int level;
	private int foodLevel;
	private boolean canForceStart;
	private boolean canSelectTeam;
	private boolean canViewAchievements;
	private boolean canViewCosmetics;
	private boolean maxHealth;
	private boolean removeAllPotionEffects;
	private String gameMode;
	
	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getFoodLevel() {
		return foodLevel;
	}

	public void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
	}

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
	
	public boolean isCanViewAchievements() {
		return canViewAchievements;
	}

	public void setCanViewAchievements(boolean canViewAchievements) {
		this.canViewAchievements = canViewAchievements;
	}

	public boolean isCanViewCosmetics() {
		return canViewCosmetics;
	}

	public void setCanViewCosmetics(boolean canViewCosmetics) {
		this.canViewCosmetics = canViewCosmetics;
	}

	public boolean isMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(boolean maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	public boolean isRemoveAllPotionEffects() {
		return removeAllPotionEffects;
	}

	public void setRemoveAllPotionEffects(boolean removeAllPotionEffects) {
		this.removeAllPotionEffects = removeAllPotionEffects;
	}

	public String getGameMode() {
		return gameMode;
	}

	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}
	
}
