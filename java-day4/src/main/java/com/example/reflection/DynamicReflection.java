package com.example.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class DynamicReflection {
    public static void main(String[] args) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        // Object creation
        User zhang =  new User("zhang", 18);
        System.out.println(zhang.toString());

        Class<User> userCls = User.class;
        Constructor<User> constructor = userCls.getDeclaredConstructor(String.class, Integer.class);
        constructor.newInstance("Arun", 22);

        Field id = User.class.getDeclaredField("id");
        id.setAccessible(true);
        id.set(zhang, 22);
    }
}

class User {
    private final String name;
    private final int age;
    private String id;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
