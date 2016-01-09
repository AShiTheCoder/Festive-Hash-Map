import java.io.*;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try{
        System.out.print("Sides:");
        int s = Integer.parseInt(br.readLine());
        System.out.print("Dice:");
        int d = Integer.parseInt(br.readLine());
        Die[] rollers = new Die[d];
        System.out.print("How many rolls?");
        int r = Integer.parseInt(br.readLine());
      
		Display disp = new Display(r, s, rollers);
		disp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		disp.setVisible(true);
		}catch(NumberFormatException nfe){
            System.err.println("That's not a number");
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
