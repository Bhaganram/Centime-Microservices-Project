/*
 * Author: Srividya Ramaswamy
 * Since: 8 Jul 2020
 *
 * COPYRIGHT NOTICE:
 * Copyright (c) 2009 Infosys Technologies Limited, Electronic City,
 * Hosur Road, Bangalore - 560 100, India.
 * All Rights Reserved.
 * This software is the confidential and proprietary information of
 * Infosys Technologies Ltd. ("Confidential Information"). You shall
 * not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered
 * into with Infosys.
 */

package com.centime.l1.commons.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Default security configuration class.
 */
@EnableWebSecurity
@Configuration
public class DefaultConfig {

	/**
	 * Configures the security filter chain to permit all requests.
	 *
	 * @param http the HttpSecurity object to configure
	 * @return the configured SecurityFilterChain
	 * @throws Exception if an error occurs during configuration
	 */
	@Bean
	public SecurityFilterChain defaultConfigSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
				.cors(AbstractHttpConfigurer::disable)
				.csrf(AbstractHttpConfigurer::disable);

		return http.build();
	}
}