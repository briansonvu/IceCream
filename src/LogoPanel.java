
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LogoPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LogoPanel() {
		
		this.setBackground(Color.WHITE);
		BufferedImage logo = null;
		try {
			logo = ImageIO.read(new File("Header-Art-01.png"));
		} catch (IOException e) {
		}
		JLabel logoLabel = new JLabel(new ImageIcon(logo));
		this.add(logoLabel);	
	}
}
