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

        List<Player> players =  playerRepository.findAll().stream()
                .map(player -> new Player(
                        player.getFirstName(),
                        player.getLastName(),
                        player.getBirthDate(),
                        new Rank(player.getRank(), player.getPoints())
                ))
                .sorted(Comparator.comparing(player -> player.rank().position()))
                .collect(Collectors.toList());


        if (players.isEmpty()){
            throw new ResourceNotFoundException("Aucun joueurs trouvé");
        }



        return players;
    }

    /*list le joueur en fonction du lastname*/
    public Player getPlayerByLastName(String lastName) {

        Optional<PlayerEntity> player = playerRepository.findByLastNameIgnoreCase(lastName);
        if (player.isEmpty()) {
            throw new ResourceNotFoundException("Joueur introuvable avec le nom de famille : " +lastName);
        }

        return new Player(
                player.get().getFirstName(),
                player.get().getLastName(),
                player.get().getBirthDate(),
                new Rank(player.get().getRank(), player.get().getPoints())
        );
    }

    /*creer un nouveau joueur*/
   public Player createPlayer(PlayerToSave playerToSave) {


       Optional<PlayerEntity> playerToCreate =playerRepository.findByLastNameIgnoreCase(playerToSave.lastName());
       if (playerToCreate.isPresent()) {
            throw new ResourceNotFoundException("player with last name " + playerToSave.lastName() + " already exists");
       }

        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setLastName(playerToSave.lastName());
        playerEntity.setFirstName(playerToSave.firstName());
        playerEntity.setBirthDate(playerToSave.birthDate());
        playerEntity.setPoints(playerToSave.points());
        playerEntity.setRank(1);
        playerRepository.save(playerEntity);

        return getPlayerByLastName(playerToSave.lastName());
   }

   /*update un nouveau joueur*/
   public Player updatePlayer(PlayerToSave playerToSave, String lastName) {

       Optional<PlayerEntity> player = playerRepository.findByLastNameIgnoreCase(lastName);
       if (player.isEmpty()) {
           throw new ResourceNotFoundException("Joueur introuvable avec le nom de famille : " +lastName);
       }

       player.get().setLastName(playerToSave.lastName());
       player.get().setFirstName(playerToSave.firstName());
       player.get().setBirthDate(playerToSave.birthDate());
       player.get().setPoints(playerToSave.points());
       playerRepository.save(player.get());

       return getPlayerByLastName(playerToSave.lastName());
   }


   public String deletePlayer(String lastName) {
       Optional<PlayerEntity> player = playerRepository.findByLastNameIgnoreCase(lastName);
       if (player.isEmpty()) {
           throw new ResourceNotFoundException("Joueur introuvable avec le nom de famille : " +lastName);
       }

       playerRepository.delete(player.get());

       return "Le joueur avec le nom de famille '" + lastName + "' a été supprimé avec succès.";
   }


}
