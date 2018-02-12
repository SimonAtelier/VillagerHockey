package context;

import gateways.CommandGateway;
import gateways.GameGateway;
import gateways.InventoryGateway;
import gateways.PermissionGateway;
import gateways.PlayerGateway;
import gateways.SignGateway;
import gateways.TeamSpawnsGateway;
import gateways.impl.CommandGatewayImpl;
import gateways.impl.InventoryGatewayYaml;
import gateways.impl.PermissionGatewayAlwaysTrueMock;
import gateways.impl.PlayerGatewayImpl;
import gateways.impl.SignGatewayImpl;
import gateways.impl.TeamSpawnGatewayImpl;
import view.MessageView;
import view.impl.MessageViewImpl;

public class Context {

	public static PermissionGateway permissionGateway = new PermissionGatewayAlwaysTrueMock();
	
	public static InventoryGateway inventoryGateway = new InventoryGatewayYaml();
	
	public static GameGateway gameGateway;
	
	public static PlayerGateway playerGateway = new PlayerGatewayImpl();
	
	public static MessageView messageView = new MessageViewImpl();
	
	public static CommandGateway commandGateway = new CommandGatewayImpl();
	
	public static TeamSpawnsGateway teamSpawnsGateway = new TeamSpawnGatewayImpl();
	
	public static SignGateway signGateway = new SignGatewayImpl();
	
}
