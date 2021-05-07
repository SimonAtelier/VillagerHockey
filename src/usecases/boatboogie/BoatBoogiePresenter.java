package usecases.boatboogie;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.TreeSpecies;
import org.bukkit.World;
import org.bukkit.entity.Boat;
import org.bukkit.entity.EntityType;

import entities.Location;
import usecases.boatboogie.BoatBoogie.BoatBoogieResponse;
import util.LocationConvert;
import view.title.TitleView;
import view.title.TitleViewImpl;
import view.title.TitleViewModel;
import view.title.TitleViewModelImpl;

public class BoatBoogiePresenter implements BoatBoogieResponse {

	private BoatBoogieResponseModel responseModel;
	
	@Override
	public void onPresentBoats(BoatBoogieResponseModel responseModel) {
		setResponseModel(responseModel);
		
		World world = Bukkit.getWorld(responseModel.getWorldName());
		List<UUID> players = responseModel.getPlayers();
		List<Location> locations = responseModel.getLocations();
		
		for (int i = 0; i < players.size(); i++) {
			org.bukkit.Location l = LocationConvert.toBukkitLocation(locations.get(i));
			Boat boat = (Boat) world.spawnEntity(l, EntityType.BOAT);
			boat.setWoodType(TreeSpecies.DARK_OAK);
			boat.addPassenger(Bukkit.getPlayer(players.get(i)));
		}
		
		broadcastTitle();
	}
	
	private void broadcastTitle() {
		TitleView titleView = new TitleViewImpl();
		titleView.setTitleViewModel(createTitleViewModel());
		
		for (UUID player : responseModel.getPlayers())
			titleView.display(player);
	}
	
	private TitleViewModel createTitleViewModel() {
		TitleViewModel titleViewModel = new TitleViewModelImpl();
		titleViewModel.setTitle("§cBOAT BOOGIE!");
		titleViewModel.setSubtitle("§eSPECIAL ROUND!");
		titleViewModel.setFadeInTimeInSeconds(1);
		titleViewModel.setStayTimeInSeconds(2);
		titleViewModel.setFadeOutTimeInSeconds(1);
		return titleViewModel;
	}
	
	private void setResponseModel(BoatBoogieResponseModel responseModel) {
		this.responseModel = responseModel;
	}

}
