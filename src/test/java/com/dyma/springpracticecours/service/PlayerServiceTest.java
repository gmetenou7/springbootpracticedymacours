package com.dyma.springpracticecours.service;

import com.dyma.springpracticecours.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;
    
    private PlayerService playerService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        playerService = new PlayerService(playerRepository);
    }

    @Test
    public void shouldReturnPlayer(){}

}
