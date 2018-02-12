package gateways.impl;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;

import gateways.SignGateway;
import spigot.LocationConvert;

public class SignGatewayImpl implements SignGateway {

	@Override
	public void update(entities.Sign sign) {
		Block block = getBukkitLocation(sign).getBlock();
		
		if (!(block.getState() instanceof Sign))
			return;
		
		Sign bukkitSign = (Sign) block.getState();
		bukkitSign.setLine(0, sign.getFirstLine());
		bukkitSign.setLine(1, sign.getSecondLine());
		bukkitSign.setLine(2, sign.getThirdLine());
		bukkitSign.setLine(3, sign.getFourthLine());
		bukkitSign.update();
	}
	
	private Location getBukkitLocation(entities.Sign joinSign) {
		return LocationConvert.toBukkitLocation(joinSign.getLocation());
	}
	
}
