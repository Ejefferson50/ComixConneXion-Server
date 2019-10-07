package com.server.project.comixconnexion.repositories;

import com.server.project.comixconnexion.entities.Comic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Long> {

    Optional<Comic> findComicBySeriesTitleAndIssue(String seriesTitle, Integer issue);
}
