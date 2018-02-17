package context;

import gateways.CommandGateway;
import gateways.GameGateway;
import gateways.InventoryGateway;
import gateways.PermissionGateway;
import gateways.PlayerGateway;
import gateways.SignGateway;
import gateways.TeamSpawnsGateway;
import view.MessageView;

public class Context {

	public static PermissionGateway permissionGateway;
	
	public static InventoryGateway inventoryGateway;
	
	public static GameGateway gameGateway;
	
	public static PlayerGateway playerGateway;
	
	public static MessageView messageView;
	
	public static CommandGateway commandGateway;
	
	public static TeamSpawnsGateway teamSpawnsGateway;
	
	public static SignGateway signGateway;
	
}
