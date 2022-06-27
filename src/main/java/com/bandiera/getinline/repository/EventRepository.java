package com.bandiera.getinline.repository;

import com.bandiera.getinline.constant.EventStatus;
import com.bandiera.getinline.domain.Event;
import com.bandiera.getinline.domain.QEvent;
import com.querydsl.core.types.dsl.ComparableExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends
        EventReadOnlyRepository<Event, Long>,
//        JpaRepository<Event, Long>,
        QuerydslPredicateExecutor<Event>,
        QuerydslBinderCustomizer<QEvent> {

    List<Event> findByEventNameAndEventStatus(String eventName, EventStatus eventStatus);
    List<Event> findByEventStatusAndEventName(@Param("eventName") String eventName,
                                              @Param("eventStatus") EventStatus eventStatus);
    Optional<Event> findFirstByEventEndDatetimeBetween(LocalDateTime from, LocalDateTime to);

    @Override
    default void customize(QuerydslBindings bindings, QEvent root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(
                root.placeId,
                root.eventName,
                root.eventStatus,
                root.eventStartDatetime,
                root.eventEndDatetime
                );
        bindings.bind(root.eventName).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.eventStartDatetime).first(ComparableExpression::goe);
        bindings.bind(root.eventEndDatetime).first(ComparableExpression::loe);
    }

}
