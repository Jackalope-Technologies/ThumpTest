/* Copyright (C) 2024 Jackalope Technologies Ltd - All Rights Reserved */
package com.jackalope.thumptest.service;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ApplicationInfoService {
    private static final String VERSION = "0.0.1";
    private static final String HOMEPAGE = "https://github.com/Jackalope-Technologies/ThumpTest";

    public String getVersion() {
        return VERSION;
    }

    public String getHomepage() {
        return HOMEPAGE;
    }
}
