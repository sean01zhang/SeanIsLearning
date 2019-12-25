package seanEngine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComboBox.KeySelectionManager;

public class KeyboardInput implements KeyListener {
	Boolean ignoreInput;
	int[] input;
	int count = 0;
	SeanEngine se;
	
	public KeyboardInput(SeanEngine e) {
		initializeBindings();
		se=e;
	}
	
	public void initializeBindings() {
		input = new int[256];
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		input[e.getKeyCode()] = 1;
		se.keyInterpreter(input);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		input[e.getKeyCode()] = 0;
	}
	
	public void enableInput() {
		ignoreInput = false;
	}
	
	public void disableInput() {
		ignoreInput = true;
	}
}
