package org.example.graphqlrestapi.service;

import jakarta.annotation.PostConstruct;
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
        Player newPlayer = new Player(id, name, team);
        if(player.isPresent()) players.set(players.indexOf(player.get()), newPlayer);
        else throw new IllegalArgumentException("Player not found");
        return newPlayer;
    }

    @PostConstruct
    private void init() {
        players.add(new Player(id.incrementAndGet(), "MS Dhoni", Team.CSK));
        players.add(new Player(id.incrementAndGet(), "Rohit Sharma", Team.MI));
        players.add(new Player(id.incrementAndGet(), "Virat Kohli", Team.RCB));
        players.add(new Player(id.incrementAndGet(), "David Warner", Team.SRH));
        players.add(new Player(id.incrementAndGet(), "KL Rahul", Team.LSG));
        players.add(new Player(id.incrementAndGet(), "Shreyas Iyer", Team.DC));
        players.add(new Player(id.incrementAndGet(), "Dinesh Karthik", Team.KKR));
        players.add(new Player(id.incrementAndGet(), "Steve Smith", Team.RR));
        players.add(new Player(id.incrementAndGet(), "Suresh Raina", Team.CSK));
        players.add(new Player(id.incrementAndGet(), "AB de Villiers", Team.RCB));
        players.add(new Player(id.incrementAndGet(), "Chris Gayle", Team.KXIP));
        players.add(new Player(id.incrementAndGet(), "Rishabh Pant", Team.DC));
        players.add(new Player(id.incrementAndGet(), "Sunil Narine", Team.KKR));
        players.add(new Player(id.incrementAndGet(), "Jasprit Bumrah", Team.MI));
        players.add(new Player(id.incrementAndGet(), "Ravindra Jadeja", Team.CSK));
        players.add(new Player(id.incrementAndGet(), "Hardik Pandya", Team.GT));
        players.add(new Player(id.incrementAndGet(), "Andre Russell", Team.KKR));
        players.add(new Player(id.incrementAndGet(), "Ravichandran Ashwin", Team.DC));
        players.add(new Player(id.incrementAndGet(), "Bhuvneshwar Kumar", Team.SRH));
        players.add(new Player(id.incrementAndGet(), "Shikhar Dhawan", Team.DC));

    }

    public List<Player> findAll() {
        return players;
    }
}
