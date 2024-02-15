package dit954lab.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Stream;

// This panel represents the animated part of the view with the car images.

public abstract class DrawPanel extends JPanel{
	protected HashMap<String,BufferedImage> images = new HashMap<>();
	// Initializes the panel and reads the images
	public DrawPanel(int x,int y){
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

	public abstract Stream<CarViewData> getCars();

	// This method is called each time the panel updates/refreshes/repaints itself
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		this.getCars().forEach(car -> {
			BufferedImage image = this.images.get(car.name());
			if(image != null){
				g.drawImage(image,car.x(),car.y(),null);
			}
		});
	}
}
