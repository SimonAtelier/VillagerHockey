package gateways.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entities.JoinSigns;
import entities.Location;
import gateways.JoinSignGateway;

public class JoinSignsGatewayImpl implements JoinSignGateway {

	private HashMap<String, JoinSigns> joinSigns;
	
	public JoinSignsGatewayImpl() {
		joinSigns = new HashMap<String, JoinSigns>();
	}
	
	@Override
	public List<Location> findJoinSignLocations(String game) {
		JoinSigns joinSigns = this.joinSigns.get(game);
		if (joinSigns == null)
			return new ArrayList<Location>();
		return joinSigns.getLocations();
	}

	@Override
	public void addLocation(String game, Location location) {
		JoinSigns joinSigns = this.joinSigns.get(game);
		if (joinSigns == null) {
			joinSigns = new JoinSigns();
			this.joinSigns.put(game, joinSigns);
		}
		joinSigns.addLocation(location);
	}

	@Override
	public void removeLocation(String game, Location location) {
		JoinSigns joinSigns = this.joinSigns.get(game);
		if (joinSigns != null)
			joinSigns.removeLocation(location);
	}

}
