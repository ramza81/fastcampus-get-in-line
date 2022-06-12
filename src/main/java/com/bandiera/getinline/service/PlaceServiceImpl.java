package com.bandiera.getinline.service;

import com.bandiera.getinline.constant.PlaceType;
import com.bandiera.getinline.dto.PlaceDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class PlaceServiceImpl implements PlaceService {
    @Override
    public List<PlaceDTO> getPlaces(PlaceType placeType, String placeName, String address, String phoneNumber) {
        return List.of();
//                PlaceDTO.of(
//                        PlaceType.COMMON,
//                        "DGB대구은행 본점",
//                        "대구광역시 수성구 달구벌대로 2310",
//                        "053-755-8760",
//                        100,
//                        "은행",
//                        LocalDateTime.of(2020,6, 6, 0, 0,0),
//                        LocalDateTime.of(2020,6, 6, 0, 0,0)
//                ),
//                PlaceDTO.of(
//                        PlaceType.COMMON,
//                        "대구시교육청",
//                        "대구광역시 수성구 수성로76길 11",
//                        "053-231-0000",
//                        100,
//                        "대한민국 교육부",
//                        LocalDateTime.of(2020,6, 6, 0, 0,0),
//                        LocalDateTime.of(2020,6, 6, 0, 0,0)
//                )
//        );
    }

    @Override
    public Optional<PlaceDTO> getPlace(Long placeId) {
        return Optional.empty();
//        return Optional.of(PlaceDTO.of(
//                PlaceType.COMMON,
//                "DGB대구은행 본점",
//                "대구광역시 수성구 달구벌대로 2310",
//                "053-755-8760",
//                100,
//                "은행",
//                LocalDateTime.of(2020, 6, 6, 0, 0, 0),
//                LocalDateTime.of(2020, 6, 6, 0, 0, 0)
//        ));

    }

    @Override
    public boolean createPlace(PlaceDTO placeDTO) {
        return true;
    }

    @Override
    public boolean modifyPlace(Long placeId, PlaceDTO placeDTO) {
        return true;
    }

    @Override
    public boolean removePlace(Long placeId) {
        return true;
    }
}
