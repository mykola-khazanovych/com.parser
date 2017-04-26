package com.parser.dependency;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.parser.DependencyInjectionModule;

/**
 * @author Mykola Khazanovych
 *         23.04.2017;
 */
public class InjectorBuilder {
    private static volatile Injector injector;

    public static Injector getInjector(){
        if (injector == null){
            synchronized (InjectorBuilder.class) {
                if (injector == null)
                    injector  = Guice.createInjector(new DependencyInjectionModule());
            }
        }
        return injector;
    }
    private InjectorBuilder(){}
}