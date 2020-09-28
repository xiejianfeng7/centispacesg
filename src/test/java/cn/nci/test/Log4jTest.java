package cn.nci.test;

import org.apache.log4j.Logger;

/**
 * @program: centispacesg
 * @description: log4j测试
 * @author: xiejianfeng
 * @create: 2020-09-28 10:53
 */
public class Log4jTest {
    private static Logger logger = Logger.getLogger(Log4jTest.class);

    /**
     * @param args
     */
    public static void main(String[] args) {

        // 记录debug级别的信息
        logger.debug("This is debug message.");
        // 记录info级别的信息
        logger.info("This is info message.");
        // 记录error级别的信息
        logger.error("This is error message.");

    }
}
