package com.cjburkey.forge.json;

import java.util.ArrayList;
import java.util.List;

public class Generator {
	
	public String name;
	public List<BTextField> items;
	private int id;
	
	public Generator(String name, List<BTextField> items) {
		this.name = name;
		this.items = items;
		this.id = nid;
		nid ++;
	}
	
	public void extra() {  }
	public String generate() { return ""; }
	public String getFileName() { return ""; }
	public final int getID() { return this.id; }
	
	//--STATIC--//
	
	public static final ArrayList<Generator> generators = new ArrayList<Generator>();
	private static int nid = 0;
	
	public static final void registerGenerators() {
		generators.addAll(Search.loadGenerators());
	}
	
}