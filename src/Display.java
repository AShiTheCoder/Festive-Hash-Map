import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import javax.swing.*;

public class Display extends JFrame implements MouseListener{
	static final int ROLLS = 1000;
	static final int CAPACITY = (int) (Math.ceil(ROLLS*1.3));
	private double y_scale;
	static HashMap<String,Integer> h = new HashMap<String,Integer>(CAPACITY);
	static final int SIDES = 6;
	static final int POS = SIDES*4-3;
	private boolean erase = false;
	
	public Display(){
		init();
	}
	
	public void init(){
		setSize(1040,600);
		addMouseListener(this);
		initRolls();
		y_scale = (this.getHeight()-200.0)/200;
	}
	
	public void paint(Graphics g) {
		if (erase) {
			super.paint(g);
			erase = false;
		}
		repaint();
		initHistogram(g);
		refreshBars(g);
	}
	
	void initHistogram(Graphics g){
		g.setColor(Color.BLACK);
		g.drawLine(100, 100, 100, this.getHeight()-100); //draws Y axis
		g.drawLine(100, this.getHeight()-100, this.getWidth()-100, this.getHeight()-100); //draws X axis
		
		for (int i = 0; i < 5; i++){	//draws Y axis markings
			g.drawLine(95, ((this.getHeight()-200)/4)*i+100, 100, ((this.getHeight()-200)/4)*i+100);
		}
		for (int i = 0; i < POS+1; i++){	//draws X axis markings
			g.drawLine(((this.getWidth()-200)/POS)*i+100, this.getHeight()-100, ((this.getWidth()-200)/POS)*i+100, this.getHeight()-95);
		}
		
		for (int i = 0; i < POS; i++){	//draws X axis numbers
			g.drawString(String.valueOf(i+4), ((this.getWidth()-200)/(2*POS))*(2*i+1)+100, this.getHeight()-80); 
		}
		for (int i = 0; i < 4; i++){	//draws Y axis numbers
			g.drawString(String.valueOf((i+1)*50), 70, ((this.getHeight()-200)/4)*(4-(i+1))+105);
		}
		g.drawString(String.valueOf(0), 80, this.getHeight()-95);
		repaint();
	}
	
	void refreshBars(Graphics g){
		g.setColor(Color.BLACK);
		for (int i = 0; i < POS; i++){
			g.drawRect(((this.getWidth()-200)/POS)*i+100, (int)(this.getHeight()-h.get(""+(i+4))*y_scale-100), (this.getWidth()-200)/POS, (int)(h.get(""+(i+4))*y_scale));
		}
		g.setColor(Color.BLUE);
		for (int i = 0; i < POS; i++){
			g.fillRect(((this.getWidth()-200)/POS)*i+100+1, (int)(this.getHeight()-h.get(""+(i+4))*y_scale-100+1), (this.getWidth()-200)/POS-1, (int)(h.get(""+(i+4))*y_scale-1));
		}
	}
	
	public static void initRolls() {
        Die roller1 = new Die(SIDES);
        Die roller2 = new Die(SIDES);
        Die roller3 = new Die(SIDES);
        Die roller4 = new Die(SIDES);
        String rollSum;
        
        for (int i = 0; i < 21; i++){
        	h.put("" + (i+4), 0);
        }
        
        for (int i = 0; i < 1000; i++) {
            rollSum = "" + (roller1.roll() + roller2.roll() + roller3.roll() + roller4.roll());
            h.put(rollSum, (Integer) (h.get(rollSum)) + 1);
        }
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		erase = true;
		initRolls();
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
