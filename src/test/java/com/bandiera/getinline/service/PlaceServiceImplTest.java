package com.bandiera.getinline.service;

import com.bandiera.getinline.constant.PlaceType;
import com.bandiera.getinline.dto.PlaceDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class PlaceServiceImplTest {

    private PlaceServiceImpl sut;

    @BeforeEach
    void setUp() {
        sut = new PlaceServiceImpl();
    }

    @DisplayName("검색 조건 없이 장소를 검색하면, 전체 결과를 출력하여 보여준다.")
    @Test
    void givenNothing_whenSearchingPlaces_thenReturnsEntirePlaceList() {
        // Given


        // When
        List<PlaceDTO> list = sut.getPlaces(null, null, null, null);

        // Then
        assertThat(list).hasSize(2);

    }

    @DisplayName("검색 조건 함께 이벤트 검색하면, 검색된 결과를 출력하여 보여준다.")
    @Test
    void givenSearchParams_whenSearchingEvents_thenReturnsEventList() {
        // Given
        PlaceType placeType = PlaceType.COMMON;
        String placeName = "DGB대구은행 본점";
        String address = "대구광역시 수성구 달구벌대로 2310";
        String phoneNumber = "053-755-8760";

        // When
        List<PlaceDTO> list = sut.getPlaces(placeType, placeName, address, phoneNumber);

        // Then
        assertThat(list)
                .hasSize(1)
                .allSatisfy(place -> {
                    assertThat(place)
                            .hasFieldOrPropertyWithValue("placeType", placeType)
                            .hasFieldOrPropertyWithValue("placeName", placeName)
                            .hasFieldOrPropertyWithValue("address",address)
                            .hasFieldOrPropertyWithValue("phoneNumber",phoneNumber);
                });

    }

    @DisplayName("장소 ID로 존재하는 장소를 조회하면, 해당 장소 정보를 출력하여 보여준다.")
    @Test
    void givenPlaceId_whenSearchingExistingPlace_thenReturnsPlace() {
        // Given
        long placeId = 1L;
        PlaceDTO placeDTO = createPlaceDTO("DGB대구은행 본점", "대구광역시 수성구 달구벌대로 2310");

        // When
        Optional<PlaceDTO> result = sut.getPlace(placeId);

        // Then
        assertThat(result).hasValue(placeDTO);

    }

    @DisplayName("장소 ID로 장소를 조회하면, 빈 장소 정보를 출력하여 보여준다.")
    @Test
    void givenEventId_whenSearchingNoneExistingEvent_thenReturnsEmptyEvent() {
        // Given
        long placeId = 2L;

        // When
        Optional<PlaceDTO> result = sut.getPlace(placeId);

        // Then
        assertThat(result).isEmpty();

    }

    @DisplayName("장소 정보를 주면, 장소 정보를 생성하고 결과를 true 로 보여준다.")
    @Test
    void givenPlace_whenCreating_thenCreatesPlaceAndReturnsTrue() {
        // Given
        PlaceDTO placeDTO = createPlaceDTO("DGB대구은행 본점", "대구광역시 수성구 달구벌대로 2310");

        // When
        boolean result = sut.createPlace(placeDTO);

        // Then
        assertThat(result).isTrue();

    }

    @DisplayName("장소 정보를 주지 않으면, 장소 정보를 생성을 중단하고 결과를 false 로 보여준다.")
    @Test
    void givenNothing_whenCreating_thenAbortCreatingAndReturnsFalse() {
        // Given


        // When
        boolean result = sut.createPlace(null);

        // Then
        assertThat(result).isFalse();

    }

    @DisplayName("장소 ID와 정보를 주면, 장소 정보를 변경하고 결과를 true 로 보여준다.")
    @Test
    void givenPlaceIdAndItsInfo_whenModifying_thenModifiesPlaceAndReturnsTrue() {
        // Given
        long placeId = 1L;
        PlaceDTO placeDTO = createPlaceDTO("DGB대구은행 본점", "대구광역시 수성구 달구벌대로 2310");

        // When
        boolean result = sut.modifyPlace(placeId, placeDTO);

        // Then
        assertThat(result).isTrue();

    }

    @DisplayName("장소 ID를 주지 않으면, 장소 정보 변경을 중단하고 결과를 false 로 보여준다.")
    @Test
    void givenNoPlaceId_whenModifying_thenAbortModifyingAndReturnsFalse() {
        // Given
        PlaceDTO placeDTO = createPlaceDTO("DGB대구은행 본점", "대구광역시 수성구 달구벌대로 2310");

        // When
        boolean result = sut.modifyPlace(null, placeDTO);

        // Then
        assertThat(result).isFalse();

    }

    @DisplayName("장소 ID만 주고 변경할 정보를 주지 않으면, 장소 정보 변경을 중단하고 결과를 false 로 보여준다.")
    @Test
    void givenPlaceIdOnly_whenModifying_thenAbortModifyingAndReturnsFalse() {
        // Given
        long placeId = 1L;

        // When
        boolean result = sut.modifyPlace(placeId, null);

        // Then
        assertThat(result).isFalse();

    }

    @DisplayName("장소 ID를 주면, 장소 정보를 삭제하고 결과를 true 로 보여준다.")
    @Test
    void givenPlaceId_whenDeleting_thenDeletesPlaceAndReturnsTrue() {
        // Given
        long placeId = 1L;

        // When
        boolean result = sut.removePlace(placeId);

        // Then
        assertThat(result).isTrue();

    }

    @DisplayName("장소 ID를 주지 않으면, 장소 정보 삭제를 중단하고 결과를 false 로 보여준다.")
    @Test
    void givenNothing_whenDeleting_thenAbortDeletingAndReturnsFalse() {
        // Given

        // When
        boolean result = sut.removePlace(null);

        // Then
        assertThat(result).isFalse();

    }

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