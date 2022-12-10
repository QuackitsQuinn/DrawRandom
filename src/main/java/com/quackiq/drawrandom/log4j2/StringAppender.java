package com.quackiq.drawrandom.log4j2;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.AbstractOutputStreamAppender;
import org.apache.logging.log4j.core.appender.OutputStreamManager;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/**
 * A Custom Appender for Log4j2 that logs to a String. This is useful for testing logging.
 *
 * @author rewolf
 */
@Plugin(name = "StringAppender", category = "Core", elementType = "appender", printObject = true)
public class StringAppender extends AbstractOutputStreamAppender<StringAppender.StringOutputStreamManager> {
    private static final LoggerContext context = (LoggerContext) LogManager.getContext(false);
    private static final Configuration configuration = context.getConfiguration();
    private final StringOutputStreamManager manager;

    private StringAppender(String name, Layout<? extends Serializable> layout, StringOutputStreamManager manager, boolean ignoreExceptions, boolean immediateFlush) {
        super(name, layout, null, ignoreExceptions, immediateFlush, null, manager);
        this.manager = manager;
    }

    /**
     * Create a StringAppender with a given output format
     *
     * @param nullablePatternString Can be {@code null}. The PatternLayout string for log output.
     * @return a new StringAppender
     */
    @PluginFactory
    public static StringAppender createStringAppender(@PluginAttribute("name") String name,
                                                      @PluginAttribute("PatternString") String nullablePatternString) {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        final PatternLayout layout;

        if (nullablePatternString == null) {
            layout = PatternLayout.createDefaultLayout();
        } else {
            layout = PatternLayout.newBuilder()
                    .withPattern(nullablePatternString)
                    .build();
        }

        return new StringAppender(
                name,
                layout,
                new StringOutputStreamManager(outputStream, "StringStream", layout),
                false,
                true);
    }

    public void addToLogger(final String loggerName, final Level level) {
        LoggerConfig loggerConfig = configuration.getLoggerConfig(loggerName);
        loggerConfig.addAppender(this, level, null);
        context.updateLoggers();
    }

    public void removeFromLogger(final String loggerName) {
        LoggerConfig loggerConfig = configuration.getLoggerConfig(loggerName);
        loggerConfig.removeAppender("StringAppender");
        context.updateLoggers();
    }

    public String getOutput() {
        manager.flush();
        return manager.getStream().toString();
    }

    /**
     * StringOutputStreamManager to manage an in memory byte-stream representing our stream
     */
    static class StringOutputStreamManager extends OutputStreamManager {
        ByteArrayOutputStream stream;

        StringOutputStreamManager(ByteArrayOutputStream os, String streamName, Layout<?> layout) {
            super(os, streamName, layout, false);
            stream = os;
        }

        ByteArrayOutputStream getStream() {
            return stream;
        }
    }
}
