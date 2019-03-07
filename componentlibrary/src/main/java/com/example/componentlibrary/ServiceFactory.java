package com.example.componentlibrary;

/**
 * Created by dengchong on 19-3-7.
 */
public class ServiceFactory {

    private static IComponentInterface sComponentInterface;

    private ServiceFactory() {

    }

    private static final ServiceFactory INSTANCE = new ServiceFactory();

    public static final ServiceFactory getInstance() {
        return INSTANCE;
    }

    public IComponentInterface getComponentInterface() {
        return sComponentInterface;
    }

    public void register(IComponentInterface componentInterface) {
        sComponentInterface = componentInterface;
    }

}
