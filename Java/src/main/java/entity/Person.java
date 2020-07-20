package entity;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    /**
     * 重写equals方法
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        // 如果是同一个对象返回true，反之返回false
        if (this == obj) {
            return true;
        }
        // 判断是否类型相同
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Person person = (Person) obj;
        return name.equals(person.name) && age == person.age;
    }
}