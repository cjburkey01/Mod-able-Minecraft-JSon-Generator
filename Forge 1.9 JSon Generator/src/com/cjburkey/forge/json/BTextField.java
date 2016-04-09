package com.cjburkey.forge.json;

import javafx.scene.control.TextField;

public class BTextField extends TextField {
	
	public String name;
	
	public BTextField(String n) {
		this.name = n;
	}
	
	public BTextField setPrompt(String t) {
		this.setPromptText(t);
		return this;
	}
	
}