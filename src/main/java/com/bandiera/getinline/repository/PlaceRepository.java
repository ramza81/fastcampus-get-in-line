package com.bandiera.getinline.repository;

import com.bandiera.getinline.domain.Place;
import com.bandiera.getinline.domain.QPlace;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

// TODO : 인스턴스 생성 편의를 위해 임시로 default 사용, repository layer 구현이 완성되면 삭제할 것
public interface PlaceRepository extends
        JpaRepository<Place, Long>,
        QuerydslPredicateExecutor<Place>,
        QuerydslBinderCustomizer<QPlace> {
    @Override
    default void customize(QuerydslBindings bindings, QPlace root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(
                root.placeType,
                root.placeName,
                root.phoneNumber
        );
        bindings.bind(root.placeName).first(StringExpression::like);
        bindings.bind(root.address).first(StringExpression::like);
        bindings.bind(root.phoneNumber).first(StringExpression::like);

    }
}
