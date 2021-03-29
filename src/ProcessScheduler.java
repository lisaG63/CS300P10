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

import java.util.Scanner;

/**
 * This class represents a scheduler that takes in user's input schedule the processes.
 * 
 * @author Weihang Guo
 *
 */
public class ProcessScheduler {
  private int currentTime; // stores the current time after the last run
  private int numProcessesRun; // stores the number of processes run so far
  private WaitingProcessQueue queue; // this processing unit’s queue
  
  /**
   * Constructor of ProcessScheduler.
   */
  public ProcessScheduler() {
    queue = new WaitingProcessQueue();
    currentTime = 0;
    numProcessesRun = 0;
  }
  
  /**
   * This method inserts the given process in the WaitingProcessQueue queue.
   * 
   * @param process the process to be added
   */
  public void scheduleProcess(CustomProcess process) {
    queue.insert(process);
  }
  
  /**
   * Runs the ready processes already scheduled in this processScheduler’s queue
   * 
   * @return the String representing the running result
   */
  public String run() {
    String log = "";//store the processes to run
    int size = queue.size();//store the number of the processes
    
    if (size < 2) {//add the starting line to clarify how many processes is scheduled
      log = log + "Starting " + size + " process\n\n";
    } else {
      log = log + "Starting " + size + " processes\n\n";
    }
    
    for (int i = 0; i < size; i ++) {//add each processes to the log
      log = log + "Time " + currentTime + " : Process ID " + queue.peekBest().getProcessId() + 
          " Starting.\n";//add the information of a process
      currentTime += queue.peekBest().getBurstTime();
      //increase the current time after one process is completed
      log = log + "Time " + currentTime + " : Process ID " + queue.peekBest().getProcessId() + 
          " Completed.\n";//inform the user that this process is completed
      queue.removeBest();//remove the process from the queue
      numProcessesRun ++;//update the number of processes already run
    }
    log = log + "\nTime " + currentTime + ": All scheduled processes completed.\n";
    //when all the processes are completed, add a line to inform the user
    return log;
  }
  

  
  /**
   * Greet the user.
   */
  private static void greet() {
    System.out.println("========== Welcome to the SJF Process Scheduler App ========\n");
  }
  
  /**
   * Instruct the user to use this scheduler.
   */
  private static void instruction() {
    System.out.println("Enter command: \n[schedule <burstTime>] or [s <burstTime>] \n[run] or [r]\n"
        + "[quit] or [q]\n");
  }
  
  
  /**
   * Read the user's input and execute the command.
   */
  private static void read() {
    ProcessScheduler scheduler = new ProcessScheduler();
    Scanner reader = new Scanner(System.in);
    greet();
    instruction();
    String input = reader.nextLine();
    while (!(input.equals("q") || input.equals("quit"))) {
      if (input.equals("r") || input.equals("run")) {
        System.out.println(scheduler.run());
      } else if (input.split(" ").length == 2 && (input.split(" ")[0].equals("s") ||
          input.split(" ")[0].equals("schedule"))) {
        schedule(scheduler, input.split(" ")[1]);
      } else {
        System.out.println("WARNING: Please enter a valid command!\n");
      }
      instruction();
      input = reader.nextLine();
    }
    quit(scheduler);
    reader.close();

  }
  
  /**
   * Quit the scheduler.
   * @param scheduler the scheduler that the user is currently using
   */
  private static void quit(ProcessScheduler scheduler) {
    System.out.println(scheduler.numProcessesRun + " processes run in " + scheduler.currentTime + 
        " units of time!\n" + "Thank you for using our scheduler!\n" + "Goodbye!\n");
  }
  
  /**
   * Add a new process to the queue according to its burst time and process id.
   * @param scheduler the scheduler that the user is currently using
   * @param time the burst time of the process
   */
  private static void schedule(ProcessScheduler scheduler, String time) {
    try {
      int burstTime = Integer.parseInt(time);//Covert from String type to Integer type and store it
      CustomProcess process = new CustomProcess(burstTime);
      //Create a new CustomProcess with the burstTime
      scheduler.scheduleProcess(process);//schedule the process
      System.out.println("Process ID " + process.getProcessId() + " scheduled. Burst Time = " + 
          burstTime + "\n");//print to inform the user that this process is scheduled
    } catch (NumberFormatException e) {//Check if the time is an integer
      System.out.println("WARNING: burst time MUST be an integer!\n");
    } catch (IllegalArgumentException e) {//Check if the time is positive
      System.out.println(e.getMessage());
    }
  }

  
 
  /**
   * Start a scheduler for the user to use.
   * @param args input arguments
   */
  public static void main(String[] args) {
    read();
  }

}
