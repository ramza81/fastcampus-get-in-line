package com.bandiera.getinline.controller;

import com.bandiera.getinline.constant.PlaceType;
import com.bandiera.getinline.domain.Place;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
        Place place = new Place();
        place.setId(1l);
        place.setPlaceType(PlaceType.COMMON);
        place.setPlaceName("대구은행역");
        place.setAddress("대구광역시 수성구 수성동4가");
        place.setCapacity(10);
        place.setMemo("대구은행역 대구2호선");
        place.setPhoneNumber("000-000-0000");
        map.put("place", place);

        return new ModelAndView("admin/place-detail", map);
    }

    @GetMapping("/events")
    public String adminEvents() {
        return "admin/events";
    }

    @GetMapping("/events/{eventId}")
    public String adminEventDetail(@PathVariable Integer eventId) {
        return "admin/event-detail";
    }
}
