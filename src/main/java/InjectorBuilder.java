import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author Mykola Khazanovych
 *         23.04.2017;
 */
class InjectorBuilder {
    private static volatile Injector injector;

    static Injector getInjector(){
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