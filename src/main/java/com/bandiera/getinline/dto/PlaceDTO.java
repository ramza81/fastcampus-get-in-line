package com.bandiera.getinline.dto;

import com.bandiera.getinline.constant.PlaceType;

public record PlaceDTO(
        PlaceType placeType,
        String placeName,
        String address,
        String phoneNumber,
        Integer capacity,
        String memo
) {
    public static PlaceDTO of (
            PlaceType placeType,
            String placeName,
            String address,
            String phoneNumber,
            Integer capacity,
            String memo
    ) {
        return new PlaceDTO(placeType, placeName, address, phoneNumber, capacity, memo);
    }
}

