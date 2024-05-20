package com.dyma.springpracticecours.web;

import com.dyma.springpracticecours.Player;
import com.dyma.springpracticecours.PlayerToSave;
import com.dyma.springpracticecours.errors.Error;
import com.dyma.springpracticecours.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tennis Players Api")
@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Operation(summary = "Finds player", description = "fetch all player")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "OK : Requête traitée avec succès.",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Player.class))
                    )}
            ),
            @ApiResponse(responseCode = "404", description = "Not Found : Ressource non trouvée.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class))})
    })
    @GetMapping
    public List<Player> playerList() {
        return playerService.getPlayers();
    }


    @Operation(summary = "get specific player", description = "fetch specific player")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "return player who match for lastName",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Player.class)
                    )}
            ),
            @ApiResponse(responseCode = "404", description = "Not Found : Ressource non trouvée.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class))})
    })
    @GetMapping("{lastName}")
    public Player getPlayerByLastName(@PathVariable("lastName") String lastName) {
        return playerService.getPlayerByLastName(lastName);
    }


    @Operation(summary = "Create a player", description = "Post a player")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Created:  Requête traitée avec succès",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlayerToSave.class)
                    )}
            ),
            @ApiResponse(responseCode = "404", description = "Not Found : Ressource non trouvée.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request : La syntaxe de la requête est erronée.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)
                    )}),
            @ApiResponse(responseCode = "500", description = "Bad Request : Une erreur interne du serveur s'est produite.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)
                    )})
    })
    @PostMapping
    public Player createPlayer(@Valid @RequestBody PlayerToSave player) {
        return playerService.createPlayer(player);
    }


    @Operation(summary = "update a player", description = "update a player")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "return player",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlayerToSave.class)
                    )}
            ),
            @ApiResponse(responseCode = "404", description = "Not Found : Ressource non trouvée.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request : La syntaxe de la requête est erronée.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)
                    )}
            )
    })
    @PutMapping("{lastName}")
    public Player updatePlayer(@Valid @RequestBody PlayerToSave player, @PathVariable("lastName") String lastName) {
        return playerService.updatePlayer(player, lastName);
    }


    @Operation(summary = "delete a player", description = "delete a player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "player has been deleted"),
            @ApiResponse(responseCode = "404", description = "Player with specified last name was not found.",
                content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = Error.class))}
            )
    })
    @DeleteMapping("{lastName}")
    public  String deletePlayerByLastName(@PathVariable("lastName") String lastName) {
        return playerService.deletePlayer(lastName);
    }



}