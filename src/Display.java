import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import javax.swing.*;

public class Display extends JFrame implements MouseListener{
	static final int ROLLS = 1000;
	static final int CAPACITY = (int) (Math.ceil(ROLLS*1.3));
	static HashMap<String,Integer> h = new HashMap<String,Integer>(CAPACITY);
	
	public Display(){
		init();
	}
	
	public void init(){
		setSize(800,600);
		addMouseListener(this);
		initRolls();
	}
	
	public void paint(Graphics g) {
		repaint();
		initHistogram(g);
		refreshBars(g);
	}
	
	void initHistogram(Graphics g){
		g.setColor(Color.BLACK);
		g.drawLine(100, 100, 100, this.getHeight()-100);
		g.drawLine(100, this.getHeight()-100, this.getWidth()-100, this.getHeight()-100);
		g.drawString(String.valueOf(0), 90, 500); 
	}
	
	void refreshBars(Graphics g){
		
	}
	
	public static void initRolls() {
        Die roller1 = new Die(6);
        Die roller2 = new Die(6);
        Die roller3 = new Die(6);
        Die roller4 = new Die(6);
        String rollSum;
        
        for (int i = 0; i < 1000; i++) {
            rollSum = "" + roller1.roll() + roller2.roll() + roller3.roll() + roller4.roll();
            if (h.get(rollSum) == null) {
                h.put(rollSum, 1);
            } else {
                h.put(rollSum, (Integer) (h.get(rollSum)) + 1);
            }
        }
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
