package com.bej.service.service;

import com.bej.service.domain.Track;
import com.bej.service.exceptions.ArtistNotFoundException;
import com.bej.service.exceptions.TrackAlreadyFoundException;
import com.bej.service.exceptions.TrackNotFoundException;
import com.bej.service.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {

    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyFoundException {
        if (trackRepository.findById(track.getTrackId()).isPresent()) {
            throw new TrackAlreadyFoundException();
        }
        return trackRepository.save(track);
    }

    @Override
    public List<Track> getAllTrack() throws Exception {
        return trackRepository.findAll();
    }

    @Override
    public boolean deleteTrack(int trackId) throws TrackNotFoundException {
        boolean result = false;
        if (trackRepository.findById(trackId).isEmpty()) {
            throw new TrackNotFoundException();
        } else {
            trackRepository.deleteById(trackId);
            return true;
        }
    }

    @Override
    public List<Track> getAllTrackByTrackRating(int trackRating) throws TrackNotFoundException {
        if (trackRepository.findAllTrackByTrackRating(trackRating).isEmpty()) {
            throw new TrackNotFoundException();
        }
        return trackRepository.findAllTrackByTrackRating(trackRating);
    }

    @Override
    public List<Track> getAllTrackByArtistName(String artistName) throws ArtistNotFoundException {
        if (trackRepository.findAllTrackByArtistName(artistName).isEmpty()) {
            throw new ArtistNotFoundException();
        }
        return trackRepository.findAllTrackByArtistName(artistName);
    }
}