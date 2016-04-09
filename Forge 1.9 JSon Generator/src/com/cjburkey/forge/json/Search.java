package com.cjburkey.forge.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Search {
	
	public static final File dir = new File(System.getProperty("user.home") + "/JSonGenerator/generators/");
	
	public static final ArrayList<Generator> loadGenerators() {
		if(!dir.exists()) dir.mkdirs();
		ArrayList<Generator> gens = new ArrayList<Generator>();
		for(File f : dir.listFiles()) {
			if(f.getName().endsWith(".gen")) {
				try {
					FileInputStream fileIn = new FileInputStream(f);
					ObjectInputStream in = new ObjectInputStream(fileIn);
					Properties p = (Properties) in.readObject();
					
					List<BTextField> fields = new ArrayList<BTextField>();
					for(String s : p.getProperty("required").split(",")) {
						fields.add(new BTextField(s.trim()).setPrompt(s.trim()));
					}
					
					Generator g = new Generator(p.getProperty("name"), fields) {
						public String getFileName() {
							String json = p.getProperty("filename");
							for(BTextField f : fields) {
								json = json.replaceAll("%%" + f.name + "%%", f.getText());
							}
							return json;
						}
						public String generate() {
							String json = p.getProperty("json");
							for(BTextField f : fields) {
								json = json.replaceAll("%%" + f.name + "%%", f.getText());
							}
							return json;
						}
					};
					
					gens.add(g);
					
					in.close();
					fileIn.close();
				} catch(IOException e) {
					System.out.println("File missing: '" + f.getName() + "'");
				} catch(ClassNotFoundException e) {
					System.out.println("Invalid class with: '" + f.getName() + "'");
				} catch(Exception e) {
					System.out.println("An error occurred with: '" + f.getName() + "'");
				}
			}
		}
		return gens;
	}
	
}