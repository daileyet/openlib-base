package xyz.openthinks.vimixer.ui.controller;

import i18n.I18n;
import i18n.I18nApplicationLocale;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioMenuItem;
import javafx.stage.FileChooser;
import xyz.openthinks.vimixer.resources.bundles.ViMixerBundles;
import xyz.openthinks.vimixer.ui.ViMixerApp;
import xyz.openthinks.vimixer.ui.model.ViFile;
import xyz.openthinks.vimixer.ui.model.ViFileSupportType;

public class RootLayoutController extends BaseController {

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);
		
	}
	
	@Override
	protected void afterSetTransfer() {
		super.afterSetTransfer();
		File storeFile = this.lastConfigureFile();
		loadConfigure(storeFile);
	}
	
	@FXML
	private void handAddAction() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extensionFilter1 = new FileChooser.ExtensionFilter(
				"video files", ViFileSupportType.SUPPORT_LIST_VIDEO);
		FileChooser.ExtensionFilter extensionFilter2 = new FileChooser.ExtensionFilter(
				"image files", ViFileSupportType.SUPPORT_LIST_IMAGE);
		fileChooser.getExtensionFilters().addAll(extensionFilter1,
				extensionFilter2);
		List<File> files = fileChooser.showOpenMultipleDialog(this.stage());
		if (files != null) {
			for (File file : files) {
				this.listProperty().get().add(new ViFile(file));
			}
		}
	}


	@FXML
	private void handQuitAction() {
		System.exit(0);
	}

	@FXML
	private void handViewCurrent() {
		((ViMixerApp) this.app()).showConfigurePane();
	}

	@FXML
	private void handSaveConfigure() {
		File storeFile = this.lastConfigureFile();
		if (storeFile != null) {
			saveConfigure(storeFile);
		} else {
			handSaveAsConfigure();
		}
	}

	@FXML
	private void handSaveAsConfigure() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
		// Show save file dialog
		File file = fileChooser.showSaveDialog(this.stage());
		this.saveConfigure(file);
	}

	@FXML
	private void handLoadConfigure() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
		// Show save file dialog
		File file = fileChooser.showOpenDialog(this.stage());
		this.loadConfigure(file);
	}
	
	@FXML
	private void handAbout(){
		Alert alert =new Alert(AlertType.INFORMATION);
		alert.setTitle(getBundleMessage("app.title"));
		alert.setHeaderText(getBundleMessage("about.alert.header"));
		alert.setContentText(I18n.getMessage(ViMixerBundles.UI, "about.alert.content", "1.0","Dailey Dai","http://openthinks.xyz"));
		alert.initOwner(this.stage());
		alert.showAndWait();
	}
	
	@FXML
	private void handLocaleChange(Event event){
		String menuitemId = ((RadioMenuItem)event.getSource()).getId();
		String changeToLocale = menuitemId.split("_")[0];
		Locale locale = new Locale.Builder().setLanguage(changeToLocale).build();
		I18nApplicationLocale.getInstance().changeCurrentLocale(locale);
	}

}
