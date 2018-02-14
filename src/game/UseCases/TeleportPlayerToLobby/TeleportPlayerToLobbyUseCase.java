package game.UseCases.TeleportPlayerToLobby;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import entities.Location;
import game.Game;
import gateways.GameGateway;
import util.LocationConvert;

public class TeleportPlayerToLobbyUseCase implements TeleportPlayerToLobby {

	private GameGateway gameGateway;
	
	@Override
	public void execute(UUID uniquePlayerId) {
		Game game = gameGateway.getGameOfPlayer(uniquePlayerId);
		Location lobby = game.getLobby();
		Player player = Bukkit.getPlayer(uniquePlayerId);
		player.teleport(LocationConvert.toBukkitLocation(lobby));
	}

	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}
	
}
