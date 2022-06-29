package com.bandiera.getinline.service;

import com.bandiera.getinline.constant.PlaceType;
import com.bandiera.getinline.dto.PlaceDto;
import com.querydsl.core.types.Predicate;

import java.util.List;
import java.util.Optional;

/**
 * 장소 서비스
 *
 * @author BANDIERA
 */
public interface PlaceService {

    /**
     * 검색어를 받아서 장소 리스트를 반환
     *
     * @param predicate
     * @return
     */
    List<PlaceDto> getPlaces(Predicate predicate);

    /**
     * 장소 ID를 받아서 장소를 반환
     *
     * @param placeId 장소 ID
     * @return
     */
    Optional<PlaceDto> getPlace(Long placeId);

    /**
     * 장소 정보를 받아서 생성 후 결과를 반환
     *
     * @param placeDTO 장소 정보
     * @return
     */
    boolean createPlace(PlaceDto placeDTO);

    /**
     * 장소 ID와 장소 정보를 받아서 수정 후 결과를 반환
     *
     * @param placeId 장소 ID
     * @param placeDTO 장소 정보
     * @return
     */
    boolean modifyPlace(Long placeId, PlaceDto placeDTO);

    /**
     * 장소 ID를 받아서 삭제 후 결과를 반환
     *
     * @param placeId 장소 ID
     * @return
     */
    boolean removePlace(Long placeId);
}
