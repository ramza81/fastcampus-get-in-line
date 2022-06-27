package com.bandiera.getinline.constant;

import javax.persistence.Embeddable;

@Embeddable
public enum EventStatus {
    PENDING, OPENED, CLOSED, CANCELLED, ABORTED
}
