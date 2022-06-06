package com.bandiera.getinline.controller;

import com.bandiera.getinline.constant.EventStatus;
import com.bandiera.getinline.constant.PlaceType;
import com.bandiera.getinline.dto.EventDTO;
import com.bandiera.getinline.dto.PlaceDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/admin")
@Controller
public class AdminController {

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
        Map<String, Object> map = new HashMap<>();
        map.put("place", PlaceDTO.of(
                PlaceType.COMMON,
                "DGB대구은행 본점",
                "대구광역시 수성구 달구벌대로 2310",
                "053-755-8760",
                100,
                "은행",
                LocalDateTime.now(),
                LocalDateTime.now()
        ));

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
        Map<String, Object> map = new HashMap<>();
        map.put("event", EventDTO.of(
                1L,
                "오전 공부",
                EventStatus.OPENED,
                LocalDateTime.of(2022, 6, 6, 9,0),
                LocalDateTime.of(2022, 6, 6, 11,0),
                0,
                24,
                "Spring boot Web Mcv - TDD",
                LocalDateTime.now(),
                LocalDateTime.now()
        ));

        return new ModelAndView("admin/event-detail", map);
    }
}
