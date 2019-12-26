package seanEngine;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SeanCausalityObj {
	String cause;
	Queue<String> effect;
	SeanEngine se;
	
	public SeanCausalityObj(String cause, String effect, SeanEngine se) {
		this.cause = cause;
		this.effect = new LinkedList<String>();
		this.effect.add(effect);
		this.se = se;
	}
	
	public SeanCausalityObj(String cause, SeanEngine se) {
		this.cause = cause;
		this.effect = new LinkedList<String>();
		this.se = se;
	}
	
	public SeanCausalityObj(String cause, Queue<String> effect, SeanEngine se) {
		this.cause = cause;
		this.effect = effect;
		this.se = se;
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
	
	public void enactEffect(Boolean kill) {
		while(!effect.isEmpty()) {
			se.suffixInterpreter(effect.poll());
		}
		if(kill) {
			se.d.getSta().setSComp(null);
			se.ki.ignoreInput = false;
		}
	}
	
	public void enactEffect() {
		while(!effect.isEmpty()) {
			se.suffixInterpreter(effect.poll());
		}
	}
	
	public String toString() {
		return ("~~~ " +cause +" "+ Arrays.toString(getEffects()) + " ~~~");
	}
}
