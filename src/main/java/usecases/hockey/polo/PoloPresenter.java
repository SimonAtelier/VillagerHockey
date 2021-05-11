package usecases.hockey.polo;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import entities.Location;
import entities.TeamColor;
import usecases.hockey.polo.Polo.PoloResponse;
import util.LocationConvert;
import view.title.TitleView;
import view.title.TitleViewImpl;
import view.title.TitleViewModel;
import view.title.TitleViewModelImpl;

public class PoloPresenter implements PoloResponse {

	private PoloResponseModel responseModel;

	@Override
	public void onPresent(PoloResponseModel responseModel) {
		this.responseModel = responseModel;

		World world = Bukkit.getWorld(responseModel.getWorldName());
		List<UUID> players = responseModel.getPlayers();
		List<Location> locations = responseModel.getLocations();

		for (int i = 0; i < players.size(); i++) {
			TeamColor teamColor = responseModel.getTeamColors().get(i);
			org.bukkit.Location l = LocationConvert.toBukkitLocation(locations.get(i));
			Horse horse = (Horse) world.spawnEntity(l, EntityType.HORSE);
			horse.setTamed(true);
			horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
			horse.getInventory().setArmor(
					createColoredLeatherArmour(Material.LEATHER_HORSE_ARMOR, Color.fromRGB(teamColor.getRGB())));
			horse.addPassenger(Bukkit.getPlayer(players.get(i)));
		}

		broadcastTitle();
	}

	private void broadcastTitle() {
		TitleView titleView = new TitleViewImpl();
		titleView.setTitleViewModel(createTitleViewModel());

		for (UUID player : responseModel.getPlayers()) {
			titleView.display(player);
		}
	}

	private TitleViewModel createTitleViewModel() {
		TitleViewModel titleViewModel = new TitleViewModelImpl();
		titleViewModel.setTitle("§cPOLO!");
		titleViewModel.setSubtitle("§eSPECIAL ROUND!");
		titleViewModel.setFadeInTimeInSeconds(1);
		titleViewModel.setStayTimeInSeconds(2);
		titleViewModel.setFadeOutTimeInSeconds(1);
		return titleViewModel;
	}

	private ItemStack createColoredLeatherArmour(Material material, Color color) {
		ItemStack itemStack = new ItemStack(material, 1);
		LeatherArmorMeta itemMeta = (LeatherArmorMeta) itemStack.getItemMeta();
		itemMeta.setColor(color);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

}
