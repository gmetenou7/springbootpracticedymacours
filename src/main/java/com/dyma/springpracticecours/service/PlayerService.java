package com.dyma.springpracticecours.service;

import com.dyma.springpracticecours.Player;
import com.dyma.springpracticecours.PlayerList;
import com.dyma.springpracticecours.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {


    public List<Player> getPlayers() {
        List<Player> players = PlayerList.ALL.stream()
                .sorted(Comparator.comparing(player -> player.rank().position()))
                .collect(Collectors.toList());

        if (players.isEmpty()) {
            throw new ResourceNotFoundException("Aucun joueur trouvé");
        }

        return players;
    }

    public Player getPlayerByLastName(String lastName) {
        return  PlayerList.ALL.stream()
                .filter(player -> player.lastName().equals(lastName))
                .findFirst()
                .orElseThrow(()-> new ResourceNotFoundException("Joueur introuvable avec le nom de famille : " +lastName));
    }
}
