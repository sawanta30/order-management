package com.oms.manage.enums;

import java.io.Serializable;

public enum OrderStatus implements Serializable {
    NEW,PROCESSING,FAILED,DELIVERED,IN_TRANSIT,OUT_FOR_DELIVERY
}
