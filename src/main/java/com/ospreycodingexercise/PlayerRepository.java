package com.ospreycodingexercise;

import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {
    Player findPlayerById(Integer id);
}
