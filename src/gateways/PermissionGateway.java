package gateways;

import java.util.UUID;

public interface PermissionGateway {

	boolean hasPermission(UUID uniquePlayerId, String permission);
	
}
