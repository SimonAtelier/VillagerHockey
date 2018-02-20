package usecases.jointeam;

import java.util.List;
import java.util.UUID;

import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.PlayerGateway;

public interface JoinTeam {

	void execute(JoinTeamRequest request, JoinTeamResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setPermissionGateway(PermissionGateway permissionGateway);
	
	void setPlayerGateway(PlayerGateway playerGateway);
	
	public interface JoinTeamRequest {
		
		String getGame();
		
		String getTeam();
		
		UUID getPlayer();
		
	}
	
	public interface JoinTeamResponse {
		
		void onNoPermission();
		
		void onNoSuchGame(String game);
		
		void onNoSuchTeam(String team);
		
		void onAlreadyJoinedATeam();
		
		void onTeamIsAlreadyFull(String team);
		
		void presentNowInTeam(UUID player, String team, int color);
		
		void presentTeamJoined(List<UUID> players, String player, String team);
		
	}
	
}
