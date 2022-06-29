package com.bandiera.getinline.integration;

import com.bandiera.getinline.dto.EventDto;
import com.bandiera.getinline.service.EventServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EventServiceSociableTest {

    @Autowired
    private EventServiceImpl sut;

    @DisplayName("검색 조건 없이 이벤트 검색하면, 전체 결과를 출력하여 보여준다.")
    @Test
    void givenNothing_whenSearchingEvents_thenReturnsEntireEventList() {
        // Given

        // When
        List<EventDto> list = sut.getEvents(3L, null, null, null, null);

        // Then
        assertThat(list).hasSize(0);

    }

}
