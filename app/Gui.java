import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

import dit954lab.Movable;
import dit954lab.Physical;
import dit954lab.world.CarRepairShop;
import dit954lab.world.vehicles.Car;
import dit954lab.world.vehicles.CarFerry;
import dit954lab.world.vehicles.CarTransporter;
import dit954lab.world.vehicles.Saab95;
import dit954lab.world.vehicles.Scania;
import dit954lab.world.vehicles.StandardCar;
import dit954lab.world.vehicles.Vehicle;
import dit954lab.world.vehicles.Volvo240;
import dit954lab.world.vehicles.addons.Flak;
import util.Container;
import vector2d.Coord;

class Gui{
	public static final class DrawPanel extends JPanel implements ActionListener{
		private static final long serialVersionUID = -1544594115852711561L;
		private static final double scale = 20.0;

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
			return new Color((c.getRGB() & 0b00000000_11111111_11111111_11111111) | (alpha << 24),true);
		}

		protected void paintInterface(Graphics g){
			final double x = this.thing.player.getPosition().getX() / scale;
			final double y = this.thing.player.getPosition().getY() / scale ;
			final double dx = this.thing.player.getVelocity().getX();
			final double dy = this.thing.player.getVelocity().getY();
			final int h = g.getFont().getSize();

			g.setColor(Color.DARK_GRAY);
			g.drawString("x: " + Integer.toString((int)x),0,h);
			g.drawString("y: " + Integer.toString((int)y),0,h * 2);
			g.drawString("dx: " + Double.toString(dx),0,h * 3);
			g.drawString("dy: " + Double.toString(dy),0,h * 4);
			if(this.thing.player instanceof Saab95){
				g.drawString("Turbo: " + Boolean.toString(((Saab95)this.thing.player).isTurboOn()),0,h * 5);
			}
			if(this.thing.player instanceof Flak.Has<?>){
				g.drawString("Flak: closed=" + Boolean.toString(((Flak.Has<?>)this.thing.player).getFlak().isFlakClosed()) + " , open=" + Boolean.toString(((Flak.Has<?>)this.thing.player).getFlak().isFlakOpen()),0,h * 6);
			}
		}

		protected void paintVehicle(Graphics g,Physical obj){
			double x = obj.getPosition().getX() / scale;
			double y = obj.getPosition().getY() / scale ;
			double dx = obj.getVelocity().getX();
			double dy = obj.getVelocity().getY();
			final int h = g.getFont().getSize();

			if(obj instanceof Car){
				Car car = (Car)obj;
				g.setColor(colorWithAlpha(car.getColor(),128));
				g.fillRect((int)x-8,(int)y-8,16,16);
			}
			g.setColor(Color.BLACK);
			g.drawRect((int)x-8,(int)y-8,16,16);
			g.drawLine((int)x,(int)y,(int)(x+dx/scale*4),(int)(y+dy/scale*4));
			if(obj instanceof Vehicle){
				g.drawString(((Vehicle)obj).getModelName(),(int)x,(int)y - h);
			}
		}
		
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);

			//Draw background
			g.setColor(new Color(253,242,234));
			g.fillRect(0,0,this.getWidth(),this.getHeight());

			//Draw player info
			paintInterface(g);
			
			//Draw vehicles
			for(Physical obj : this.thing.objs){
				paintVehicle(g,obj);
			}
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
		public ArrayList<Physical> objs;
		public Vehicle player;

		public Thing(){
			this.restart();
		}

		public void restart(){
			player = new Volvo240(new Coord(1000,1000),0);
			objs = new ArrayList<>(10);
			objs.add(player);
			objs.add(new Saab95(new Coord(3000,1000),0));
			objs.add(new Scania(new Coord(5000,1000),0));
			objs.add(new CarTransporter<StandardCar>(new Coord(7000,1000),0));
			objs.add(new Volvo240(new Coord(1000,3000),0));
			objs.add(new Volvo240(new Coord(3000,3000),0));
			objs.add(new CarRepairShop<Volvo240>(2,new Coord(5000,3000)));
			objs.add(new CarFerry<StandardCar>(new Coord(7000,3000),0));
		}

		@SuppressWarnings("unchecked")
		@Override
		public void keyPressed(KeyEvent e){
			System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
			switch(e.getKeyCode()){
				case KeyEvent.VK_LEFT:
					player.turnLeft();
					break;
				case KeyEvent.VK_RIGHT:
					player.turnRight();
					break;
				case KeyEvent.VK_UP:
					player.gas(0.4);
					break;
				case KeyEvent.VK_DOWN:
					player.brake(0.7);
					break;
				case KeyEvent.VK_SPACE:
					player.startEngine();
					break;
				case KeyEvent.VK_Z:
					if(player instanceof Saab95){
						((Saab95)player).setTurboOn();
					}
					else if(player instanceof Container.Has<?>){
						for(Physical obj : objs){
							if(obj instanceof StandardCar){
								System.out.println(((Container.Has<StandardCar>)player).getContainer().add((StandardCar)obj));
							}
						}
					}
					break;
				case KeyEvent.VK_X:
					if(player instanceof Saab95){
						((Saab95)player).setTurboOff();
					}
					else if(player instanceof Container.Has<?>){
						System.out.println(((Container.Has<StandardCar>)player).getContainer().remove());
					}
					break;
				case KeyEvent.VK_HOME:
					if(player instanceof Flak.Has<?>){
						((Flak.Has<?>)player).getFlak().openFlak();
					}
					break;
				case KeyEvent.VK_END:
					if(player instanceof Flak.Has<?>){
						((Flak.Has<?>)player).getFlak().closeFlak();
					}
					break;
				case KeyEvent.VK_R:
					this.restart();
					break;
				case KeyEvent.VK_1:
					selectPlayer(0);
					break;
				case KeyEvent.VK_2:
					selectPlayer(1);
					break;
				case KeyEvent.VK_3:
					selectPlayer(2);
					break;
				case KeyEvent.VK_4:
					selectPlayer(3);
					break;
				case KeyEvent.VK_5:
					selectPlayer(4);
					break;
				case KeyEvent.VK_6:
					selectPlayer(5);
					break;
				case KeyEvent.VK_7:
					selectPlayer(6);
					break;
				case KeyEvent.VK_8:
					selectPlayer(7);
					break;
				case KeyEvent.VK_9:
					selectPlayer(8);
					break;
			}
		}

		void selectPlayer(int n){
			if(objs.size() > n){
				Physical obj = objs.get(n);
				if(obj instanceof Vehicle){
					player = (Vehicle)obj;
				}
			}
		}
		
		@Override
		public void keyReleased(KeyEvent e){}

		@Override
		public void keyTyped(KeyEvent e){}

		@Override
		public void actionPerformed(ActionEvent a){
			for(Physical obj : objs){
				if(obj instanceof Movable){
					((Movable)obj).move();
				}
			}
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
