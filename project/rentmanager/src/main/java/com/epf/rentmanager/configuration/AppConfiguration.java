package com.epf.rentmanager.configuration;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.epf.rentmanager.persistence.ConnectionManager;

@Configuration
@ComponentScan({ "com.epf.rentmanager.service", "com.epf.rentmanager.dao", "com.epf.rentmanager.persistence" })
public class AppConfiguration {
	@Bean
	public Connection jdbcConnection() throws SQLException {
		return new ConnectionManager().getConnection();
	}
}
