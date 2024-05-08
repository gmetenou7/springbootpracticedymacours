package com.dyma.springpracticecours.controller;

import com.dyma.springpracticecours.Player;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Tag(name = "Tennis Players Api")
@RestController
@RequestMapping("/players")
public class PlayerController {

    @Operation(summary = "Finds player", description = "fetch all player")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "return players list",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Player.class))
                    )}
            )
    })
    @GetMapping
    public List<Player> playerList() {
        return Collections.emptyList();
    }


    @Operation(summary = "get specific player", description = "fetch specific player")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "return player who match for lastName",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Player.class)
                    )}
            )
    })
    @GetMapping("{lastName}")
    public Player getPlayerByLastName(@PathVariable("lastName") String lastName) {
        return null;
    }


    @Operation(summary = "Create a player", description = "Post a player")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201", description = "return player",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Player.class)
                    )}
            )
    })
    @PostMapping
    public  Player createPlayer(@RequestBody Player player) {
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
    @PutMapping
    public  Player updatePlayer(@RequestBody Player player) {
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
