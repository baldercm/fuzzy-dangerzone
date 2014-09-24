package org.baldercm.poc;

import java.io.InputStream;
import java.io.InputStreamReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class MainNashorn {

	public static void main(String[] args) throws Exception {
		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine = engineManager.getEngineByName("nashorn");
		Invocable invocable = (Invocable) engine;
		InputStream in = MainNashorn.class.getResourceAsStream("/javascriptPoc.js");
		engine.eval(new InputStreamReader(in));
		Object hello = invocable.invokeFunction("hello", "from Javascript!");
		System.out.println(hello);
	}

}