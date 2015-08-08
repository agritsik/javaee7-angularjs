package com.agritsik.samples.catalog.entity;

import javax.persistence.*;

/**
 * Created by andrey on 7/24/15.
 */
@Entity
@Table(name = "careers")
@NamedQuery(name = Career.FIND_BY_PLAYER_ID, query = "SELECT c FROM Career c WHERE c.player.id=:playerId")
public class Career {

    public static final String FIND_BY_PLAYER_ID = "Career.findByPlayerId";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Player player;

    @ManyToOne
    private Club club;


    public Career() {
    }

    public Career(Player player, Club club) {
        this.player = player;
        this.club = club;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    @Override
    public String toString() {
        return "Career{" +
                "id=" + id +
                ", player=" + player +
                ", club=" + club +
                '}';
    }
}
