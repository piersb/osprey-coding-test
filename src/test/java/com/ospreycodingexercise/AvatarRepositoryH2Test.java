package com.ospreycodingexercise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

@DataJpaTest
@Sql(scripts = "/create-data.sql")
@Sql(scripts = "/cleanup-data.sql", executionPhase = AFTER_TEST_METHOD)
public class AvatarRepositoryH2Test {
    
    @Autowired
    private AvatarRepository avatarRepository;

    @Test
    public void HistoryCanBeDeleted() {
//        Iterable<Avatar> avatars;
//        
//        avatars = avatarRepository.findAll();
//        assertNotNull(avatarRepository.findAll());
//        
//        AvatarController avatarController = new AvatarController();
//        avatarController.clearHistory();
//        
//        avatars = avatarRepository.findAll();
//        assertNull(avatarRepository.findAll());
    }



}
