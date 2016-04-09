package com.cjburkey.forge.json;

import java.io.File;
import java.io.FileWriter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class JSonGenerator extends Application {
	
	public static void main(String[] args) {
		System.out.println("--[ PRGM: BOOT ]--");
		launch(args);
		System.out.println("--[ PRGM: DONE ]--");
	}
	
	public void start(Stage s) throws Exception {
		System.out.println("Program starting...");
		Generator.registerGenerators();
		
		System.out.println("Registered " + Generator.generators.size() + " generator(s).");
		
		VBox root = new VBox();
		Scene scene = new Scene(root);
		
		TabPane tabbed = new TabPane();
		Button gen = new Button("Generate");
		
		for(Generator g : Generator.generators) {
			VBox n = new VBox();
			for(Node node : g.items) {
				n.getChildren().add(node);
			}
			
			n.setPadding(new Insets(10));
			n.setSpacing(10);
			
			NTab t = new NTab(g);
			t.setText(g.name);
			t.setContent(n);
			t.setClosable(false);
			t.setOnSelectionChanged(e -> {
				if(t.isSelected()) System.out.println("Selected Generator: id" + g.getID());
			});
			tabbed.getTabs().add(t);
			
			g.extra();
		}
		
		root.setPadding(new Insets(0, 0, 10, 0));
		root.setSpacing(10);
		root.getChildren().addAll(tabbed, gen);
		root.setAlignment(Pos.CENTER);
		
		gen.setOnAction(e -> {
			for(Generator g : Generator.generators) {
				int rid = g.getID();
				int tabID = getIDFromTab(tabbed.getSelectionModel().getSelectedItem());
				if(rid == tabID) {
					FileChooser chooser = new FileChooser();
					chooser.setTitle("Select Output File");
					chooser.setInitialFileName(g.getFileName() + ".json");
					chooser.getExtensionFilters().add(new ExtensionFilter("JSON Files (*.json)", "*.json"));
					File f = chooser.showSaveDialog(s);
					
					if(f != null) {
						create(f, g.generate());
					} else {
						System.out.println("Cancelled.");
					}
				}
			}
		});
		
		s.setScene(scene);
		s.sizeToScene();
		s.centerOnScreen();
		s.setResizable(false);
		s.setTitle("Forge 1.9 JSon Generator");
		s.show();
		s.setWidth(s.getWidth() * 3);
		s.centerOnScreen();
		
		System.out.println("Program start finished.");
	}
	
	private static final int getIDFromTab(Tab t) {
		return ((NTab) t).getGenerator().getID();
	}
	
	private static final void create(File f, String s) {
		System.out.println("Writing to: '" + f + "'");
		System.out.println("//--START--//");
		System.out.println(s);
		System.out.println("//-- END --//");
		try {
			FileWriter w = new FileWriter(f);
			w.write(s);
			w.close();
		} catch (Exception e) {
			e.printStackTrace();
			
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("An Error Occurred! (" + e.hashCode() + ")");
			a.setContentText(e.getMessage() + "\n\nSee console for full error.");
			
			Platform.exit();
		}
		System.out.println("//--SIZE: [ " + f.length() + " ]--//");
		System.out.println("//--DONE--//");
	}
	
}