package com.bandiera.getinline.repository;

import com.bandiera.getinline.dto.PlaceDTO;

import java.util.List;
import java.util.Optional;

// TODO : 인스턴스 생성 편의를 위해 임시로 default 사용, repository layer 구현이 완성되면 삭제할 것
public interface PlaceRepository {

    default List<PlaceDTO> findPlace() { return List.of(); }
    default Optional<PlaceDTO> findEvent(Long placeId) { return Optional.empty(); }
    default boolean insertPlace(PlaceDTO placeDTO) { return false; }
    default boolean updatePlace(Long placeId, PlaceDTO dto) { return false; }
    default boolean deletePlace(Long placeId) { return false; }

}
