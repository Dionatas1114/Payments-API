package com.api.payments.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {

    static final Logger log = LoggerFactory.getLogger(Log.class);

    static TextColors textColors;
    public static final String TEXT_RESET = "\033[0m";

    public static void info(String message) {
        log.info(TextColors.GREEN + message + TEXT_RESET);
    }

    public static void error(String message) {
        log.info(TextColors.RED + message + TEXT_RESET);
    }

    public static void debug(String message) {
        log.info(TextColors.BLUE + message + TEXT_RESET);
    }

    public static void warn(String message) {
        log.info(TextColors.YELLOW + message + TEXT_RESET);
    }
}
