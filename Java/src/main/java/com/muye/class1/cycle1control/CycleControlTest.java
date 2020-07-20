package com.muye.class1.cycle1control;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : gwh
 * @Desc:  break、continue、return
 * @date : 2020-06-09 10:58
 **/
public class CycleControlTest {

    Logger logger = LoggerFactory.getLogger(CycleControlTest.class);

    /**
     * 直接结束一个循环，跳出循环体。break以后的循环体中的语句不会继续执行，循环体外面的会执行
     */
    @Test
    public void testBreak() {
        for (int i = 0; i < 3; i++) {
            logger.info("i = "+i);
            if (i == 1) {
                break;
            }
            logger.info("break 后 ");
        }
        logger.info("循环结束 ");
    }

    /**
     * 中止本次循环，继续下次循环。continue以后的循环体中的语句不会继续执行，下次循环继续执行，循环体外面的会执行
     */
    @Test
    public void testContinue(){
        for (int i = 0; i < 3; i++) {
            logger.info("i = " + i);
            if (i == 1) {
                continue;
            }
            logger.info("continue 后 ");
        }
        logger.info("循环结束 ");
    }

    /**
     * return的功能是结束一个方法。 一旦在循环体内执行return，将会结束该方法，
     * 循环自然也随之结束。与continue和break不同的是，return直接结束整个方法，不管这个return处于多少层循环之内。
     */
    @Test
    public void testReturn(){
        for (int i = 0; i < 3; i++) {
            logger.info("i = " + i);
            if (i == 1) {
                return;
            }
            logger.info("return 后");
        }
        logger.info("循环结束");
    }



}
