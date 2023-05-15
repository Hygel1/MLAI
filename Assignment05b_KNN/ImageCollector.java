import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Brings up an interactive canvas for user to draw
 * an image or number. The class then reads the drawing
 * and converts to a 1 x 28*28 array of integers. 
 * 
 * Program then prompts user for a text file name
 * and write the data to the file with values separated by commas
 * 
 * Formatted to collect user drawings and compare against
 * mnist number dataset.
 * 
 * @author Project Setup by: michaudc / www.nebomusic.net
 * 
 * Depends on stdlib.jar from Princeton
 *
 */
public class ImageCollector implements DrawListener {

	// Fields
	private Draw draw = new Draw();   		 // drawing object
    private int [] rawData = new int[28*28]; // Stores values after user has drawn

    /**
     * Default Constructor
     * Sets canvas size of drawing to 112 by 112
     * Prompts User with directions
     */
    public ImageCollector() {
    	draw.setCanvasSize(112, 112);
        draw.addListener(this);
        draw.clear(Color.WHITE);
        draw.show();
        System.out.println("Draw the Number and then press Space");
        System.out.println("Press c to clear");
    }

    /**
     * draw a spot as the user drags the mouse
     */
    public void mouseDragged(double x, double y) {
        draw.setPenColor(Color.BLACK);
        draw.filledCircle(x, y, 0.05);
    }

    /**
     * Main method for Testing
     * @param args - not used
     */
    public static void main(String[] args) {
        new ImageCollector();
    }

    // Override Methods from DrawListener
    // This program uses keyTyped()
	@Override
	public void mousePressed(double x, double y) {
		keyPressed(0);
		
	}

	@Override
	public void mouseReleased(double x, double y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(double x, double y) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * User controls for Drawing
	 * Space Bar will capture the drawing
	 * 'c' key will clear the drawing canvas
	 */
	@Override
	public void keyTyped(char c) {
		// TODO Auto-generated method stub
		// Space Bar Captures Drawing
		if (c == ' ') {
			System.out.println("Preparing Data");
			captureDrawing();
			System.out.println("Data is Ready");
		}
		
		// 'c' key clears the screen
		if (c == 'c') {
			draw.clear();
		}
		
	}

	@Override
	public void keyPressed(int keycode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(int keycode) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Method to take the Drawn Image from User
	 * and convert to a 28 by 28 grid is pixels
	 * Reverses the image so number is white on black 
	 * to match the mnist dataset. 
	 * Shows the image and creates a 1 row and 28 column of ints to 
	 * match the data setup of the mnist dataset
	 * 
	 * You will need to implement this method. Some code is provided
	 */
	private void captureDrawing() {
		// Saves Drawing to .png file
		// Then opens drawing as a Grayscale Picture object
		String path = "temp.png";
		draw.save(path);
		GrayscalePicture picture = new GrayscalePicture(path);
		
		// Rescale Picture manually to match mnist data
		GrayscalePicture small = new GrayscalePicture(28, 28);
		
		//TODO: Copy pixels from picture object to small object
		// you will need to devise a method to "average" the values
		// of a block of pixels from larger picture to small.
		// The image also must be "reversed" so the backround is black
		// and the number is written in white
		// dimensions are 224 by 224, column major (x, y)
		// Implement Here:
		
		
		// Call the Write Data to File Method
		writeDataToFile();
		
		// Show the Big and Small Picture
		picture.show();
		small.show();
		
	}
	
	/**
	 * This method is provided. It will read the rawData array list of doubles
	 * and write them to a file.  
	 * This method will prompt the user for a file name and then
	 * iterate through the array and write the values to the file name
	 * specified by the user.
	 */
	private void writeDataToFile() {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter filename: ");
			String path = scanner.nextLine();
			
			FileWriter writer = new FileWriter(path);
			String out = "";
			for (Integer i : rawData) {
				out += i + ",";
			}
			out = out.substring(0, out.length()-1);
			writer.write(out);
			writer.close();
			scanner.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
