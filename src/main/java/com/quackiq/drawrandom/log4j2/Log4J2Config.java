package com.quackiq.drawrandom.log4j2;

import com.quackiq.drawrandom.Constants;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Order;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.apache.logging.log4j.core.config.plugins.Plugin;

import java.net.URI;

@Plugin(name = "Log4J2Config", category = ConfigurationFactory.CATEGORY)
@Order(50)

public class Log4J2Config extends ConfigurationFactory {

    static Configuration createConfiguration(final String name, ConfigurationBuilder<BuiltConfiguration> builder) {
        builder.setConfigurationName(name);
        builder.setStatusLevel(Constants.LOG_LEVEL);
        // String Appender
        AppenderComponentBuilder string = builder.newAppender("StringOut", "StringAppender");
        string.addAttribute("name", "StringOut");
        string.addAttribute("PatternString", Constants.LOG_PATTERN);
        // Add Appenders
        builder.add(string);
        // Root Logger
        builder.add(builder.newLogger("org.apache.logging.log4j", Constants.LOG_LEVEL).
                add(builder.newAppenderRef("StringOut")).
                addAttribute("additivity", false));
        builder.add(builder.newRootLogger(Constants.LOG_LEVEL).add(builder.newAppenderRef("StringOut")));
        // Create Configuration
        return builder.build();
    }

    @Override
    protected String[] getSupportedTypes() {
        return new String[]{"*"};
    }

    @Override
    public Configuration getConfiguration(LoggerContext loggerContext, ConfigurationSource source) {
        return getConfiguration(loggerContext, source.toString(), null);
    }

    @Override
    public Configuration getConfiguration(final LoggerContext loggerContext, final String name, final URI configLocation) {
        ConfigurationBuilder<BuiltConfiguration> builder = newConfigurationBuilder();
        return createConfiguration(name, builder);
    }
}
