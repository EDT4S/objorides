package dit954lab.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{
	protected record CarDrawData(BufferedImage image,Point point){
		public CarDrawData(BufferedImage image){this(image,new Point());}
	}
	protected java.util.List<CarDrawData> cars;
	// Initializes the panel and reads the images
	public DrawPanel(int x,int y){
		this.setDoubleBuffered(true);
		this.setPreferredSize(new Dimension(x,y));
		this.setBackground(Color.green);
		// Print an error message in case file is not found with a try/catch block
		try{
			this.cars = new java.util.ArrayList(Arrays.asList(
				new CarDrawData(ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Volvo240.jpg"))),
				new CarDrawData(ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Saab95.jpg"))),
				new CarDrawData(ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Scania.jpg")))
				//new CarDrawData(ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/VolvoBrand.jpg"))),
			));
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}

	// TODO: Make this general for all cars
	void moveit(int i,int x,int y){
		cars.get(i).point.x = x;
		cars.get(i).point.y = y;
	}

	// This method is called each time the panel updates/refreshes/repaints itself
	// TODO: Change to suit your needs.
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		for(CarDrawData car : this.cars){
			g.drawImage(car.image,car.point.x,car.point.y,null); // see javadoc for more info on the parameters
		}
	}
}
