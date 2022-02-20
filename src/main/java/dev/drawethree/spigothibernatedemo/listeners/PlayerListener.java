package dev.drawethree.spigothibernatedemo.listeners;

import dev.drawethree.spigothibernatedemo.SpigotHibernateDemo;
import dev.drawethree.spigothibernatedemo.model.PlayerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

	private final SpigotHibernateDemo plugin;

	public PlayerListener(SpigotHibernateDemo plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		PlayerData data = this.plugin.getPlayerDataController().getPlayerData(event.getPlayer());
		if (data == null) {
			this.plugin.getPlayerDataController().createNewPlayerData(event.getPlayer());
		}
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		PlayerData playerData = this.plugin.getPlayerDataController().getPlayerData(event.getEntity());
		playerData.setDeaths(playerData.getDeaths() + 1);
		this.plugin.getPlayerDataController().updatePlayerData(playerData);
		if (event.getEntity().getKiller() != null) {
			PlayerData killerData = this.plugin.getPlayerDataController().getPlayerData(event.getEntity().getKiller());
			killerData.setKills(killerData.getKills() + 1);
			this.plugin.getPlayerDataController().updatePlayerData(killerData);
		}
	}
}
