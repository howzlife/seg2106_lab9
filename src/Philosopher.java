public class Philosopher extends Thread {
  private GraphicTable table;
  private Chopstick left;
  private Chopstick right;
  private int ID;
  final int timeThink_max = 0;
  final int timeNextFork = 0;
  final int timeEat_max = 0;

  Philosopher(int ID, GraphicTable table, Chopstick left, Chopstick right) {
    this.ID = ID;
    this.table = table;
    this.left = left;
    this.right = right;
    setName("Philosopher "+ID);
  }

public void run() {
    for(;;) {

    	table.isThinking(ID);
        System.out.println(getName()+" thinks");
        try {
        	sleep((long)(Math.random()*timeThink_max));
        	} catch(InterruptedException e) {
        	System.out.println(e);
        }        
        System.out.println(getName()+" finished thinking");               
 
    System.out.println(getName()+" is hungry");               
      table.isHungry(ID);

      System.out.println(getName()+" wants left chopstick");
      synchronized(left) {
    	  while (!left.isFree()) {
    		  try {left.wait();} catch (InterruptedException e) {System.out.println("Got Interrupted!");}
    	  }
      }
      left.take();
      table.takeChopstick(ID, left.getID());
      System.out.println(getName()+" got left chopstick");
      
      try {
	sleep(timeNextFork);
      } catch(InterruptedException e) {
	System.out.println(e);
      }     
      
      System.out.println(getName()+" wants right chopstick");
      synchronized(right) {
    	  while (!right.isFree()) {
    		  try { right.wait(); } catch (InterruptedException e) {
    			  System.out.println("Got interrupted!");
    		  }
    	  }
      }
      right.take();
      table.takeChopstick(ID, right.getID());
      System.out.println(getName()+" got right chopstick");

      System.out.println(getName()+" eats");               
      try {
	sleep((long)(Math.random()*timeEat_max));
      } catch(InterruptedException e) {
	System.out.println(e);
      }

      System.out.println(getName()+" finished eating");               
      
      table.releaseChopstick(ID, left.getID());
      left.release();
      left.notify();
      System.out.println(getName()+" released left chopstick");
     
      table.releaseChopstick(ID, right.getID());
      right.release();
      right.notify();
      System.out.println(getName()+" released right chopstick");

     }
  }
}