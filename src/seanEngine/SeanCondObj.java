package seanEngine;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class SeanCondObj {
	public static Boolean evaluateStatement(String s) {
		ScriptEngineManager manager = new ScriptEngineManager();
	    ScriptEngine engine = manager.getEngineByName("js");
	    try {
			return (Boolean) engine.eval(s);
		} catch (ScriptException e) {}
		return null;
	}
}
