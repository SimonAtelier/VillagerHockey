package gateways.impl;

import java.util.UUID;

import gateways.PermissionGateway;

public class PermissionGatewayAlwaysFalseMock implements PermissionGateway {

	@Override
	public boolean hasPermission(UUID uniquePlayerId, String permission) {
		return false;
	}

}
