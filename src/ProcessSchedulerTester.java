//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P10 SJF Process Scheduler
// Files:           ProcessScheduler.java, ProcessSchedulerTester.java, 
//                  CustomProcess.java, WaitingProcessQueue.java
// Course:          CS300, fall, 2019
//
// Author:          Weihang Guo
// percentage:           wguo63@wisc.edu
// Lecturer's Name: Mouna Kacem
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Milks: None
// Online Sources: None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This class tests the methods in the CustomProcess class and the WaitingProcessQueue class.
 * 
 * @author Weihang Guo
 *
 */
public class ProcessSchedulerTester {

  /**
   * Checks the correctness of the insert operation implemented in the WaitingProcessQueue class
   * 
   * @return true when the method runs correctly, false otherwise.
   */
  public static boolean testInsertWaitingProcessQueue() {
    CustomProcess.initNextProcessID();//initialize the process ID
    WaitingProcessQueue test = new WaitingProcessQueue();
    test.insert(new CustomProcess(3));
    if (!test.toString().equals( "p1(3) ")) {
      return false;
    }
    test.insert(new CustomProcess(4));
    test.insert(new CustomProcess(2));
    if(!test.toString().equals("p3(2) p2(4) p1(3) ")) {
      return false;
    }
    return true;
  }
  /** 
   * Checks the correctness of the removeBest operation implemented in the WaitingProcessQueue class
   * @return true when the method runs correctly, false otherwise.
   */
  public static boolean testRemoveBestWaitingProcessQueue() {
    CustomProcess.initNextProcessID();//initialize the process ID
    WaitingProcessQueue test = new WaitingProcessQueue();
    test.insert(new CustomProcess(3));
    test.insert(new CustomProcess(5));
    test.insert(new CustomProcess(4));
    test.insert(new CustomProcess(7));
    if (!test.removeBest().toString().equals("p1(3)")) {
      return false;
    } else if (!test.toString().equals("p3(4) p2(5) p4(7) ")) {
      return false;
    }
    return true;
  }
  
  /**
   * Checks the correctness of the compareTo method implemented in the CusTomProcess class
   * 
   * @returntrue when the method runs correctly, false otherwise.
   */
  public static boolean testCompareToCustomProcess() {
    CustomProcess.initNextProcessID();//initialize the process ID
    CustomProcess test1 = new CustomProcess(30);
    CustomProcess test2 = new CustomProcess(10);
    CustomProcess test3 = new CustomProcess(10);
    if (test1.compareTo(test2) < 0 || test1.compareTo(test2) == 0){
      return false;//test processes with different burst time
    } else if (test2.compareTo(test3) > 0 || test2.compareTo(test3) == 0) {
      return false;//test processes with same burst time
    }
    return true;
  }
  
  public static boolean testExpandWaitingProcessQueue() {
    CustomProcess.initNextProcessID();//initialize the process ID
    WaitingProcessQueue test = new WaitingProcessQueue();
    String result = "";//store the correct result

    //insert 21 customProcesses(the initial capacity is 20)
    for (int i = 1; i < 22; i ++) {
      test.insert(new CustomProcess(i));
      result = result + "p" + i + "(" + i + ") ";
    }
    if (!test.toString().equals(result)) {
      return false;
    } else if (test.size() != 21) {
      return false;
    }
    return true;
  }
  
  
  public static void main(String[] args) {
    System.out.println(testInsertWaitingProcessQueue() + " " + testRemoveBestWaitingProcessQueue()
        + " " + testCompareToCustomProcess() + " " + testExpandWaitingProcessQueue());

  }

}
