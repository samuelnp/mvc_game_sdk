package com.game.helpers;

/**
 * MultipleKeyReader wrap multiple key pulsations and recognize them
 */
public class MultipleKeysReader {

	private String command;
	private String commandKeys;
	
	public MultipleKeysReader() {
		command = null;
		commandKeys = ""; // set var to empty string to concatenate characters
	}
	
	public MultipleKeysReader(String command) {
		this();
		this.command = command;
	}
	
	public void add(String s) {
		if(command.contains(s)) {
			commandKeys += s;
		} else {
			commandKeys = ""; // set var to empty string to concatenate characters
		}
	}
	
	public Boolean found() {
		if(commandKeys.equals(command)) {
        	commandKeys = "";
        	return true;
        } 
		
		return false;
	}
	
}
