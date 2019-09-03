package com.server.project.comixconnexion.repositories;

import com.server.project.comixconnexion.entities.Comic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Long> {
}
