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

    @DisplayName("검색 조건 없이 장소를 검색하면, 전체 결과를 출력하여 보여준다.")
    @Test
    void givenNothing_whenSearchingPlaces_thenReturnsEntirePlaceList() {
        // Given
        given(placeRepository.findPlaces(null, null, null, null))
                .willReturn(List.of(
                        createPlaceDTO("DGB대구은행 본점", "대구광역시 수성구 달구벌대로 2310"),
                        createPlaceDTO("대구시교육청", "대구광역시 수성구 수성로76길 11")
                ));

        // When
        List<PlaceDTO> list = sut.getPlaces(null, null, null, null);

        // Then
        assertThat(list).hasSize(2);
        then(placeRepository).should().findPlaces(null, null, null, null);


    }

    @DisplayName("검색 조건 함께 이벤트 검색하면, 검색된 결과를 출력하여 보여준다.")
    @Test
    void givenSearchParams_whenSearchingEvents_thenReturnsEventList() {
        // Given
        PlaceType placeType = PlaceType.COMMON;
        String placeName = "DGB대구은행 본점";
        String address = "대구광역시 수성구 달구벌대로 2310";
        String phoneNumber = "053-755-8760";

        given(placeRepository.findPlaces(placeType, placeName, address, phoneNumber))
                .willReturn(List.of(
                        createPlaceDTO(placeType,"DGB대구은행 본점", "대구광역시 수성구 달구벌대로 2310",phoneNumber)
                ));

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
        then(placeRepository).should().findPlaces(placeType, placeName, address, phoneNumber);


    }

    @DisplayName("장소 ID로 존재하는 장소를 조회하면, 해당 장소 정보를 출력하여 보여준다.")
    @Test
    void givenPlaceId_whenSearchingExistingPlace_thenReturnsPlace() {
        // Given
        long placeId = 1L;
        PlaceDTO placeDTO = createPlaceDTO("DGB대구은행 본점", "대구광역시 수성구 달구벌대로 2310");
        given(placeRepository.findPlace(placeId)).willReturn(Optional.of(placeDTO));

        // When
        Optional<PlaceDTO> result = sut.getPlace(placeId);

        // Then
        assertThat(result).hasValue(placeDTO);
        then(placeRepository).should().findPlace(placeId);

    }

    @DisplayName("장소 ID로 장소를 조회하면, 빈 장소 정보를 출력하여 보여준다.")
    @Test
    void givenEventId_whenSearchingNoneExistingEvent_thenReturnsEmptyEvent() {
        // Given
        long placeId = 2L;
        PlaceDTO placeDTO = createPlaceDTO("DGB대구은행 본점", "대구광역시 수성구 달구벌대로 2310");
        given(placeRepository.findPlace(placeId)).willReturn(Optional.empty());

        // When
        Optional<PlaceDTO> result = sut.getPlace(placeId);

        // Then
        assertThat(result).isEmpty();
        then(placeRepository).should().findPlace(placeId);

    }

    @DisplayName("장소 정보를 주면, 장소 정보를 생성하고 결과를 true 로 보여준다.")
    @Test
    void givenPlace_whenCreating_thenCreatesPlaceAndReturnsTrue() {
        // Given
        PlaceDTO placeDTO = createPlaceDTO("DGB대구은행 본점", "대구광역시 수성구 달구벌대로 2310");
        given(placeRepository.insertPlace(placeDTO)).willReturn(true);


        // When
        boolean result = sut.createPlace(placeDTO);

        // Then
        assertThat(result).isTrue();
        then(placeRepository).should().insertPlace(placeDTO);

    }

    @DisplayName("장소 정보를 주지 않으면, 장소 정보를 생성을 중단하고 결과를 false 로 보여준다.")
    @Test
    void givenNothing_whenCreating_thenAbortCreatingAndReturnsFalse() {
        // Given
        given(placeRepository.insertPlace(null)).willReturn(false);

        // When
        boolean result = sut.createPlace(null);

        // Then
        assertThat(result).isFalse();
        then(placeRepository).should().insertPlace(null);

    }

    @DisplayName("장소 ID와 정보를 주면, 장소 정보를 변경하고 결과를 true 로 보여준다.")
    @Test
    void givenPlaceIdAndItsInfo_whenModifying_thenModifiesPlaceAndReturnsTrue() {
        // Given
        long placeId = 1L;
        PlaceDTO placeDTO = createPlaceDTO("DGB대구은행 본점", "대구광역시 수성구 달구벌대로 2310");
        given(placeRepository.updatePlace(placeId, placeDTO)).willReturn(true);

        // When
        boolean result = sut.modifyPlace(placeId, placeDTO);

        // Then
        assertThat(result).isTrue();
        then(placeRepository).should().updatePlace(placeId, placeDTO);

    }

    @DisplayName("장소 ID를 주지 않으면, 장소 정보 변경을 중단하고 결과를 false 로 보여준다.")
    @Test
    void givenNoPlaceId_whenModifying_thenAbortModifyingAndReturnsFalse() {
        // Given
        PlaceDTO placeDTO = createPlaceDTO("DGB대구은행 본점", "대구광역시 수성구 달구벌대로 2310");
        given(placeRepository.updatePlace(null, placeDTO)).willReturn(false);


        // When
        boolean result = sut.modifyPlace(null, placeDTO);

        // Then
        assertThat(result).isFalse();
        then(placeRepository).should().updatePlace(null, placeDTO);

    }

    @DisplayName("장소 ID만 주고 변경할 정보를 주지 않으면, 장소 정보 변경을 중단하고 결과를 false 로 보여준다.")
    @Test
    void givenPlaceIdOnly_whenModifying_thenAbortModifyingAndReturnsFalse() {
        // Given
        long placeId = 1L;
        given(placeRepository.updatePlace(placeId, null)).willReturn(false);

        // When
        boolean result = sut.modifyPlace(placeId, null);

        // Then
        assertThat(result).isFalse();
        then(placeRepository).should().updatePlace(placeId, null);

    }

    @DisplayName("장소 ID를 주면, 장소 정보를 삭제하고 결과를 true 로 보여준다.")
    @Test
    void givenPlaceId_whenDeleting_thenDeletesPlaceAndReturnsTrue() {
        // Given
        long placeId = 1L;
        given(placeRepository.deletePlace(placeId)).willReturn(true);

        // When
        boolean result = sut.removePlace(placeId);

        // Then
        assertThat(result).isTrue();
        then(placeRepository).should().deletePlace(placeId);

    }

    @DisplayName("장소 ID를 주지 않으면, 장소 정보 삭제를 중단하고 결과를 false 로 보여준다.")
    @Test
    void givenNothing_whenDeleting_thenAbortDeletingAndReturnsFalse() {
        // Given
        given(placeRepository.deletePlace(null)).willReturn(false);

        // When
        boolean result = sut.removePlace(null);

        // Then
        assertThat(result).isFalse();
        then(placeRepository).should().deletePlace(null);

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