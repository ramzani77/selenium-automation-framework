package com.sameer.test.retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static final Logger log = LogManager.getLogger(RetryAnalyzer.class);

    private int count = 0;
    private static final int MAX_RETRY = 2;

    @Override
    public boolean retry(ITestResult result) {

        if (count < MAX_RETRY) {
            count++;
            log.warn("Retrying test: {} | Attempt: {}", result.getName(), count);
            return true;
        }

        return false;
    }
}