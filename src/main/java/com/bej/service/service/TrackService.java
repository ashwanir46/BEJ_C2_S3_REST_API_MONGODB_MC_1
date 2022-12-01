package com.bej.service.service;


import com.bej.service.domain.Track;
import com.bej.service.exceptions.ArtistNotFoundException;
import com.bej.service.exceptions.TrackAlreadyFoundException;
import com.bej.service.exceptions.TrackNotFoundException;

import java.util.List;

public interface TrackService {
    Track saveTrack(Track track) throws TrackAlreadyFoundException;

    List<Track> getAllTrack() throws Exception;

    boolean deleteTrack(int trackId) throws TrackNotFoundException;

    List<Track> getAllTrackByTrackRating(int trackRating) throws TrackNotFoundException;

    List<Track> getAllTrackByArtistName(String artistName) throws ArtistNotFoundException;

}