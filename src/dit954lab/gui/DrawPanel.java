package dit954lab.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{
	// Just a single image, TODO: Generalize
	BufferedImage volvoImage;
	// To keep track of a single car's position
	Point volvoPoint = new Point();

	BufferedImage volvoWorkshopImage;
	Point volvoWorkshopPoint = new Point(300,300);

	// Initializes the panel and reads the images
	public DrawPanel(int x,int y){
		this.setDoubleBuffered(true);
		this.setPreferredSize(new Dimension(x,y));
		this.setBackground(Color.green);
		// Print an error message in case file is not found with a try/catch block
		try{
			volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Volvo240.jpg"));
			volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/VolvoBrand.jpg"));
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}

	// TODO: Make this general for all cars
	void moveit(int x,int y){
		volvoPoint.x = x;
		volvoPoint.y = y;
	}

	// This method is called each time the panel updates/refreshes/repaints itself
	// TODO: Change to suit your needs.
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(volvoImage,volvoPoint.x,volvoPoint.y,null); // see javadoc for more info on the parameters
		g.drawImage(volvoWorkshopImage,volvoWorkshopPoint.x,volvoWorkshopPoint.y,null);
	}
}
