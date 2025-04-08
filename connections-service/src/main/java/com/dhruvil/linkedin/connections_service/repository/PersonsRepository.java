package com.dhruvil.linkedin.connections_service.repository;

import com.dhruvil.linkedin.connections_service.entity.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonsRepository extends Neo4jRepository<Person, Long> {
    Optional<Person> getByName(String name);

    @Query("MATCH (personA:Person) -[:CONNECTED_TO]- (personB:Person) " +
            "WHERE personA.userId = $userId " +
            "RETURN personB")
    List<Person> getFirstDegreeConnections(Long userId);


    @Query("MATCH (personA:Person {userId: $userId}) " +
            "MATCH (personA)-[:CONNECTED_TO*3]-(personC:Person) " +
            "WHERE personC.userId <> $userId AND NOT (personA)-[:CONNECTED_TO*1..2]-(personC) " +
            "RETURN DISTINCT personC")
    List<Person> getThirdDegreeConnections(Long userId);

}
