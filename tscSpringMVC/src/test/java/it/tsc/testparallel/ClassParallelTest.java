package it.tsc.testparallel;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;

import it.tsc.test.dao.AllarmsDaoTest;
import it.tsc.test.scheduler.BaseSchedulerTest;

public class ClassParallelTest {

  @Ignore
  @Test
  public void test() {
    Class[] cls = { AllarmsDaoTest.class, BaseSchedulerTest.class };

    // Parallel among classes
    JUnitCore.runClasses(ParallelComputer.classes(), cls);

    System.out.println("----------------------------");

    // Parallel among methods in a class
    JUnitCore.runClasses(ParallelComputer.methods(), cls);

    System.out.println("----------------------------");

    // Parallel all methods in all classes
    JUnitCore.runClasses(new ParallelComputer(true, true), cls);
  }
}
