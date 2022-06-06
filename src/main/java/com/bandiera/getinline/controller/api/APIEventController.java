package com.bandiera.getinline.controller.api;

import com.bandiera.getinline.constant.ErrorCode;
import com.bandiera.getinline.constant.EventStatus;
import com.bandiera.getinline.dto.APIDataResponse;
import com.bandiera.getinline.dto.APIErrorResponse;
import com.bandiera.getinline.dto.EventRequest;
import com.bandiera.getinline.dto.EventResponse;
import com.bandiera.getinline.exception.GeneralException;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/api")
@RestController
public class APIEventController {

    @GetMapping("/events")
    public APIDataResponse<List<EventResponse>> getEvents() {
        return APIDataResponse.of(List.of(EventResponse.of(
                1L,
                "오전 공부",
                EventStatus.OPENED,
                LocalDateTime.of(2022, 6, 6, 9,0),
                LocalDateTime.of(2022, 6, 6, 11,0),
                0,
                24,
                "Spring boot Web Mcv - TDD"
        )));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/events")
    public APIDataResponse<Void> createEvent(@RequestBody EventRequest eventRequest) {
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
