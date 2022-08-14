package com.ospreycodingexercise;

import org.springframework.data.repository.CrudRepository;

public interface AvatarRepository extends CrudRepository<Avatar, Long> {
    Avatar findPlayerById(Integer id);
}
