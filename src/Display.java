import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import javax.swing.*;

public class Display extends JFrame implements MouseListener{
	private int rolls;
	private int capacity = (int) (Math.ceil(rolls*1.3));
	private double y_scale;
	private HashMap<String,Integer> h = new HashMap<String,Integer>(capacity);
	private int sides;
	private int pos;
	private boolean erase = false;
	private Die[] rollers;
	private int dice;
	private int max = 0;
	
	public Display(int r, int s, Die[] d){
		rolls = r;
		sides = s;
		rollers = d;
		dice = rollers.length;
		pos = (sides-1)*dice+1;
		init();
	}
	
	public void init(){
		setSize(1040,600);
		addMouseListener(this);
		initrolls();
		findMax();
		y_scale = (this.getHeight()-200.0)/max;
	}
	
	public void paint(Graphics g) {
		if (erase) {
			super.paint(g);
			erase = false;
		}
		repaint();
		initHistogram(g);
		refreshBars(g);
		y_scale = (this.getHeight()-200.0)/max;
	}
	
	void initHistogram(Graphics g){
		g.setColor(Color.BLACK);
		g.drawLine(100, 100, 100, this.getHeight()-100); //draws Y axis
		g.drawLine(100, this.getHeight()-100, this.getWidth()-100, this.getHeight()-100); //draws X axis
		
		for (int i = 0; i < (max/50)+1; i++){	//draws Y axis markings
			g.drawLine(95, ((this.getHeight()-200)/(max/50))*i+100, 100, ((this.getHeight()-200)/(max/50))*i+100);
		}
		for (int i = 0; i < pos+1; i++){	//draws X axis markings
			g.drawLine(((this.getWidth()-200)/pos)*i+100, this.getHeight()-100, ((this.getWidth()-200)/pos)*i+100, this.getHeight()-95);
		}
		
		for (int i = 0; i < pos; i++){	//draws X axis numbers
			g.drawString(String.valueOf(i+dice), ((this.getWidth()-200)/(2*pos))*(2*i+1)+100, this.getHeight()-80); 
		}
		for (int i = 0; i < (max/50); i++){	//draws Y axis numbers
			g.drawString(String.valueOf((i+1)*50), 70, ((this.getHeight()-200)/(max/50))*((max/50)-(i+1))+105);
		}
		g.drawString(String.valueOf(0), 80, this.getHeight()-95);
		repaint();
	}
	
	void refreshBars(Graphics g){
		g.setColor(Color.BLACK);
		for (int i = 0; i < pos; i++){
			g.drawRect(((this.getWidth()-200)/pos)*i+100, (int)(this.getHeight()-h.get(""+(i+dice))*y_scale-100), (this.getWidth()-200)/pos, (int)(h.get(""+(i+dice))*y_scale));
		}
		g.setColor(Color.BLUE);
		for (int i = 0; i < pos; i++){
			g.fillRect(((this.getWidth()-200)/pos)*i+100+1, (int)(this.getHeight()-h.get(""+(i+dice))*y_scale-100+1), (this.getWidth()-200)/pos-1, (int)(h.get(""+(i+dice))*y_scale-1));
		}
		g.setColor(Color.BLACK);
		for (int i = 0; i < pos; i++){	
			g.drawString(String.valueOf(h.get(""+(i+dice))), ((this.getWidth()-200)/(2*pos))*(2*i+1)+95, (int)(this.getHeight()-h.get(""+(i+dice))*y_scale-110)); 
		}
	}
	
	public void initrolls() {
		int totalSum;
       
		for (int i = 0; i < dice; i++){
			rollers[i] = new Die(sides);
		}
		
        String rollsum;
        
        for (int i = 0; i < pos; i++){
        	h.put("" + (i+dice), 0);
        }
        
        for (int i = 0; i < rolls; i++) {
        	totalSum = 0;
        	for (int j = 0; j < dice; j++){
    			totalSum = totalSum + rollers[j].roll();
    		}
            rollsum = "" + totalSum;
            h.put(rollsum, (Integer) (h.get(rollsum)) + 1);
        }
    }
	
	public void findMax(){
		for (int i = 0; i < pos; i++){
			if (h.get(""+ (i+dice)) > max){
				max = h.get("" + (i+dice));
			}
		}
		max = max + 50 - (max % 50);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		erase = true;
		initrolls();
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
