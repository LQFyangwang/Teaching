import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.EventListener;
import java.util.Vector;

import javax.imageio.ImageIO;
public class GameApp extends Frame implements MouseListener{
	Vector v=new Vector();	
	public GameApp(){
		super("Îå×ÓÆåÓÎÏ·");
		this.addMouseListener(this);
		this.setSize(435,465);
	}
	public void paint(Graphics g){
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		try {
			BufferedImage bufImage = ImageIO.read(this.getClass().getResource("/images/Board.gif"));
			BufferedImage bufImage1 = ImageIO.read(this.getClass().getResource("/images/Black.gif"));
			BufferedImage bufImage2 = ImageIO.read(this.getClass().getResource("/images/white.gif"));
			g.drawImage(bufImage,0,20,this);
			for(int i=0;i<v.size();i++){
				String str=(String)v.get(i);
				String tmp[]=str.split("-");			
				int a=Integer.parseInt(tmp[0]);
				int b=Integer.parseInt(tmp[1]);						
				int x=b*25+18-12;
				int y=a*25+39-12;
				if(i%2==0)
					g.drawImage(bufImage1,x,y,this);
				else
					g.drawImage(bufImage2,x,y,this);
			}
		} catch(Exception e) {
		}
	}
	public void update(Graphics g){
		this.paint(g);
		
	}
	public void mouseClicked(MouseEvent e) {
		// TODO: Add your code here
		int x=e.getX();
		int y=e.getY();
		int gridy=(x-18)/25+((x-18)%25>12?1:0);
		int gridx=(y-39)/25+((y-39)%25>12?1:0);
		
		System.out.println(gridx+"-"+gridy);
		
		if(!v.contains(gridx+"-"+gridy)){
			v.add(gridx+"-"+gridy);
		}
		this.repaint();
	}
	public void mousePressed(MouseEvent e) {	}
	public void mouseReleased(MouseEvent e) {	}
	public void mouseEntered(MouseEvent e) {	}
	public void mouseExited(MouseEvent e) {	}
	
	public static void main(String args[]){
		new GameApp().show();
	}
}