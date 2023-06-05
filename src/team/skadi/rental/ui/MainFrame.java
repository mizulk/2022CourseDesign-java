/**
 * 
 */
package team.skadi.rental.ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * @author mizudp
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	public static final int FRAME_WIDTH = 1000, FRAME_HEIGHT = 610;
	public static final int FRAME_MIN_WIDTH, FRAME_MIN_HEIGHT;

	private CardLayout cardLayout;

	static {
		FRAME_MIN_HEIGHT = (int) (FRAME_HEIGHT * 0.85);
		FRAME_MIN_WIDTH = (int) (FRAME_WIDTH * 0.85);
	}

	public MainFrame() {
		super("德莱联盟电源租凭系统");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int x = (screenSize.width >> 1) - (FRAME_WIDTH >> 1);
		int y = (screenSize.height >> 1) - (FRAME_HEIGHT >> 1);
		setBounds(x, y, FRAME_WIDTH, FRAME_HEIGHT);
		setMinimumSize(new Dimension(FRAME_MIN_WIDTH, FRAME_MIN_HEIGHT));
		bulidLayout();
		addListener();
		setVisible(true);
	}

	private void bulidLayout() {
		cardLayout = new CardLayout();
		setLayout(cardLayout);
		add("login", new LoginPanel(this));
	}

	private void addListener() {

	}
}
