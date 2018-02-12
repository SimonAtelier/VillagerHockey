package usecases.LeaveTeam;

import java.util.List;
import java.util.UUID;

import gateways.GameGateway;
import gateways.PlayerGateway;

public interface LeaveTeam {

	void execute(LeaveTeamRequest request, LeaveTeamResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setPlayerGateway(PlayerGateway playerGateway);
	
	public interface LeaveTeamRequest {
		
		UUID getPlayer();
		
	}
	
	public interface LeaveTeamResponse {
		
		void presentLeftTeam(UUID player, String team);
		
		void presentLeftTeam(List<UUID> players, String player, String team);
		
	}
	
}
