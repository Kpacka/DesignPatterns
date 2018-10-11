package design.patterns.builder;

import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;

public class LoggerBuilder {

    private Logger utilLogger;
    private Class requestingClass;


    public LoggerBuilder(Class requestingClass) {
        this.requestingClass = requestingClass;
        utilLogger = Logger.getLogger(requestingClass.getCanonicalName());
    }

    public LoggerBuilder withFilter(Filter filter) {
        utilLogger.setFilter(filter);
        return this;
    }

    public LoggerBuilder logAtLevel(Level logLevel) {
        utilLogger.setLevel(logLevel);

        //In order to fine tune the log level we must override the root
        // handlers log level

        Logger rootLogger = Logger.getLogger("");

        for (Handler handler : rootLogger.getHandlers()) {
            handler.setLevel(logLevel);
        }

        return this;
    }

    public org.slf4j.Logger build() {
        return LoggerFactory.getLogger(requestingClass.getCanonicalName());
    }

}