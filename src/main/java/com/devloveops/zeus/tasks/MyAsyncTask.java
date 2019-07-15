package com.devloveops.zeus.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author RockyWu
 * @date 2019-04-04
 */
@Service
public class MyAsyncTask {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Async
    public void test() {
        try {
            logger.info("************Async****************");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Async
    public Future<String> test1() {
        try {
            logger.info("************Async****************");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>("OK");
    }
}
