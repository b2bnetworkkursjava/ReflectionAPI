package pl.b2bnetwork.reflection;

import pl.b2bnetwork.animal.Dog;

import java.lang.reflect.*;

public class Reflection {

    public void checkNameClass(Class reflectClass) {
        // sprawdzenie nazwy klasy
        System.out.println("Nazwa klasy: " + reflectClass.getName());
    }

    public void checkModifiers(Class reflectClass) {

        // sprawdzenie modyfikatorów
        // można sprawdzić wiele opcji np:
        // isInterface, isPrivate, isFinal, isAbstract itp..
        int classModifiers = reflectClass.getModifiers();
        System.out.println("\nCzy klasa jest publiczna: " + Modifier.isPublic(classModifiers));
    }

    public void checkInterfaces(Class reflectClass) {

        // sprawdzenie interfejsów jakie implementuje
        Class[] interfaces = reflectClass.getInterfaces();
        System.out.println("\nIle interfejsów implementuje: " + interfaces.length);
    }

    public void checkInheritance(Class reflectClass) {

        // sprawdzenie klasy, po której dziedziczy
        Class superClass = reflectClass.getSuperclass();
        System.out.println("\nKlasa dziedziczy po " + superClass.getName());

    }

    public void checkFields(Class reflectClass) {

        // sprawdzenie pól
        Field[] declaredFields = reflectClass.getDeclaredFields();
        System.out.println("\nPola:");
        for (Field field : declaredFields) {
            System.out.println("Nazwa pola: " + field.getName());
            System.out.println("Czy jest widoczne: " + field.isAccessible());
        }
    }

    public void checkMethods(Class reflectClass) {

        // sprawdzenie metod
        System.out.println("\nMetody:");
        Method[] methods = reflectClass.getMethods();
        for (Method method : methods) {
            System.out.println("\nNazwa metody: " + method.getName());

            // sprawdzenie zwracanych typów przez metody
            System.out.println("Zwracany typ: " + method.getReturnType());

            // sprawdzenie jakie parametry przyjmuje metoda
            Class[] parameterType = method.getParameterTypes();
            for (Class parameter : parameterType) {
                System.out.println("Przyjmuje parametr: " + parameter.getName());
            }
        }
        System.out.println();
    }

    public void checkConstructors(Class reflectClass) {

        // sprawdzenie konstruktorów
        Constructor[] constructors = reflectClass.getConstructors();
        System.out.println("Konstruktory: ");
        for (Constructor constructor : constructors) {
            System.out.println("Kontruktor: " + constructor);
        }
    }

    public void createNewObjectInstance(Class reflectClass) {

        // wiadomo już, że istnieje konstruktor przyjmujący parametr String, więc to wykorzystamy
        Constructor dogConstructor = null;
        try {
            dogConstructor = reflectClass.getConstructor(String.class);


            Dog dog = null;
            dog = (Dog) dogConstructor.newInstance("Ted");


            dog.setName("Rex");
            System.out.println("Pies ma na imię: " + dog.getName());


            // dostęp do prywatnych pól
            Field privateOwnersName;

            privateOwnersName = Dog.class.getDeclaredField("owner");
            privateOwnersName.setAccessible(true);

            String owner = (String) privateOwnersName.get(dog);
            System.out.println("Imię właściciela: " + owner);

            // dostęp do prywatnych metod
            String methodName = "makeSound";

            Method privateMethod = Dog.class.getDeclaredMethod(methodName);
            privateMethod.setAccessible(true);
            String returnValue = (String) privateMethod.invoke(dog);
            System.out.println("Pies daje głos: " + returnValue);

            String methodWithParametersName = "makeMoreSound";

            Class[] methodWithParameters = new Class[]{Integer.TYPE, String.class};
            Object[] parameters = new Object[]{10, "woof"};


            Method privateMethodWithParameters = Dog.class.getDeclaredMethod(methodWithParametersName, methodWithParameters);
            privateMethodWithParameters.setAccessible(true);
            String methodWithParamsReturnValue = (String) privateMethodWithParameters.invoke(dog, parameters);

            System.out.println(methodWithParamsReturnValue);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
