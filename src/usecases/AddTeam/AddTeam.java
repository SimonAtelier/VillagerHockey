package usecases.AddTeam;

import java.util.List;
import java.util.UUID;

import gateways.GameGateway;
import gateways.PermissionGateway;

public interface AddTeam {

	void execute(AddTeamRequest request, AddTeamResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setPermissionGateway(PermissionGateway permissionGateway);
	
	public interface AddTeamRequest {
		
		UUID getPlayer();
		
		String getGame();
		
		String getName();
		
		String getColor();
		
	}
	
	public interface AddTeamResponse {
		
		void onNoPermission();
		
		void onNoSuchGame(String name);
		
		void onInvalidTeamName(String name);
		
		void onTeamColorIsNotValid(String color, List<String> possibleValues);
		
		void onTeamWithNameAlreadyExists(String name);
		
		void onTeamWithColorAlreadyExists(String color);
		
		void onTeamSuccessfullyAdded(String game, String team);
		
		void onMaximumAmountOfTeamsAlreadyReached(int maximum);
		
	}
	
}
