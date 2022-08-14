package com.ospreycodingexercise;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvatarRepository extends CrudRepository<Avatar, Long> {
    Avatar findAvatarById(Integer id);
}
