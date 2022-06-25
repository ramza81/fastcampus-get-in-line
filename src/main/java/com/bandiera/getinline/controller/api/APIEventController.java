package com.bandiera.getinline.controller.api;

import com.bandiera.getinline.constant.EventStatus;
import com.bandiera.getinline.dto.APIDataResponse;
import com.bandiera.getinline.dto.EventRequest;
import com.bandiera.getinline.dto.EventResponse;
import com.bandiera.getinline.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
//@Validated
@RequiredArgsConstructor
//@RequestMapping("/api")
//@RestController
public class APIEventController {

    private final EventService eventService;

    @GetMapping("/events")
    public APIDataResponse<List<EventResponse>> getEvents(
            @Positive Long placeId,
            @Size(min = 2) String eventName,
            EventStatus eventStatus,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventStartDatetime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventEndDatetime
    ) {
        List<EventResponse> responses = eventService.getEvents(
                placeId,
                eventName,
                eventStatus,
                eventStartDatetime,
                eventEndDatetime
        ).stream().map(EventResponse::from).toList();

        return APIDataResponse.of(responses);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/events")
    public APIDataResponse<Void> createEvent(@Validated @RequestBody EventRequest eventRequest) {
        log.debug("보고 싶은 값: {}", eventRequest);
        return APIDataResponse.empty();
    }

    @GetMapping("/events/{eventId}")
    public APIDataResponse<EventResponse> getEvent(@PathVariable Long eventId) {
        if (eventId.equals(2L)) {
            return APIDataResponse.empty();
        }

        return APIDataResponse.of(EventResponse.of(
                1L,
                "오전 공부",
                EventStatus.OPENED,
                LocalDateTime.of(2022, 6, 6, 9,0),
                LocalDateTime.of(2022, 6, 6, 11,0),
                0,
                24,
                "Spring boot Web Mcv - TDD"
        ));
    }

    @PutMapping("/events/{eventId}")
    public APIDataResponse<Void> modifyEvent(
            @PathVariable Long eventId,
            @RequestBody EventRequest eventRequest
    ) {
        return APIDataResponse.empty();
    }
    
    @DeleteMapping("/events/{eventId}")
    public APIDataResponse<Void> removeEvent(@PathVariable Long eventId) {
        return APIDataResponse.empty();
    }

}
