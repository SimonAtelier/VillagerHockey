package context;

import config.Configuration;
import gateways.CommandGateway;
import gateways.GameGateway;
import gateways.InventoryGateway;
import gateways.JoinSignGateway;
import gateways.PermissionGateway;
import gateways.PlayerDataGateway;
import gateways.PlayerGateway;
import gateways.SignGateway;
import gateways.TeamSpawnsGateway;
import view.message.MessageView;

public class Context {

	public static PermissionGateway permissionGateway;
	
	public static InventoryGateway inventoryGateway;
	
	public static GameGateway gameGateway;
	
	public static PlayerGateway playerGateway;
	
	public static MessageView messageView;
	
	public static CommandGateway commandGateway;
	
	public static TeamSpawnsGateway teamSpawnsGateway;
	
	public static SignGateway signGateway;
	
	public static JoinSignGateway joinSignGateway;
	
	public static Configuration configuration;
	
	public static PlayerDataGateway playerDataGateway;
	
}
