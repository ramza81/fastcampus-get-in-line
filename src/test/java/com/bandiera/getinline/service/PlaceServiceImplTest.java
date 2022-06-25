package com.bandiera.getinline.service;

import com.bandiera.getinline.constant.PlaceType;
import com.bandiera.getinline.dto.PlaceDTO;
import com.bandiera.getinline.repository.PlaceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class PlaceServiceImplTest {

    @InjectMocks
    private PlaceServiceImpl sut;

    @Mock
    private PlaceRepository placeRepository;

    ?

    private PlaceDTO createPlaceDTO(String placeName, String address) {

        return createPlaceDTO(
                PlaceType.COMMON,
                placeName,
                address,
                "000-000-0000"
                );
    }

    private PlaceDTO createPlaceDTO(
            PlaceType placeType,
            String placeName,
            String address,
            String phoneNumber
    ) {
        return PlaceDTO.of(
                placeType,
                placeName,
                address,
                phoneNumber,
                100,
                "은행",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

}