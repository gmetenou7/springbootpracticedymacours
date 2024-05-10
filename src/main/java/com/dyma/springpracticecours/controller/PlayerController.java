package com.dyma.springpracticecours.controller;

import com.dyma.springpracticecours.Player;
import com.dyma.springpracticecours.errors.ErrorResponse;
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

import java.util.Collections;
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
                    responseCode = "200", description = "return players list",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Player.class))
                    )}
            ),
            @ApiResponse(responseCode = "404", description = "Players was not found.",
                    content = {@Content(mediaType = "application/json"/*,
                            schema = @Schema(implementation = Error.class)*/)})
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
            @ApiResponse(responseCode = "404", description = "Player with specified last name was not found.",
                    content = {@Content(mediaType = "application/json"/*,
                            schema = @Schema(implementation = Error.class)*/)})
    })
    @GetMapping("{lastName}")
    public Player getPlayerByLastName(@PathVariable("lastName") String lastName) {
        return playerService.getPlayerByLastName(lastName);
    }


    @Operation(summary = "Create a player", description = "Post a player")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201", description = "return player",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Player.class)
                    )}
            ),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PostMapping
    public  Player createPlayer(@Valid @RequestBody Player player) {
        return player;
    }


    @Operation(summary = "update a player", description = "update a player")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "return player",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Player.class)
                    )}
            )
    })
    @PutMapping("{lastName}")
    public  Player updatePlayer(@Valid @RequestBody Player player, @PathVariable("lastName") String lastName) {
        return player;
    }


    @Operation(summary = "delete a player", description = "delete a player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "delete player")
    })
    @DeleteMapping("{lastName}")
    public  void deletePlayerByLastName(@PathVariable("lastName") String lastName) {
        return ;
    }



}
