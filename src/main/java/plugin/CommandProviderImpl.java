package plugin;

import entities.command.CommandProvider;
import entities.command.CommandRegistration;
import usecases.achievements.displayachievements.DisplayAchievementsCommand;
import usecases.api.addgame.AddGameCommand;
import usecases.api.addteam.AddTeamCommand;
import usecases.api.forcestart.ForceStartCommand;
import usecases.api.joingame.JoinGameCommand;
import usecases.api.kickplayer.KickPlayerCommand;
import usecases.api.leavegame.LeaveGameCommand;
import usecases.api.listgames.ListGamesCommand;
import usecases.api.setlobby.SetLobbyCommand;
import usecases.api.setminplayers.SetMinPlayersCommand;
import usecases.api.setplayingtime.SetPlayingTimeCommand;
import usecases.api.showhelp.ShowHelpCommand;
import usecases.api.startgame.StartGameCommand;
import usecases.api.stopgame.StopGameCommand;
import usecases.encaps.addteamspawn.AddTeamSpawnCommand;
import usecases.encaps.showstatistics.ShowStatisticsCommand;
import usecases.hockey.savegame.SaveGameCommand;
import usecases.hockey.setgoal.SetGoalCommand;
import usecases.hockey.setvillagerspawn.SetVillagerSpawnCommand;

public class CommandProviderImpl implements CommandProvider {

	@Override
	public void registerCommands(CommandRegistration commandRegistration) {
		commandRegistration.registerCommand(new AddGameCommand("addgame"));
		commandRegistration.registerCommand(new AddTeamSpawnCommand("addteamspawn"));
		commandRegistration.registerCommand(new JoinGameCommand("join"));
		commandRegistration.registerCommand(new KickPlayerCommand("kick"));
		commandRegistration.registerCommand(new LeaveGameCommand("leave"));
		commandRegistration.registerCommand(new ListGamesCommand("list"));
		commandRegistration.registerCommand(new SaveGameCommand("save"));
		commandRegistration.registerCommand(new SetGoalCommand("setgoal"));
		commandRegistration.registerCommand(new SetLobbyCommand("setlobby"));
		commandRegistration.registerCommand(new SetVillagerSpawnCommand("setspawner"));
		commandRegistration.registerCommand(new ShowHelpCommand("help"));
		commandRegistration.registerCommand(new SetMinPlayersCommand("setminplayers"));
		commandRegistration.registerCommand(new SetPlayingTimeCommand("setplayingtime"));
		commandRegistration.registerCommand(new AddTeamCommand("addteam"));
		commandRegistration.registerCommand(new StartGameCommand("start"));
		commandRegistration.registerCommand(new StopGameCommand("stop"));
		commandRegistration.registerCommand(new ShowStatisticsCommand("stats"));
		commandRegistration.registerCommand(new DisplayAchievementsCommand("rewards"));
		commandRegistration.registerCommand(new ForceStartCommand("forcestart"));
	}

}
