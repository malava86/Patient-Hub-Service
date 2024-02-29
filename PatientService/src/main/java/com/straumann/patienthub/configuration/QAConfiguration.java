package com.straumann.patienthub.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("qa")
public class QAConfiguration {
	// QA-specific configurations
}
