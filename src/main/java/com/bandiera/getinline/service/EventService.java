package com.bandiera.getinline.service;

import com.bandiera.getinline.constant.EventStatus;
import com.bandiera.getinline.dto.EventDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 이벤트 서비스
 *
 * @author Bandiera
 */
public interface EventService {
    /**
     * 검색어를 받아서 이벤트 리스트를 반환
     *
     * @param placeId 장소 ID
     * @param eventName 이벤트 이름
     * @param eventStatus 이벤트 상태
     * @param eventStartDatetime 시작시간
     * @param eventEndDatetime 종료시간
     * @return
     */
    List<EventDTO> getEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime
    );

    /**
     *  이벤트 ID를 받아서 이벤트를 반환
     *
     * @param eventId 이벤트 ID
     * @return
     */
    Optional<EventDTO> getEvent(Long eventId);

    /**
     * 이벤트 정보를 받아서 생성 후 결과를 반환
     *
     * @param eventDTO 이벤트 정보
     * @return
     */
    boolean createEvent(EventDTO eventDTO);

    /**
     * 이벤트 ID와 이벤트 정보를 받아서 수정 후 결과를 반환
     *
     * @param eventId 이벤트 ID
     * @param eventDTO 이벤트 정보
     * @return
     */
    boolean modifyEvent(Long eventId, EventDTO eventDTO);

    /**
     * 이벤트 ID를 받아서 삭제 후 결과를 반환
     *
     * @param eventId 이벤트 ID
     * @return
     */
    boolean deleteEvent(Long eventId);
}
