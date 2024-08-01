/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest.service;

import lombok.Getter;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18nService {
    private final Locale locale;

    @Getter
    private final ResourceBundle bundle;

    public I18nService() {
        locale = Locale.getDefault();
        // Testing
//        locale = Locale.of("eo");
        bundle = ResourceBundle.getBundle("Strings", locale);
    }

    public String getString(final String key) {
        return bundle.getString(key);
    }

}
