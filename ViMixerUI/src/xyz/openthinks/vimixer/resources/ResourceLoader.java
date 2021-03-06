package xyz.openthinks.vimixer.resources;

import java.net.URL;

import javafx.scene.image.Image;

public class ResourceLoader {
	public static final Image APP_ICON;
	public static final URL FXML_ROOTLAYOUT;
	public static final URL FXML_MAINFRAME;
	public static final URL CSS_APP;
	public static final URL FXML_CONFIGUREPANE;

	static {

		FXML_ROOTLAYOUT = ResourceLoader.class.getResource("/xyz/openthinks/vimixer/ui/view/RootLayout.fxml");
		FXML_MAINFRAME = ResourceLoader.class.getResource("/xyz/openthinks/vimixer/ui/view/MainFrame.fxml");
		FXML_CONFIGUREPANE = ResourceLoader.class.getResource("/xyz/openthinks/vimixer/ui/view/ConfigurePane.fxml");

		CSS_APP = ResourceLoader.class.getResource("/xyz/openthinks/vimixer/ui/view/application.css");
		APP_ICON = new Image(ResourceLoader.class.getResourceAsStream("vimixer_4.png"));

	}
}
