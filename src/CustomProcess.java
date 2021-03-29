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
 * This class represents a CustomProcess with its burst time.
 * 
 * @author Weihang Guo
 *
 */
public class CustomProcess implements java.lang.Comparable<CustomProcess>{
  private static int nextProcessId = 1; // stores the id to be assigned to the next process
                                        //to be created
  private final int PROCESS_ID; // unique identifier for this process
  private int burstTime; // time required by this process for CPU execution
  
  /**
   * Initializes the nextProcessId to zero. This method can be called at the beginning of the test
   * methods.
   */
  static void initNextProcessID() {
  CustomProcess.nextProcessId = 1;
  }
  
  /** 
   * Compares two process according to their burst time and process ID.
   * @return 0 if they are the same, a negative int (<0) if this process is less than the argument, 
   * a positive int (>0) if this process is greater than the argument.
   * @author Weihang Guo
   */
  @Override
  public int compareTo(CustomProcess o) {
    if(getBurstTime() != o.getBurstTime()) {//if the burst times are different
      return getBurstTime() - (o.getBurstTime());
    }
    //if the burst times are the same, compare the process ID.
    return toString().compareTo(o.toString());
  }
  
  /**
   * Returns a String representation of this CustomProcess
   * @return a string representation of this CustomProcess
   */
  public String toString() {
    return "p" + this.PROCESS_ID + "(" + this.burstTime + ")";
  }
  
  /**
   * Constructor for CustomProcess that takes in a burst time as input.
   * @param burstTime the running time of the process
   * @throws IllegalArgumentException
   */
  public CustomProcess(int burstTime) { 
    if (!(burstTime > 0)) {//Check if the burstTime is positive
      throw new IllegalArgumentException("WARNING: burst time must be positive!\n");
    }
    PROCESS_ID = nextProcessId;
    nextProcessId ++;
    this.burstTime = burstTime;
  }
  
  /**
   * Returns PROCESS_ID.
   * @return PROCESS_ID
   */
  public int getProcessId() {
    return PROCESS_ID;
  }
  
  /**
   * Returns burstTime.
   * @return burstTime
   */
  public int getBurstTime() {
    return burstTime;
  }

}
