package com.bej.service.repository;


import com.bej.service.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends MongoRepository<Track, Integer> {
    @Query("{'trackRating':{$gt: ?0}}")
    List<Track> findAllTrackByTrackRating(int trackRating);

    @Query("{'trackArtist.artistName':{$in:[?0]}}")
    List<Track> findAllTrackByArtistName(String artistName);
}