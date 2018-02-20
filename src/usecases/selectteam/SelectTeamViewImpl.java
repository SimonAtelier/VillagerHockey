package usecases.selectteam;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import context.Context;
import game.event.TeamSelectListener;
import main.MainPlugin;
import usecases.showteams.ShowTeamsResponseItem;
import view.MessageView;

public class SelectTeamViewImpl implements SelectTeamView, Listener {

	private int slots;
	private UUID viewer;
	private Inventory inventory;
	private List<ShowTeamsResponseItem> teams;
	private TeamSelectListener listener;

	public SelectTeamViewImpl(UUID viewer) {
		this.viewer = viewer;
		teams = new ArrayList<ShowTeamsResponseItem>();
	}
	
	@EventHandler
	public void onClickInventory(InventoryClickEvent e) {
		if (!e.getWhoClicked().getUniqueId().equals(viewer))
			return;
		if (!e.getClickedInventory().equals(inventory))
			return;
		int slot = e.getSlot();
		if (slot > teams.size() - 1)
			return;
		fireTeamSelected(teams.get(slot));
		e.getWhoClicked().closeInventory();
	}
	
	@EventHandler
	public void onCloseInventory(InventoryCloseEvent e) {
		if (!e.getPlayer().getUniqueId().equals(viewer))
			return;
		if (!e.getInventory().equals(inventory))
			return;
		InventoryClickEvent.getHandlerList().unregister(this);
	}

	@Override
	public void displayTeams(List<ShowTeamsResponseItem> teams) {
		this.teams.clear();
		this.teams.addAll(teams);
		inventory = Bukkit.createInventory(null, slots, SelectTeamViewMessages.SELECT_TEAM_TITLE);
		Player bukkitPlayer = Bukkit.getPlayer(viewer);
		createItems();
		Bukkit.getServer().getPluginManager().registerEvents(this, MainPlugin.getInstance());
		bukkitPlayer.openInventory(inventory);
	}

	@Override
	public void displayPlayerIsNotIngame() {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, SelectTeamViewMessages.SELECT_TEAM_NOT_INGAME);
	}
	
	@Override
	public void displayAlreadyJoinedATeam() {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, SelectTeamViewMessages.SELECT_TEAM_ALREADY_JOINED_A_TEAM);
	}

	private void createItems() {
		for (ShowTeamsResponseItem team : teams) {
			Color color = Color.fromRGB(team.getColor());
			ItemStack itemStack = createColoredLeatherHelmet(color);
			ItemMeta itemMeta = itemStack.getItemMeta();
			itemMeta.setDisplayName(createDisplayName(team));
			itemStack.setItemMeta(itemMeta);
			inventory.addItem(itemStack);
		}
	}
	
	private String createDisplayName(ShowTeamsResponseItem team) {
		String displayName = SelectTeamViewMessages.SELECT_TEAM_ITEM_NAME;
		displayName = displayName.replace("$team$", team.getName());
		displayName = displayName.replace("$size$", team.getSize() + "");
		displayName = displayName.replace("$maxsize$", team.getMaxSize() + "");
		return displayName;
	}
	
	private ItemStack createColoredLeatherHelmet(Color color) {
		Material material = Material.LEATHER_HELMET;
		ItemStack itemStack = new ItemStack(material, 1);
		LeatherArmorMeta itemMeta = (LeatherArmorMeta) itemStack.getItemMeta();
		itemMeta.setColor(color);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	private void fireTeamSelected(ShowTeamsResponseItem team) {
		listener.onTeamSelected(viewer, team.getGame(), team.getName());
	}

	@Override
	public void setSlots(int slots) {
		this.slots = slots;
	}

	@Override
	public UUID getViewer() {
		return viewer;
	}

	@Override
	public void setTeamSelectListener(TeamSelectListener listener) {
		this.listener = listener;
	}

}
