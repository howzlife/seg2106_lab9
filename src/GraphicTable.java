import java.io.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;

public class GraphicTable extends Frame implements WindowListener {
  private Point center;
  private GraphicPlate plates[];
  private GraphicChopstick chops[];

  GraphicTable() {
    super();

    addWindowListener(this);
    setTitle("Dining Philosophers");
    setSize(200, 200);
    setBackground(Color.darkGray);

    center = new Point(getSize().width/2, getSize().height/2);

    plates = new GraphicPlate[5];
    for(int i=0; i<5; i++) {
      plates[i] = 
	  new GraphicPlate(i, center, new Point(center.x, center.y-70), 20);
    }

    chops = new GraphicChopstick[5];
    for(int i=0; i<5; i++) {
      chops[i] = new GraphicChopstick(i, center, 
				      new Point(center.x, center.y-70), 
				      new Point(center.x, center.y-40));
    }
    
    show();
    setResizable(false);
  }

  /* Window events management 
   */
  public void windowOpened(WindowEvent evt) {
  }
  public void windowClosing(WindowEvent evt) {
    System.exit(0);
  }
  public void windowClosed(WindowEvent evt) {
  }
  public void windowIconified(WindowEvent evt) {
  }
  public void windowDeiconified(WindowEvent evt) {
  }
  public void windowActivated(WindowEvent evt) {
  }
  public void windowDeactivated(WindowEvent evt) {
  }
  
  public void isHungry(int phID) {
    plates[phID].setColor(phID);
    repaint();
  }

  public void isThinking(int phID) {
    plates[phID].setColor(-1);
    repaint();
  }

  public void takeChopstick(int phID, int chID) {
    chops[chID].setColor(phID);
    repaint();
  }

  public void releaseChopstick(int phID, int chID) {
    chops[chID].setColor(-1);
    repaint();
  }

  public void paint(Graphics g) {
   for(int i=0; i<5; i++) {
      plates[i].draw(g);
      chops[i].draw(g);
    }
  }

    
}