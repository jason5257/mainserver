package com.mainserver.controller.common;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class RestResult {
    private boolean success;
    private Object result;
    private String message;
}
