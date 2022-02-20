package dev.drawethree.spigothibernatedemo.controller;

import dev.drawethree.spigothibernatedemo.SpigotHibernateDemo;
import dev.drawethree.spigothibernatedemo.model.PlayerData;
import org.bukkit.entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.UUID;


class PlayerDataControllerTest {

	private static final UUID playerUUID = UUID.randomUUID();

	private static SpigotHibernateDemo mockPlugin;
	private static PlayerDataController playerDataController;
	private static Player mockPlayer;
	private static SessionFactory sessionFactory;

	@BeforeAll
	static void setUp() {
		mockPlugin = Mockito.mock(SpigotHibernateDemo.class);
		playerDataController = new PlayerDataController(mockPlugin);
		mockPlayer = Mockito.mock(Player.class);

		sessionFactory = new Configuration()
				.configure()
				.addAnnotatedClass(PlayerData.class)
				.buildSessionFactory();

		Mockito.when(mockPlayer.getUniqueId()).thenReturn(playerUUID);
		Mockito.when(mockPlugin.getSessionFactory()).thenReturn(sessionFactory);
	}

	@BeforeEach
	void before() {
		cleanUpDb();
	}

	@AfterAll
	static void tearDown() {
		sessionFactory.close();
	}

	private static void cleanUpDb() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.createQuery("delete from PlayerData ").executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

	@Test
	void test_savingPlayerData() {
		System.out.println("=== test_savingPlayerData === ");
		PlayerData createdData = playerDataController.createNewPlayerData(mockPlayer);
		System.out.println("Created player_data: " + createdData);
		PlayerData actualData = playerDataController.getPlayerData(mockPlayer);
		System.out.println("Reading player_data by PK uuid " + mockPlayer.getUniqueId() + ": " + actualData);

		Assertions.assertEquals(createdData, actualData);
		System.out.println("=== test_savingPlayerData === ");

	}

	@Test
	void test_updatingPlayerData() {
		System.out.println("=== test_updatingPlayerData === ");
		PlayerData createdData = playerDataController.createNewPlayerData(mockPlayer);
		System.out.println("Created player_data: " + createdData);

		createdData.setKills(createdData.getKills() + 1);
		createdData.setDeaths(createdData.getDeaths() + 1);
		System.out.println("Updated kills to : " + createdData.getKills());
		System.out.println("Updated deaths to : " + createdData.getDeaths());

		playerDataController.updatePlayerData(createdData);
		System.out.println("Updated player_data " + createdData);

		PlayerData actualData = playerDataController.getPlayerData(mockPlayer);
		System.out.println("Reading player_data by PK uuid " + mockPlayer.getUniqueId() + ": " + actualData);

		Assertions.assertEquals(createdData, actualData);
		System.out.println("=== test_updatingPlayerData === ");
	}

	@Test
	void test_deletePlayerData() {
		System.out.println("=== test_deletePlayerData === ");
		PlayerData createdData = playerDataController.createNewPlayerData(mockPlayer);
		System.out.println("Created player_data: " + createdData);

		playerDataController.deletePlayerData(createdData);
		System.out.println("Deleted player_data " + createdData);

		PlayerData actualData = playerDataController.getPlayerData(mockPlayer);
		System.out.println("Reading player_data by PK uuid " + mockPlayer.getUniqueId() + ": " + actualData);

		Assertions.assertNull(actualData);
		System.out.println("=== test_deletePlayerData === ");
	}
}