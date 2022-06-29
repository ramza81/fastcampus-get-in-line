package com.bandiera.getinline.constant;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPlaceType is a Querydsl query type for PlaceType
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QPlaceType extends EnumPath<PlaceType> {

    private static final long serialVersionUID = -725251497L;

    public static final QPlaceType placeType = new QPlaceType("placeType");

    public QPlaceType(String variable) {
        super(PlaceType.class, forVariable(variable));
    }

    public QPlaceType(Path<PlaceType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlaceType(PathMetadata metadata) {
        super(PlaceType.class, metadata);
    }

}

