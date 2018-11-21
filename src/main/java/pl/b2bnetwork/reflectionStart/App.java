package pl.b2bnetwork.reflectionStart;

import pl.b2bnetwork.animal.Dog;
import pl.b2bnetwork.reflection.Reflection;

public class App {
    public static void main(String[] args) {

        Reflection reflection = new Reflection();
        Class reflectClass = Dog.class;

        reflection.checkNameClass(reflectClass);
        reflection.checkModifiers(reflectClass);
        reflection.checkInterfaces(reflectClass);
        reflection.checkInheritance(reflectClass);
        reflection.checkFields(reflectClass);
        reflection.checkMethods(reflectClass);
        reflection.checkConstructors(reflectClass);
        reflection.createNewObjectInstance(reflectClass);
    }
}
