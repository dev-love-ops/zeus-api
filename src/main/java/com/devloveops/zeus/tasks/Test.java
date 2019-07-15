package com.devloveops.zeus.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author RockyWu
 * @date 2019-04-04
 */
@Service
public class Test {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MyAsyncTask myAsyncTask;

    public void test(){
        logger.info("before");
        myAsyncTask.test();
        logger.info("after");
    }
}
