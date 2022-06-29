package com.bandiera.getinline.constant;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEventStatus is a Querydsl query type for EventStatus
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QEventStatus extends EnumPath<EventStatus> {

    private static final long serialVersionUID = -1110354142L;

    public static final QEventStatus eventStatus = new QEventStatus("eventStatus");

    public QEventStatus(String variable) {
        super(EventStatus.class, forVariable(variable));
    }

    public QEventStatus(Path<EventStatus> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEventStatus(PathMetadata metadata) {
        super(EventStatus.class, metadata);
    }

}

