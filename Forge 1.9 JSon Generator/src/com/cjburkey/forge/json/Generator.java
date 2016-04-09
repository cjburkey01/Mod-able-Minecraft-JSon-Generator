package com.cjburkey.forge.json;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextField;

public class Generator {
	
	public String name;
	public List<BTextField> items;
	
	public Generator(String name, List<BTextField> items) {
		this.name = name;
		this.items = items;
	}
	
	public void extra() {  }
	public String generate() { return ""; }
	public String getFileName() { return ""; }
	
	//--STATIC--//
	
	public static final ArrayList<Generator> generators = new ArrayList<Generator>();
	
	public static final void registerGenerators() {
		//--ITEM--//
			TextField itemModid = new TextField();
			TextField itemUnlocalized = new TextField();
			/*generators.add(new Generator("Simple Item", new Node[] { itemModid, itemUnlocalized }) {
				public void extra() {
					itemModid.setPromptText("Mod ID");
					itemUnlocalized.setPromptText("Item Unlocalized Name");
				}
				public String generate() {
					return "{\n\t\"parent\": \"item/generated\",\n\t\"textures\": {\n\t\t\"layer0\": \"" + itemModid.getText() + ":items/" + itemUnlocalized.getText() + "\"\n\t}\n}";
				}
				public String getFileName() {
					return itemUnlocalized.getText();
				}
			});*/
			generators.addAll(Search.loadGenerators());
		
	}
	
}