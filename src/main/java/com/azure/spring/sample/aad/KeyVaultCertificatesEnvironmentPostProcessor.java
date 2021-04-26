// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.spring.sample.aad;

import com.azure.spring.keyvault.KeyVaultOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

/**
 * Leverage {@link EnvironmentPostProcessor} to add Key Store property source.
 */
@Order(0)
@Configuration
public class KeyVaultCertificatesEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        MutablePropertySources propertySources = environment.getPropertySources();
        KeyVaultOperation kv = (KeyVaultOperation) propertySources.get("azurekv").getSource();
        String clientSecret = kv.getProperty("client-secret");
        Properties properties = new Properties();
        properties.put("azure.activedirectory.client-secret", clientSecret);
        propertySources.addFirst(new PropertiesPropertySource("test", properties));
    }
}
