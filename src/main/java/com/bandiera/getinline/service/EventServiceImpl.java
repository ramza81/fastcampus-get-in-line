package com.bandiera.getinline.service;

import com.bandiera.getinline.constant.ErrorCode;
import com.bandiera.getinline.constant.EventStatus;
import com.bandiera.getinline.dto.EventDTO;
import com.bandiera.getinline.exception.GeneralException;
import com.bandiera.getinline.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public List<EventDTO> getEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime
    ) {
        try {
            log.debug("관찰 - placeId : {}", placeId);
            return eventRepository.findEvents(placeId, eventName, eventStatus, eventStartDatetime, eventEndDatetime);
        }
        catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }

    }

    @Override
    public Optional<EventDTO> getEvent(Long eventId) {
        try {
            return eventRepository.findEvent(eventId);
        }
        catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    @Override
    public boolean createEvent(EventDTO eventDTO) {
        try {
            return eventRepository.insertEvent(eventDTO);
        }
        catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    @Override
    public boolean modifyEvent(Long eventId, EventDTO eventDTO) {
        try {
            return eventRepository.updateEvent(eventId, eventDTO);
        }
        catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    @Override
    public boolean deleteEvent(Long eventId) {
        try {
            return eventRepository.deleteEvent(eventId);
        }
        catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }
}
