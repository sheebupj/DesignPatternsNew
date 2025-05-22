package Builder;

public class Person {

    Integer id;
    String name;
    String address;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Person(PersonBuilder builder){
        this.id=builder.id;
        this.name=builder.name;
        this.address=builder.address;

    }

    public static void main(String[] args) {
       Person person=
                PersonBuilder.getInstance()
                .setId(1)
                .setName("sheebu")
                .setAddress("paremal")
                .build();

        System.out.println(person);


    }
}
class PersonBuilder{
    Integer id;
    String name;
    String address;

    public PersonBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public PersonBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder setAddress(String address) {
        this.address = address;
        return this;
    }
    static  PersonBuilder getInstance(){ return new PersonBuilder();}

    Person build(){
        return new Person(this);
    }
}
