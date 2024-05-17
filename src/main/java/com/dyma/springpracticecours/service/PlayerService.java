package com.dyma.springpracticecours.service;

import com.dyma.springpracticecours.Player;
import com.dyma.springpracticecours.PlayerToSave;
import com.dyma.springpracticecours.Rank;
import com.dyma.springpracticecours.entity.PlayerEntity;
import com.dyma.springpracticecours.errors.specific.ResourceNotFoundException;
import com.dyma.springpracticecours.repository.PlayerRepository;
import org.springframework.stereotype.Service;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {


    private final PlayerRepository playerRepository;
    public PlayerService(PlayerRepository playerRepository) {

        this.playerRepository = playerRepository;
    }

    /*list tous les joueurs*/
    public List<Player> getPlayers() {
        List<Player> players = playerRepository.findAll().stream().map(
                    player -> new Player(
                            player.getFirstName(),
                            player.getFirstName(),
                            player.getBirthDate(),
                            new Rank(player.getRank(), player.getPoints())
                    )
                )
                .sorted(Comparator.comparing(player -> player.rank().position()))
                .collect(Collectors.toList());
        if (players.isEmpty()) {
            throw new ResourceNotFoundException("Aucun joueurs trouvé");
        }
        return players;
    }


    /*list le joueur en fonction du lastname*/
    public Player getPlayerByLastName(String lastName) {
        Optional<PlayerEntity> player = playerRepository.findOneByLastNameIgnoreCase(lastName);
        if (player.isEmpty()) {
            throw new ResourceNotFoundException("Joueur introuvable avec le nom de famille : " +lastName));
        }

        return new Player(
                player.get().getFirstName(),
                player.get().getLastName(),
                player.get().getBirthDate(),
                new Rank(player.get().getRank(), player.get().getPoints())
        );
    }


    /*creer un nouveau joueur*/
   public PlayerToSave createPlayer(PlayerToSave player) {
        return player;
   }

   /*update un nouveau joueur*/
   public PlayerToSave updatePlayer(PlayerToSave player) {
      return player;
   }


   public String deletePlayer(String lastName) {
       return lastName;
   }


}
