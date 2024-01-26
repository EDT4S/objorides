package dit954lab.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

import dit954lab.cars.Car;
import dit954lab.cars.Volvo240;
import vector2d.Coord;

public class Gui{
	public static final class DrawPanel extends JPanel implements ActionListener{
		private static final long serialVersionUID = -1544594115852711561L;

		private Thing thing;

		public DrawPanel(){
			this.thing = new Thing();
			this.addKeyListener(this.thing);
			this.setFocusable(true);
		}

		/**
		 * Constructs a Color with the same color but using the specified alpha value.
		 * @param c The base color.
		 * @param alpha The new alpha value.
		 * @return A color cased on the base color with the new alpha value.
		 */
		public static final Color colorWithAlpha(Color c,int alpha){
			//Converts an RGB integer to an RGBA integer by copying the bits of alpha to the alpha channel.
			return new Color((c.getRGB() | 0b11111111_00000000_00000000_00000000) & (alpha << 24),true);
		}
		
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			double x = this.thing.car1.getPosition().getX();
			double y = this.thing.car1.getPosition().getY();
			double dx = this.thing.car1.getVelocity().getX();
			double dy = this.thing.car1.getVelocity().getY();

			//Draw background
			g.setColor(new Color(253,242,234));
			g.fillRect(0,0,this.getWidth(),this.getHeight());
			
			//Draw info
			g.setColor(Color.DARK_GRAY);
			g.drawString("x: " + Integer.toString((int)x),0,g.getFont().getSize());
			g.drawString("y: " + Integer.toString((int)y),0,g.getFont().getSize() * 2);
			g.drawString("dx: " + Double.toString(dx),0,g.getFont().getSize() * 3);
			g.drawString("dy: " + Double.toString(dy),0,g.getFont().getSize() * 4);

			//Draw car
			g.setColor(colorWithAlpha(this.thing.car1.getColor(),128));
			g.fillRect((int)x-8,(int)y-8,16,16);
			g.setColor(Color.BLACK);
			g.drawLine((int)x,(int)y,(int)(x+dx*4),(int)(y+dy*4));
			g.drawRect((int)x-8,(int)y-8,16,16);
			g.drawString(this.thing.car1.getModelName(),(int)x,(int)y - g.getFont().getSize());
		}

		@Override
		public Dimension getPreferredSize(){
			return new Dimension(640,480);
		}

		@Override
		public void actionPerformed(ActionEvent a){
			this.repaint();
		}
	}

	public static final class Thing implements KeyListener,ActionListener{
		public Car car1;
		
		public Thing(){
			this.restart();
		}

		public void restart(){
			car1 = new Volvo240(new Coord(100,100),0);
		}
		
		@Override
		public void keyPressed(KeyEvent e){
			System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
			switch(e.getKeyCode()){
				case KeyEvent.VK_LEFT:
					car1.turnLeft();
					break;
				case KeyEvent.VK_RIGHT:
					car1.turnRight();
					break;
				case KeyEvent.VK_UP:
					car1.gas(1);
					break;
				case KeyEvent.VK_DOWN:
					car1.brake(1);
					break;
				case KeyEvent.VK_SPACE:
					car1.startEngine();
					break;
				case KeyEvent.VK_R:
					this.restart();
					break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e){}

		@Override
		public void keyTyped(KeyEvent e){}

		@Override
		public void actionPerformed(ActionEvent a){
			car1.move();
		}
	}
	
	public static void main(String[] args) {
		//Lag on Linux otherwise (Source: https://stackoverflow.com/questions/41001623/java-animation-programs-running-jerky-in-linux)
		System.setProperty("sun.java2d.opengl","true");

		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				DrawPanel panel = new DrawPanel();
				JFrame frame = new JFrame("Car Drawing Test");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().add(panel);
				frame.pack();
				frame.setLocationByPlatform(true);
				frame.setVisible(true);

				//Timer for updating and drawing.
				Timer timer = new Timer(0,panel);
				timer.addActionListener(panel.thing);
				timer.setDelay((int)(1000/60)); //60 FPS
				timer.setRepeats(true);
				timer.start();
			}
		});
	}
}
