package com.bej.service.controller;

import com.bej.service.domain.Track;
import com.bej.service.exceptions.ArtistNotFoundException;
import com.bej.service.exceptions.TrackAlreadyFoundException;
import com.bej.service.exceptions.TrackNotFoundException;
import com.bej.service.service.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trackDetail/api")
public class TrackController {
    private TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("/track")
    public ResponseEntity<?> insertTrack(@RequestBody Track track) throws TrackAlreadyFoundException {
        ResponseEntity<?> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(trackService.saveTrack(track), HttpStatus.CREATED);
        } catch (TrackAlreadyFoundException e) {
            throw new TrackAlreadyFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/track1")//RequestHandlerMethod
    public ResponseEntity<?> fetchAllTrack() throws Exception {
        return new ResponseEntity<>(trackService.getAllTrack(), HttpStatus.OK);
    }

    @DeleteMapping("/track2/{trackId}")
    public ResponseEntity<?> deleteTrack(@PathVariable("trackId") int trackId) throws TrackNotFoundException {
        ResponseEntity responseEntity = null;
        try {
            trackService.deleteTrack(trackId);
            responseEntity = new ResponseEntity("Successfully Deleted", HttpStatus.OK);
        } catch (TrackNotFoundException trackNotFoundException) {
            throw new TrackNotFoundException();
        } catch (Exception exception) {
            responseEntity = new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/track3/{trackRating}")
    public ResponseEntity<?> fetchByTrackRating(@PathVariable int trackRating) throws TrackNotFoundException {
        ResponseEntity responseEntity = null;
        try {
            responseEntity = new ResponseEntity(trackService.getAllTrackByTrackRating(trackRating), HttpStatus.FOUND);

        } catch (TrackNotFoundException trackNotFoundException) {
            throw new TrackNotFoundException();
        }
        return responseEntity;
    }

    @GetMapping("/track4/{artistName}")
    public ResponseEntity<?> fetchByArtistName(@PathVariable String artistName) throws ArtistNotFoundException {
        ResponseEntity responseEntity = null;
        try {
            responseEntity = new ResponseEntity(trackService.getAllTrackByArtistName(artistName), HttpStatus.FOUND);

        } catch (ArtistNotFoundException e) {
            throw new ArtistNotFoundException();
        }
        return responseEntity;
    }
}