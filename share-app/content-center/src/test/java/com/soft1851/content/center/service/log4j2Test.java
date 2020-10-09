package com.soft1851.content.center.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author su
 * @className log4j2Test
 * @Description TODO
 * @Date 2020/10/6
 * @Version 1.0
 **/
public class log4j2Test {
    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            // 记录trace级别的信息
            logger.trace("log4j2日志输出：This is trace message1.");
            // 记录debug级别的信息
            logger.debug("log4j2日志输出：This is debug message2.");
            // 记录info级别的信息
            logger.info("log4j2日志输出：This is info message3.");
            // 记录error级别的信息
            logger.error("log4j2日志输出：This is error message4.");
        }
    }
}
