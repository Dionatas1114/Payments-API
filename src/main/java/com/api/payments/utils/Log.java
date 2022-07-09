package com.api.payments.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {

    static final Logger log = LoggerFactory.getLogger(Log.class);

    // Reset
    public static final String TEXT_RESET = "\033[0m";

    // Regular Colors
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String WHITE = "\033[0;37m";
    public static final String BLACK = "\033[0;30m";

    // Bold
    public static final String WHITE_BOLD = "\033[1;37m";
    public static final String BLACK_BOLD = "\033[1;30m";
    public static final String RED_BOLD = "\033[1;31m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String YELLOW_BOLD = "\033[1;33m";
    public static final String BLUE_BOLD = "\033[1;34m";
    public static final String PURPLE_BOLD = "\033[1;35m";
    public static final String CYAN_BOLD = "\033[1;36m";

    public static void info(String message) {
        log.info(GREEN + message + TEXT_RESET);
    }

    public static void error(String message) {
        log.info(RED + message + TEXT_RESET);
    }

    public static void debug(String message) {
        log.info(BLUE + message + TEXT_RESET);
    }

    public static void warn(String message) {
        log.info(YELLOW + message + TEXT_RESET);
    }
}
