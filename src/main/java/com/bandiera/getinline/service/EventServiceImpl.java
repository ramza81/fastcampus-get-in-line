package com.bandiera.getinline.service;

import com.bandiera.getinline.constant.EventStatus;
import com.bandiera.getinline.dto.EventDTO;
import com.bandiera.getinline.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        return eventRepository.findEvents();
    }

    @Override
    public Optional<EventDTO> getEvent(Long eventId) {
        return Optional.empty();
    }

    @Override
    public boolean createEvent(EventDTO eventDTO) {
        return true;
    }

    @Override
    public boolean modifyEvent(Long EventId, EventDTO eventDTO) {
        return false;
    }

    @Override
    public boolean deleteEvent(Long EventId) {
        return false;
    }
}
