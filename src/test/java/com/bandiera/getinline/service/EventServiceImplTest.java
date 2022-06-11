package com.bandiera.getinline.service;

import com.bandiera.getinline.constant.EventStatus;
import com.bandiera.getinline.dto.EventDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class EventServiceImplTest {

    private EventServiceImpl sut;

    @BeforeEach
    void setUp() {
        sut = new EventServiceImpl();
    }

    @DisplayName("검색 조건 없이 이벤트 검색하면, 전체 결과를 출력하여 보여준다.")
    @Test
    void givenNothing_whenSearchingEvents_thenReturnsEntireEventList() {
        // Given


        // When
        List<EventDTO> list = sut.getEvents(null, null, null, null, null);

        // Then
        assertThat(list).hasSize(2);

    }

    @DisplayName("검색 조건 함께 이벤트 검색하면, 검색된 결과를 출력하여 보여준다.")
    @Test
    void givenSearchParams_whenSearchingEvents_thenReturnsEventList() {
        // Given
        Long placeId = 1L;
        String eventName = "오전 공부";
        EventStatus eventStatus = EventStatus.OPENED;
        LocalDateTime eventStartDatetime = LocalDateTime.of(2022, 6, 6, 9, 0, 0);
        LocalDateTime eventEndDatetime = LocalDateTime.of(2022, 6, 6, 11, 0, 0, 0);

        // When
        List<EventDTO> list = sut.getEvents(placeId, eventName, eventStatus, eventStartDatetime, eventEndDatetime);

        // Then
        assertThat(list)
                .hasSize(1)
                .allSatisfy(event -> {
                    assertThat(event)
                            .hasFieldOrPropertyWithValue("placeId", placeId)
                            .hasFieldOrPropertyWithValue("eventName", eventName)
                            .hasFieldOrPropertyWithValue("eventStatus",eventStatus);
                    assertThat(event.eventStartDatetime()).isAfterOrEqualTo(eventStartDatetime);
                    assertThat(event.eventStartDatetime()).isBeforeOrEqualTo(eventStartDatetime);
                });

    }

    @DisplayName("이벤트 ID로 존재하는 이벤트를 조회하면, 해당 이벤트 정보를 출력하여 보여준다.")
    @Test
    void givenEventId_whenSearchingExistingEvent_thenReturnsEvent() {
        // Given
        long eventId = 1L;
        EventDTO eventDTO = createEventDTO(1L, "오전 공부", true);

        // When
        Optional<EventDTO> result = sut.getEvent(eventId);

        // Then
        assertThat(result).hasValue(eventDTO);

    }

    @DisplayName("이벤트 ID로 이벤트를 조회하면, 빈 이벤트 정보를 출력하여 보여준다.")
    @Test
    void givenEventId_whenSearchingNoneExistingEvent_thenReturnsEmptyEvent() {
        // Given
        long eventId = 2L;
        EventDTO eventDTO = createEventDTO(1L, "오전 공부", true);

        // When
        Optional<EventDTO> result = sut.getEvent(eventId);

        // Then
        assertThat(result).isEmpty();

    }

    @DisplayName("이벤트 ID와 정보를 주면, 이벤트 정보를 변경하고 결과를 true 로 보여준다.")
    @Test
    void givenEventIdAndItsInfo_whenModifying_thenModifiesEventAndReturnsTrue() {
        // Given
        long eventId = 1L;
        EventDTO eventDTO = createEventDTO(1L, "오전 공부", false);

        // When
        boolean result = sut.modifyEvent(eventId, eventDTO);

        // Then
        assertThat(result).isTrue();

    }

    @DisplayName("이벤트 ID를 주지 않으면, 이벤트 정보 변경을 중단하고 결과를 false 로 보여준다.")
    @Test
    void givenNoEventId_whenModifying_thenAbortModifyingAndReturnsFalse() {
        // Given
        EventDTO eventDTO = createEventDTO(1L, "오전 공부", false);

        // When
        boolean result = sut.modifyEvent(null, eventDTO);

        // Then
        assertThat(result).isFalse();

    }

    @DisplayName("이벤트 ID만 주고 변경할 정보를 주지 않으면, 이벤트 정보 변경을 중단하고 결과를 false 로 보여준다.")
    @Test
    void givenEventIdOnly_whenModifying_thenAbortModifyingAndReturnsFalse() {
        // Given
        long eventId = 1L;

        // When
        boolean result = sut.modifyEvent(eventId, null);

        // Then
        assertThat(result).isFalse();

    }

    @DisplayName("이벤트 ID를 주면, 이벤트 정보를 삭제하고 결과를 true 로 보여준다.")
    @Test
    void givenEventId_whenDeleting_thenDeletesEventAndReturnsTrue() {
        // Given
        long eventId = 1L;

        // When
        boolean result = sut.deleteEvent(eventId);

        // Then
        assertThat(result).isTrue();

    }

    @DisplayName("이벤트 ID를 주지 않으면, 이벤트 정보 삭제를 중단하고 결과를 false 로 보여준다.")
    @Test
    void givenNothing_whenDeleting_thenAbortDeletingAndReturnsFalse() {
        // Given

        // When
        boolean result = sut.deleteEvent(null);

        // Then
        assertThat(result).isFalse();

    }

    private EventDTO createEventDTO(long placeId, String eventName, boolean isMorning) {
        String hourStart = isMorning ? "09" : "13";
        String hourEnd = isMorning ? "11" : "16";

        return createEventDTO(
                placeId,
                eventName,
                EventStatus.OPENED,
                LocalDateTime.parse("2020-06-06T%s:00:00".formatted(hourStart)),
                LocalDateTime.parse("2020-06-06T%s:00:00".formatted(hourEnd))
        );
    }

    private EventDTO createEventDTO(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime
    ) {
        return EventDTO.of(
                placeId,
                eventName,
                eventStatus,
                eventStartDatetime,
                eventEndDatetime,
                0,
                24,
                "Spring boot Web Mcv - TDD",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

}