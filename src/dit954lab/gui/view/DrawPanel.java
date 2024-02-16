package dit954lab.gui.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{
	protected ViewModel model;
	protected HashMap<String,BufferedImage> images = new HashMap<>();
	// Initializes the panel and reads the images
	public DrawPanel(int x, int y, ViewModel model){
		this.model = model;
		this.setDoubleBuffered(true);
		this.setPreferredSize(new Dimension(x,y));
		this.setBackground(Color.green);
		// Print an error message in case file is not found with a try/catch block
		try{
			images.put("Volvo240",ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Volvo240.jpg")));
			images.put("Saab95",ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Saab95.jpg")));
			images.put("Scania",ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Scania.jpg")));
			//ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/VolvoBrand.jpg")),
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}

	// This method is called each time the panel updates/refreshes/repaints itself
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		this.model.getCars().forEach(car -> {
			BufferedImage image = this.images.get(car.name());
			if(image != null){
				g.drawImage(image,car.x(),car.y(),null);
			}
		});
	}
}
