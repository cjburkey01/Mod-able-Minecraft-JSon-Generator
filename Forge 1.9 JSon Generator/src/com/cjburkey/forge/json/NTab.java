package com.cjburkey.forge.json;

import javafx.scene.control.Tab;

public class NTab extends Tab {
	
	private Generator gen;
	
	public NTab(Generator g) {
		this.gen = g;
	}
	
	public Generator getGenerator() { return this.gen; }
	
}