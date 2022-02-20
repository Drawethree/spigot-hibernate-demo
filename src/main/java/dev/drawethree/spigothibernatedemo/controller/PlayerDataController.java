package dev.drawethree.spigothibernatedemo.controller;

import dev.drawethree.spigothibernatedemo.SpigotHibernateDemo;
import dev.drawethree.spigothibernatedemo.model.PlayerData;
import org.bukkit.entity.Player;
import org.hibernate.Session;

public class PlayerDataController {

	private final SpigotHibernateDemo plugin;

	public PlayerDataController(SpigotHibernateDemo plugin) {
		this.plugin = plugin;
	}

	public PlayerData createNewPlayerData(Player player) {
		PlayerData playerData = new PlayerData(player.getUniqueId().toString(), 0, 0);

		Session session = this.plugin.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		session.save(playerData);
		session.getTransaction().commit();
		session.close();

		return playerData;
	}

	public PlayerData getPlayerData(Player player) {
		Session session = this.plugin.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		PlayerData data = session.get(PlayerData.class, player.getUniqueId().toString());
		session.getTransaction().commit();
		session.close();
		return data;
	}

	public void updatePlayerData(PlayerData playerData) {
		Session session = this.plugin.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(playerData);
		session.getTransaction().commit();
		session.close();
	}

	public void deletePlayerData(PlayerData playerData) {
		Session session = this.plugin.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(playerData);
		session.getTransaction().commit();
		session.close();
	}
}
