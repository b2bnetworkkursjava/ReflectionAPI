package pl.b2bnetwork.animal;

public class Dog extends Animal {
    private String owner;

    private String makeSound() {return "bark";}

    private String makeMoreSound(int number, String sound) {
        return "Dog does: " + sound + "\nhow many times: " + number;
    }

    public Dog(String owner) {
        this.owner = owner;
    }

    public Dog() {
    }
}
