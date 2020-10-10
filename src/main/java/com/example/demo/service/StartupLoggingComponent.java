package com.example.demo.service;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartupLoggingComponent implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String ENV_TARGET_KEY = "envTarget";
    private static final String PERSISTENCE_TARGET_KEY = "persistenceTarget";
    private static final String PERSISTENCE_HOST_KEY = "spring.datasource.url";
    private static final String PERSISTENCE_USER_NAME = "spring.datasource.username";

    @Autowired
    private Environment env;

    @Override
    public void afterPropertiesSet() throws Exception {

        logger.info("============================================================================");
        try {
            logEnvTarget(env);
            logPersistenceTarget(env);
            logPersistenceData(env);
        } catch (final Exception ex) {
            logger.warn("There was a problem logging data on startup", ex);
        }

        logger.info("============================================================================");
    }

    private void logEnvTarget(final Environment env) {

        final String envTarget = getValueOfProperty(env, ENV_TARGET_KEY, "dev", Lists.newArrayList("dev"));
        logger.info("{} = {}", ENV_TARGET_KEY, envTarget);

    }

    private void logPersistenceTarget(final Environment environment) {

        final String persistenceTarget = getValueOfProperty(environment, PERSISTENCE_TARGET_KEY, "h2", Lists.newArrayList("h2", "mysql"));
        logger.info("{} = {}", PERSISTENCE_TARGET_KEY, persistenceTarget);

    }

    private void logPersistenceData(final Environment environment) {

        final String persistenceHost = getValueOfProperty(environment, PERSISTENCE_HOST_KEY, "not-found", null);
        logger.info("{} = {}", PERSISTENCE_HOST_KEY, persistenceHost);
        logger.info("With user name for DB as: {}", environment.getProperty(PERSISTENCE_USER_NAME));

    }

    private final String getValueOfProperty(final Environment env,
                                            final String propertyKey,
                                            final String propertyDefaultValue,
                                            final List<String> acceptablePropertyValues){

        String propValue = env.getProperty(propertyKey);

        if (propValue == null) {
            propValue = propertyDefaultValue;
            logger.info("The {} doesn't have an explicit value; default value is = {}", propertyKey, propertyDefaultValue);
        }

        if (acceptablePropertyValues != null) {
            if (!acceptablePropertyValues.contains(propValue)) {
                logger.warn("The property = {} has an invalid value = {}", propertyKey, propValue);
            }
        }

        if (propValue == null) {
            logger.warn("The property = {} is null", propertyKey);
        }

        return propValue;

    }

}
