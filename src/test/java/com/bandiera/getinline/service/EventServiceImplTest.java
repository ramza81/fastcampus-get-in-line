package com.bandiera.getinline.service;

import com.bandiera.getinline.constant.ErrorCode;
import com.bandiera.getinline.constant.EventStatus;
import com.bandiera.getinline.dto.EventDTO;
import com.bandiera.getinline.exception.GeneralException;
import com.bandiera.getinline.repository.EventRepository;
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
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;

@DisplayName("비즈니스 로직 - 이벤트")
@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {

    @InjectMocks
    private EventServiceImpl sut;

    @Mock
    private EventRepository eventRepository;

//    @DisplayName("검색 조건 없이 이벤트 검색하면, 전체 결과를 출력하여 보여준다.")
//    @Test
//    void givenNothing_whenSearchingEvents_thenReturnsEntireEventList() {
//        // Given
//        given(eventRepository.findEvents(null, null, null, null, null))
//                .willReturn(List.of(
//                        createEventDTO(1L, "오전 공부", true),
//                        createEventDTO(1L,  "오후 운동", false)
//                ));
//
//
//        // When
//        List<EventDTO> list = sut.getEvents(null, null, null, null, null);
//
//        // Then
//        assertThat(list).hasSize(2);
//        verify(eventRepository).findEvents(null, null, null, null, null);
//        then(eventRepository).should().findEvents(null, null, null, null, null);
//
//    }
//
//    @DisplayName("이벤트를 검색하는데 에러가 발생한 경우, 줄서기 프로젝트 기본 에러로 전환하여 에외를 던진다.")
//    @Test
//    void givenDataRelatedException_whenSearchingEvents_thenThrowsGeneralException() {
//        // Given
//        RuntimeException e = new RuntimeException("This is test.");
//        given(eventRepository.findEvents(any(), any(), any(), any(), any())).willThrow(e);
//
//
//        // When
//        Throwable thrown = catchThrowable(() -> sut.getEvents(null, null, null, null, null));
//
//        // Then
//        assertThat(thrown)
//                .isInstanceOf(GeneralException.class)
//                .hasMessageContaining(ErrorCode.DATA_ACCESS_ERROR.getMessage());
//        then(eventRepository).should().findEvents(any(), any(), any(), any(), any());
//
//    }
//
//    @DisplayName("검색 조건 함께 이벤트 검색하면, 검색된 결과를 출력하여 보여준다.")
//    @Test
//    void givenSearchParams_whenSearchingEvents_thenReturnsEventList() {
//        // Given
//        Long placeId = 1L;
//        String eventName = "오전 공부";
//        EventStatus eventStatus = EventStatus.OPENED;
//        LocalDateTime eventStartDatetime = LocalDateTime.of(2022, 6, 6, 9, 0, 0);
//        LocalDateTime eventEndDatetime = LocalDateTime.of(2022, 6, 6, 11, 0, 0, 0);
//
//        given(eventRepository.findEvents(placeId, eventName, eventStatus, eventStartDatetime, eventEndDatetime))
//                .willReturn(List.of(
//                        createEventDTO(1L, "오전 공부", eventStatus, eventStartDatetime, eventEndDatetime)
//                ));
//
//        // When
//        List<EventDTO> list = sut.getEvents(placeId, eventName, eventStatus, eventStartDatetime, eventEndDatetime);
//
//        // Then
//        assertThat(list)
//                .hasSize(1)
//                .allSatisfy(event -> {
//                    assertThat(event)
//                            .hasFieldOrPropertyWithValue("placeId", placeId)
//                            .hasFieldOrPropertyWithValue("eventName", eventName)
//                            .hasFieldOrPropertyWithValue("eventStatus",eventStatus);
//                    assertThat(event.eventStartDatetime()).isAfterOrEqualTo(eventStartDatetime);
//                    assertThat(event.eventStartDatetime()).isBeforeOrEqualTo(eventEndDatetime);
//                });
//
//        then(eventRepository).should().findEvents(placeId, eventName, eventStatus, eventStartDatetime, eventEndDatetime);
//
//
//    }
//
//    @DisplayName("이벤트 ID로 존재하는 이벤트를 조회하면, 해당 이벤트 정보를 출력하여 보여준다.")
//    @Test
//    void givenEventId_whenSearchingExistingEvent_thenReturnsEvent() {
//        // Given
//        long eventId = 1L;
//        EventDTO eventDTO = createEventDTO(1L, "오전 공부", true);
//        given(eventRepository.findEvent(eventId)).willReturn(Optional.of(eventDTO));
//
//        // When
//        Optional<EventDTO> result = sut.getEvent(eventId);
//
//        // Then
//        assertThat(result).hasValue(eventDTO);
//        then(eventRepository).should().findEvent(eventId);
//
//    }
//
//    @DisplayName("이벤트 ID로 이벤트를 조회하면, 빈 이벤트 정보를 출력하여 보여준다.")
//    @Test
//    void givenEventId_whenSearchingNoneExistingEvent_thenReturnsEmptyEvent() {
//        // Given
//        long eventId = 2L;
//        EventDTO eventDTO = createEventDTO(1L, "오전 공부", true);
//        given(eventRepository.findEvent(eventId)).willReturn(Optional.empty());
//
//
//        // When
//        Optional<EventDTO> result = sut.getEvent(eventId);
//
//        // Then
//        assertThat(result).isEmpty();
//        then(eventRepository).should().findEvent(eventId);
//
//    }
//
//    @DisplayName("이벤트 정보를 주면, 이벤트 정보를 생성하고 결과를 true 로 보여준다.")
//    @Test
//    void givenEvent_whenCreating_thenCreatesEventAndReturnsTrue() {
//        // Given
//        EventDTO eventDTO = createEventDTO(1L, "오전 공부", false);
//        given(eventRepository.insertEvent(eventDTO)).willReturn(true);
//
//        // When
//        boolean result = sut.createEvent(eventDTO);
//
//        // Then
//        assertThat(result).isTrue();
//        then(eventRepository).should().insertEvent(eventDTO);
//
//    }
//
//    @DisplayName("이벤트 정보를 주지 않으면, 이벤트 정보를 생성을 중단하고 결과를 false 로 보여준다.")
//    @Test
//    void givenNothing_whenCreating_thenAbortCreatingAndReturnsFalse() {
//        // Given
//        given(eventRepository.insertEvent(null)).willReturn(false);
//
//        // When
//        boolean result = sut.createEvent(null);
//
//        // Then
//        assertThat(result).isFalse();
//        then(eventRepository).should().insertEvent(null);
//
//    }
//
//    @DisplayName("이벤트 ID와 정보를 주면, 이벤트 정보를 변경하고 결과를 true 로 보여준다.")
//    @Test
//    void givenEventIdAndItsInfo_whenModifying_thenModifiesEventAndReturnsTrue() {
//        // Given
//        long eventId = 1L;
//        EventDTO eventDTO = createEventDTO(1L, "오전 공부", false);
//        given(eventRepository.updateEvent(eventId, eventDTO)).willReturn(true);
//
//
//        // When
//        boolean result = sut.modifyEvent(eventId, eventDTO);
//
//        // Then
//        assertThat(result).isTrue();
//        then(eventRepository).should().updateEvent(eventId, eventDTO);
//
//    }
//
//    @DisplayName("이벤트 ID를 주지 않으면, 이벤트 정보 변경을 중단하고 결과를 false 로 보여준다.")
//    @Test
//    void givenNoEventId_whenModifying_thenAbortModifyingAndReturnsFalse() {
//        // Given
//        EventDTO eventDTO = createEventDTO(1L, "오전 공부", false);
//        given(eventRepository.updateEvent(null, eventDTO)).willReturn(false);
//
//
//        // When
//        boolean result = sut.modifyEvent(null, eventDTO);
//
//        // Then
//        assertThat(result).isFalse();
//        then(eventRepository).should().updateEvent(null, eventDTO);
//
//    }
//
//    @DisplayName("이벤트 ID만 주고 변경할 정보를 주지 않으면, 이벤트 정보 변경을 중단하고 결과를 false 로 보여준다.")
//    @Test
//    void givenEventIdOnly_whenModifying_thenAbortModifyingAndReturnsFalse() {
//        // Given
//        long eventId = 1L;
//        given(eventRepository.updateEvent(eventId, null)).willReturn(false);
//
//
//        // When
//        boolean result = sut.modifyEvent(eventId, null);
//
//        // Then
//        assertThat(result).isFalse();
//        then(eventRepository).should().updateEvent(eventId, null);
//
//    }
//
//    @DisplayName("이벤트 ID를 주면, 이벤트 정보를 삭제하고 결과를 true 로 보여준다.")
//    @Test
//    void givenEventId_whenDeleting_thenDeletesEventAndReturnsTrue() {
//        // Given
//        long eventId = 1L;
//        given(eventRepository.deleteEvent(eventId)).willReturn(true);
//
//        // When
//        boolean result = sut.deleteEvent(eventId);
//
//        // Then
//        assertThat(result).isTrue();
//        then(eventRepository).should().deleteEvent(eventId);
//
//    }
//
//    @DisplayName("이벤트 ID를 주지 않으면, 이벤트 정보 삭제를 중단하고 결과를 false 로 보여준다.")
//    @Test
//    void givenNothing_whenDeleting_thenAbortDeletingAndReturnsFalse() {
//        // Given
//        given(eventRepository.deleteEvent(null)).willReturn(false);
//
//        // When
//        boolean result = sut.deleteEvent(null);
//
//        // Then
//        assertThat(result).isFalse();
//        then(eventRepository).should().deleteEvent(null);
//
//    }
//
//    private EventDTO createEventDTO(long placeId, String eventName, boolean isMorning) {
//        String hourStart = isMorning ? "09" : "13";
//        String hourEnd = isMorning ? "11" : "16";
//
//        return createEventDTO(
//                placeId,
//                eventName,
//                EventStatus.OPENED,
//                LocalDateTime.parse("2020-06-06T%s:00:00".formatted(hourStart)),
//                LocalDateTime.parse("2020-06-06T%s:00:00".formatted(hourEnd))
//        );
//    }

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