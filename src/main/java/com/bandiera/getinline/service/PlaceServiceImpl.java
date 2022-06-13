package com.bandiera.getinline.service;

import com.bandiera.getinline.constant.PlaceType;
import com.bandiera.getinline.dto.PlaceDTO;
import com.bandiera.getinline.repository.EventRepository;
import com.bandiera.getinline.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;

    @Override
    public List<PlaceDTO> getPlaces(PlaceType placeType, String placeName, String address, String phoneNumber) {
        return placeRepository.findPlaces(placeType, placeName, address, phoneNumber);
    }

    @Override
    public Optional<PlaceDTO> getPlace(Long placeId) {
        return placeRepository.findPlace(placeId);
    }

    @Override
    public boolean createPlace(PlaceDTO placeDTO) {
        return placeRepository.insertPlace(placeDTO);
    }

    @Override
    public boolean modifyPlace(Long placeId, PlaceDTO placeDTO) {
        return placeRepository.updatePlace(placeId, placeDTO);
    }

    @Override
    public boolean removePlace(Long placeId) {
        return placeRepository.deletePlace(placeId);
    }
}