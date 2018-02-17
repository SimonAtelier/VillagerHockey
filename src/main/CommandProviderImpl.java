package main;

import entities.Command.CommandProvider;
import entities.Command.CommandRegistration;
import usecases.AddGame.AddGameCommand;
import usecases.AddTeam.AddTeamCommand;
import usecases.AddTeamSpawn.AddTeamSpawnCommand;
import usecases.JoinGame.JoinGameCommand;
import usecases.KickPlayer.KickPlayerCommand;
import usecases.LeaveGame.LeaveGameCommand;
import usecases.ListGames.ListGamesCommand;
import usecases.RemoveGame.RemoveGameCommand;
import usecases.SaveGame.SaveGameCommand;
import usecases.SetGoal.SetGoalCommand;
import usecases.SetLobby.SetLobbyCommand;
import usecases.SetMinPlayers.SetMinPlayersCommand;
import usecases.SetPlayingTime.SetPlayingTimeCommand;
import usecases.SetVillagerSpawn.SetVillagerSpawnCommand;
import usecases.ShowHelp.ShowHelpCommand;
import usecases.StartGame.StartGameCommand;
import usecases.StopGame.StopGameCommand;

public class CommandProviderImpl implements CommandProvider {

	@Override
	public void registerCommands(CommandRegistration commandRegistration) {
		commandRegistration.registerCommand(new AddGameCommand());
		commandRegistration.registerCommand(new AddTeamSpawnCommand());
		commandRegistration.registerCommand(new JoinGameCommand());
		commandRegistration.registerCommand(new KickPlayerCommand());
		commandRegistration.registerCommand(new LeaveGameCommand());
		commandRegistration.registerCommand(new ListGamesCommand());
		commandRegistration.registerCommand(new SaveGameCommand());
		commandRegistration.registerCommand(new SetGoalCommand());
		commandRegistration.registerCommand(new SetLobbyCommand());
		commandRegistration.registerCommand(new SetVillagerSpawnCommand());
		commandRegistration.registerCommand(new ShowHelpCommand());
		commandRegistration.registerCommand(new SetMinPlayersCommand());
		commandRegistration.registerCommand(new SetPlayingTimeCommand());
		commandRegistration.registerCommand(new AddTeamCommand());
		commandRegistration.registerCommand(new RemoveGameCommand());
		commandRegistration.registerCommand(new StartGameCommand());
		commandRegistration.registerCommand(new StopGameCommand());
	}

}
