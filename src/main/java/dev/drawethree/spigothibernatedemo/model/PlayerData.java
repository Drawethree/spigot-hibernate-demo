package dev.drawethree.spigothibernatedemo.model;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "player_data")
public class PlayerData {

	@Id
	@Column(name = "uuid")
	private String id;

	@Column(name = "kills")
	private int kills;

	@Column(name = "deaths")
	private int deaths;

	public PlayerData(String id, int kills, int deaths) {
		this.id = id;
		this.kills = kills;
		this.deaths = deaths;
	}

	public PlayerData() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UUID getUuid() {
		return UUID.fromString(this.id);
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public OfflinePlayer getPlayerOffline() {
		return Bukkit.getOfflinePlayer(this.getUuid());
	}

	public Player getPlayer() {
		return this.getPlayerOffline().getPlayer();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PlayerData)) return false;
		PlayerData that = (PlayerData) o;
		return kills == that.kills && deaths == that.deaths && id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, kills, deaths);
	}

	@Override
	public String toString() {
		return "PlayerData{" +
				"id='" + id + '\'' +
				", kills=" + kills +
				", deaths=" + deaths +
				'}';
	}
}
