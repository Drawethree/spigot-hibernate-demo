package dev.drawethree.spigothibernatedemo.listeners;

import dev.drawethree.spigothibernatedemo.controller.PlayerDataController;
import dev.drawethree.spigothibernatedemo.model.PlayerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

	private final PlayerDataController controller;

	public PlayerListener(PlayerDataController controller) {
		this.controller = controller;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		PlayerData data = this.controller.getPlayerData(event.getPlayer());
		if (data == null) {
			this.controller.createNewPlayerData(event.getPlayer());
		}
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		PlayerData playerData = this.controller.getPlayerData(event.getEntity());
		playerData.setDeaths(playerData.getDeaths() + 1);
		this.controller.updatePlayerData(playerData);
		if (event.getEntity().getKiller() != null) {
			PlayerData killerData = this.controller.getPlayerData(event.getEntity().getKiller());
			killerData.setKills(killerData.getKills() + 1);
			this.controller.updatePlayerData(killerData);
		}
	}
}
