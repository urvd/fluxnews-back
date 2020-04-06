package com.backend.fluxnewsapi.config;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import static jdk.nashorn.internal.codegen.OptimisticTypesPersistence.load;

public class Script {
    public static Script getInstance(){
        return new Script();
    }
    public  Object helloJavascript() throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

        return engine.eval(
                "var greeting='hello world from javascript .';" +
                        "print(greeting);" +
                        "greeting"
        );
    }

    public void runJavascript() throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        load("/script.js");
    }
}
