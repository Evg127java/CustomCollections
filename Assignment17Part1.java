package com.shpp.p2p.cs.ekondratiuk.assignment17;

import static com.shpp.p2p.cs.ekondratiuk.assignment17.Constants.*;

import com.shpp.p2p.cs.ekondratiuk.assignment17.tests.SuiteTestClass;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Runs package of tests from test classes for each collection
 * To run specific tests of collections go to the "tests" package
 * Each class contains test for a corresponding collection
 */

public class Assignment17Part1 {

    public static void main(String[] args) {

        System.out.println(STARTING_MESSAGE);
        Result result = JUnitCore.runClasses(SuiteTestClass.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(ENDING_MESSAGE);
        System.out.println(TEST_CASES_NUMBER + result.getRunCount());
        System.out.println(FAILS + result.getFailureCount());
    }
}