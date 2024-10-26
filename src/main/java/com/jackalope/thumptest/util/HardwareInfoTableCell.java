/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest.util;

public record HardwareInfoTableCell(String key, String value) {
    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
