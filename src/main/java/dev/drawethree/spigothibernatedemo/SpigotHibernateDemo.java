package dev.drawethree.spigothibernatedemo;

import dev.drawethree.spigothibernatedemo.controller.PlayerDataController;
import dev.drawethree.spigothibernatedemo.listeners.PlayerListener;
import dev.drawethree.spigothibernatedemo.model.PlayerData;
import org.bukkit.plugin.java.JavaPlugin;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class SpigotHibernateDemo extends JavaPlugin {

	private static final String HIBERNATE_CONFIG_FILE_NAME = "hibernate.cfg.xml";

	private SessionFactory sessionFactory;
	private PlayerDataController playerDataController;

	@Override
	public void onEnable() {
		this.saveResource(HIBERNATE_CONFIG_FILE_NAME, false);
		this.sessionFactory = new Configuration()
				.configure(new File(this.getDataFolder().getAbsolutePath() + "/" + HIBERNATE_CONFIG_FILE_NAME))
				.addAnnotatedClass(PlayerData.class)
				.buildSessionFactory();
		this.playerDataController = new PlayerDataController(this);

		this.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
	}

	@Override
	public void onDisable() {
		if (this.sessionFactory != null) {
			this.sessionFactory.close();
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public PlayerDataController getPlayerDataController() {
		return playerDataController;
	}
}
