// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.spring.sample.aad.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ClientController {

    @Value("${client-secret}")
    private String clientSecret;

    @GetMapping("/")
    @ResponseBody
    public String get() {
        return clientSecret;
    }
}
