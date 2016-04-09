package com.cjburkey.forge.json;

import java.util.ArrayList;
import java.util.List;

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
		generators.addAll(Search.loadGenerators());
	}
	
}