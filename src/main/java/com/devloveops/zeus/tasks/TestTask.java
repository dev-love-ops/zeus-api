package com.devloveops.zeus.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author RockyWu
 * @date 2019-04-04
 */
@Service
public class TestTask {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MyAsyncTask myAsyncTask;

    @Scheduled(fixedRate = 1000, initialDelay = 5000)
    public void test1(){
        logger.info("rockywu");
    }
}
