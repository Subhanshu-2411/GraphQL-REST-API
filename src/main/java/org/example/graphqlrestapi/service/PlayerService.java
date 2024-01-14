package org.example.graphqlrestapi.service;

import org.example.graphqlrestapi.model.Player;
import org.example.graphqlrestapi.model.Team;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {

    private List<Player> players = new ArrayList<>();

    AtomicInteger id = new AtomicInteger(0);

    public Optional<Player> findOne(Integer id) {
        return players.stream().filter(player -> player.Id().equals(id)).findFirst();
    }

    public Player create(String name, Team team) {
        Player player = new Player(id.incrementAndGet(), name, team);
        players.add(player);
        return player;
    }

    public Player delete(Integer id) {
        Player player = players.stream().filter(c -> c.Id().equals(id)).findFirst().orElseThrow(() -> new RuntimeException("Player not found"));
        players.remove(player);
        return player;
    }

    public Player update(Integer id, String name, Team team) {
        Optional<Player> player = Optional.ofNullable(players.stream().filter(c -> c.Id().equals(id)).findFirst().orElseThrow(() -> new RuntimeException("Player not found")));
        if(player.isPresent()) players.remove(player);
        else throw new IllegalArgumentException("Player not found");
        Player newPlayer = new Player(id, name, team);
        players.add(newPlayer);
        return newPlayer;
    }

}
