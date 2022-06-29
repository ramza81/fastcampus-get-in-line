package com.bandiera.getinline.controller;

import com.bandiera.getinline.constant.EventStatus;
import com.bandiera.getinline.constant.PlaceType;
import com.bandiera.getinline.dto.EventDto;
import com.bandiera.getinline.dto.PlaceDto;
import com.bandiera.getinline.service.EventServiceImpl;
import com.bandiera.getinline.service.PlaceServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/admin")
@Controller
public class AdminController {

    private final PlaceServiceImpl placeService;
    private final EventServiceImpl eventService;

    public AdminController(PlaceServiceImpl placeService, EventServiceImpl eventService) {
        this.placeService = placeService;
        this.eventService = eventService;
    }

    @GetMapping("/places")
    public ModelAndView adminPlaces(
            PlaceType placeType,
            String placeName,
            String address
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("placeType", placeType);
        map.put("placeName", placeName);
        map.put("address", address);

        return new ModelAndView("admin/places", map);
    }

    @GetMapping("/places/{placeId}")
    public ModelAndView adminPlaceDetail(@PathVariable Long placeId) {
        Optional<PlaceDto> placeDto = placeService.getPlace(placeId);
        Map<String, Object> map = new HashMap<>();
        map.put("place", placeDto);

        return new ModelAndView("admin/place-detail", map);
    }

    @GetMapping("/events")
    public ModelAndView adminEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventStartDateTime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventEndDateTime
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("placeName", "place-" + placeId);
        map.put("eventName", eventName);
        map.put("eventStatus", eventStatus);
        map.put("eventStartDateTime", eventStartDateTime);
        map.put("eventEndDateTime", eventEndDateTime);

        return new ModelAndView("admin/events", map);
    }

    @GetMapping("/events/{eventId}")
    public ModelAndView adminEventDetail(@PathVariable Long eventId) {
        Optional<EventDto> event = eventService.getEvent(eventId);

        Map<String, Object> map = new HashMap<>();
        map.put("event", event);

        return new ModelAndView("admin/event-detail", map);
    }
}
