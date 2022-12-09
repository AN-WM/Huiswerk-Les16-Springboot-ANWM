package com.example.HwLes11ANWM.repositories;

import com.example.HwLes11ANWM.models.TelevisionWallBracket;
import com.example.HwLes11ANWM.models.TelevisionWallBracketKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface TelevisionWallBracketRepository extends JpaRepository<TelevisionWallBracket, TelevisionWallBracketKey> {
    // custom query om alle TelevisionWallBrackets te vinden die bij een bepaalde tv horen
    Collection<TelevisionWallBracket> findAllByTelevisionId(Long televisionId);
    // custom query om alle TelevisionWallBrackets te vinden die bij een bepaalde wallbracket horen
    Collection<TelevisionWallBracket> findAllByWallBracketId(Long wallBracketId);
}
