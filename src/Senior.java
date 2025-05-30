public class Senior extends Person {
    
    public Senior(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Senior{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                '}';
    }
    
}
