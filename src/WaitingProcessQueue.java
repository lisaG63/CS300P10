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
 * A class that represents a waiting process queue that contains some CustomProcess.
 * 
 * @author Weihang Guo
 *
 */
public class WaitingProcessQueue implements WaitingQueueADT<CustomProcess> {
  
  private static final int INITIAL_CAPACITY = 20; //the initial capacity of this 
                                                  //waiting process queue   
  private CustomProcess[] data; //min heap-array storing the CustomProcesses
                                //inserted in this WaitingProcessQueue.
                                //data is an oversize array
  private int size; //number of CustomProcesses stored in this WaitingProcessQueue
  
  /**
   * Constructor of WaitingProcessQueue. Creates an empty array with the initial capacity.
   */
  public WaitingProcessQueue() {
    size = 0;//the initial size is 0
    data = new CustomProcess[INITIAL_CAPACITY];
  }

  /**
   * Switch places of the two processes.
   * @param index1 the first process
   * @param index2 the second process
   */
  private void swap(int index1, int index2) {
    CustomProcess temp = data[index1];//store the data at the first index
    //swap the two processes
    data[index1] = data[index2];
    data[index2] = temp;
  }
  
  /**
   * Compare the process at the given index to the processes above it and change the their positions 
   * accordingly.
   * @param index the index of the process to be compared
   */
  private void minHeapPercolateUp(int index) { 
    while (index > 0) {//when the CustomProcess at the given index has a parent
      if (data[index].compareTo(data[(index - 1) / 2]) < 0) {
        //if the process is smaller than its parent
        swap(index, (index - 1) / 2);//swap them
      }
      index = (index - 1) / 2;
    }
  }
  
  /**
   * Compare the process at the given index to the processes below it and change the their positions 
   * accordingly.
   * @param index the index of the process to be compared
   */
  private void minHeapPercolateDown(int index) {
    while (2 * index + 1 < size) {
      //when the CustomProcess at the given index has a left child
      if (2 * index + 2 < size) {
      //when the CustomProcess at the given index also has a right child
        if (data[2 * index + 2].compareTo(data[2 * index + 1]) > 0) {
        //if the left child is the smallest child
          if (data[index].compareTo(data[2 * index + 1]) > 0) {
          //if the process is smaller than its left child
            swap(index, (2 * index + 1));//swap them
            index = 2 * index + 1;//update index
          }
          return;
        } else {//if the right child is the smallest child
          if (data[index].compareTo(data[2 * index + 2]) > 0) {
            //if the process is smaller than its right child
            swap(index, (2 * index +2));//swap them
            index = 2 * index + 2;//update index
          }
          return;        
        }
      } else if (data[index].compareTo(data[2 * index + 1]) > 0) {
        //if the process only has a left child and is smaller than its left child
        swap(index, (2 * index + 1));//swap them
        index = 2 * index + 1;//update index
      }
      return;
    }
  }
  
  /**
   * Double the length of the array data.
   */
  private void expand() {
    CustomProcess[] copy = new CustomProcess[data.length * 2];
    //create a new array with twice the original length
    for (int i = 0; i < size; i ++) {
      copy[i] = data[i];//copy all the elements in the original array
    }
    data = copy;//assign the new array to data
  }
  
  /**
   * inserts a new CustomProcess in this WaitingProcessQueue.
   * 
   * @param process to insert in this WaitingProcessQueue
   */
  @Override
  public void insert(CustomProcess process) {
    if (size == 0) {//when there is no element in data
      data[0] = process;//put the CustomProcess at the root
    } else {//when there is at least one element in data
      if (data.length == size) {//if the array is full
        expand();//expand the array
      } 
      data[size] = process;//assign the new CustomProcess at the next index
      minHeapPercolateUp(size );
      //compare the new CustomProcess to the processes above it to put it in the right place
    }
    size ++;//update the size
  }
  
  /**
   * removes and returns the CustomProcess with the highest priority.
   * 
   * @return the removed CustomProcess
   * @throws java.util.NoSuchElementException with a descriptive error message if this 
   * WaitingProcessQueue is empty
   */
  @Override
  public CustomProcess removeBest() {
    if (size == 0) {//if the WaitingProcessQueue is empty
      throw new java.util.NoSuchElementException("There is no process.");
      //throw NoSuchElementException
    } else if (size == 1){//if the WaitingProcessQueue has only one element
      CustomProcess removedData = data[0];//store the original process at index 0 
      data[0] = null;//remove the only process
      size --;//update size
      return removedData;
    } 
    //when the WaitingProcessQueue has more than one element
    CustomProcess removedData = data[0];//store the original process at index 0
    data[0] = data[size - 1];//assign the last element to the first element
    data[size - 1] = null;//remove the last element
    size --;//update size
    minHeapPercolateDown(0);//find the right position for the first element
    return removedData;
  }
  
  /**
   * returns without removing the CustomProcess with the highest priority.
   * 
   * @return the CustomProcess with the highest priority
   * @throws java.util.NoSuchElementException with a descriptive error message if this
   * WaitingProcessQueue is empty
   */
  @Override
  public CustomProcess peekBest() {
    if (size == 0) {//if the WaitingProcessQueue is empty
      throw new java.util.NoSuchElementException("There is no waiting process.");
      //throw NoSuchElementException
    }
    return data[0];
  }

  /**
   * returns size of WaitingProcessQueue
   * 
   * @return the size of WaitingProcessQueue
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * checks whether this WaitingProcessQueue is empty or not.
   * 
   * @return true if this WaitingProcessQueue is empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    if (size == 0) {
      return true;
    }
    return false;
  }
  
  /**
   * Return a String representation of all the non-null elements (custom processes)
   * stored in the array data.
   * 
   * @return String representation of all the non-null elements (custom processes)
   * stored in the array data.
   */
  @Override
  public String toString() {
    if (size ==0) {//if the array data is empty
      return "";
    } 
    //if the array data has elements
    String processes = "";//store the String representation
    for (int i = 0; i < size; i ++) {
      processes = processes + data[i].toString() + " ";
      //concatenate the CustomProcess in the array data
    }
    return processes;
  }
  
}
