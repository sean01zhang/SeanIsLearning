package seanEngine;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SeanCausalityObj {
	String cause;
	Queue<String> effect;
	
	public SeanCausalityObj(String cause, String effect) {
		this.cause = cause;
		this.effect = new LinkedList<String>();
		this.effect.add(effect);
	}
	
	public SeanCausalityObj(String cause) {
		this.cause = cause;
		this.effect = new LinkedList<String>();
	}
	
	public SeanCausalityObj(String cause, Queue<String> effect) {
		this.cause = cause;
		this.effect = effect;
	}
	
	public void addEffect(String command) {
		this.effect.add(command);
	}
	
	public String getCause() {
		return cause;
	}
	
	public String[] getEffects() {
		Object[] ea = effect.toArray();
		return Arrays.copyOf(ea, ea.length, String[].class);
	}
	
	
	public String toString() {
		return ("~ " +cause +" "+ Arrays.toString(getEffects()) + " ~");
	}
}
