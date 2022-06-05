package com.bandiera.getinline.controller.api;

import com.bandiera.getinline.constant.ErrorCode;
import com.bandiera.getinline.dto.APIErrorResponse;
import com.bandiera.getinline.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class APIEventController {

    @GetMapping("/events")
    public List<String> getEvents() throws Exception {
        throw new HttpRequestMethodNotSupportedException("스프링 에러 테스트");
//         return List.of("event1", "event2");
    }

    @PostMapping("/events")
    public Boolean createEvent() throws Exception {
        throw new GeneralException("General 에러 테스트");
//        return true;
    }

    @GetMapping("/events/{eventId}")
    public String getEvent(@PathVariable Integer eventId) throws Exception  {
        throw new RuntimeException("런타임 에러 테스트");
//        return "event " + eventId;
    }

    @PutMapping("/events/{eventId}")
    public Boolean modifyEvent(@PathVariable Integer eventId) {
        return true;
    }
    
    @DeleteMapping("/events/{eventId}")
    public Boolean removeEvent(@PathVariable Integer eventId) {
        return true;
    }

}
