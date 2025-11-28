import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;
import java.util.concurrent.ForkJoinPool;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {
    public static void main(String[] args) {

        // Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);

        // numbers.forEach(System.out::println);

        // numbers = Stream.of(1, 2, 3, 4, 5);
        // numbers.forEach(System.out::println);

        // int x = 10;
        // x = 20;

        // List<String> names = new ArrayList<>(List.of("Alice", "Bob", "Charlie",
        // "Anna", "David"));

        // // ETL - Extract, Transform, Load
        // var result = names
        // .parallelStream()
        // // .stream() // opening a stream
        // .filter(name -> name.toUpperCase().startsWith("A")) // intermediate operation
        // .map(String::toUpperCase); // intermediate operation

        // // Java builds an internal representation of the pipeline but does not
        // execute
        // // it yet.
        // // The pipeline will be executed when a terminal operation is invoked.
        // // result
        // // .findFirst() // terminal operation that reurns an Optional
        // // .ifPresent(System.out::println); // terminal operation

        // result.toList().stream()
        // .peek(name -> System.out.println("Peeking..." + name))
        // .forEach(System.out::println); // terminal operation

        // System.out.println("------------");

        // Stream<String> s = Stream.of("A", "B", "C").peek(x ->
        // System.out.println("Processing " + x));
        // System.out.println("no terminal operation called yet");
        // s.forEach(System.out::println);

        /*
         * 
         * orders.stream()
         * .filter(order -> order.isValid())
         * .peek(order -> log.debug("Picked up order: {}", order.getId()))
         * .map(order -> order.applyDiscount(10.0f))
         * .peek(order -> log.debug("Applied discount to order: {}", order.getId()))
         * .toList();
         */

        // names = new ArrayList<>(List.of("Alice", "Bob", "Charlie", "Anna", "David"));

        // var anyMatch = names.stream().anyMatch(name -> name.startsWith("A"));
        // // anyMatch = names.stream().filter(name -> name.startsWith("A")).count() >
        // 0;

        // var allMatch = names.stream().allMatch(name -> name.startsWith("A"));
        // // allMatch = names.stream().filter(name -> !name.startsWith("A")).count() ==
        // 0;

        // var noneMatch = names.stream().noneMatch(name -> name.startsWith("A"));
        // // noneMatch = names.stream().filter(name -> name.startsWith("A")).count() ==
        // 0;

        // System.out.println("Any match: " + anyMatch);
        // System.out.println("All match: " + allMatch);
        // System.out.println("None match: " + noneMatch);

        // Predicate<Integer> isEven = (number) -> number % 2 == 0;

        // Filtering and Slicing
        // Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // numbers
        // .filter(n -> n % 2 == 0) // filter is used to filter elements based on a
        // condition
        // .skip(2) // ignore first 2 even numbers
        // .limit(3) // limit is used to take only first n elements
        // // .collect(Collectors.toCollection(ArrayList::new))
        // // .collect(Collectors.toUnmodifiableList())
        // .collect(Collectors.toCollection(TreeSet::new));

        // // Create a parallel stream from an existing stream
        // names.stream().parallel();
        // names.parallelStream();

        // List<Integer> numbers = new ArrayList<>();
        // List<Integer> iList = IntStream.range(1, 10).boxed().toList();
        // iList
        // .parallelStream()
        // // .peek(i -> System.out.println("Processing " + i + " in thread " +
        // // Thread.currentThread().getName()))
        // // // .map(i -> i * 2)
        // // // .forEach(numbers::add); // Non thread safe operation
        // // .collect(Collectors.toList()); // Thread safe operation

        // // System.out.println("Final numbers: " + numbers);

        // // ForkJoinPool cusForkJoinPool = new ForkJoinPool(32);
        // // cusForkJoinPool.submit(() -> {
        // // iList
        // // .parallelStream()
        // // .peek(i -> System.out.println("Processing " + i + " in thread " +
        // // Thread.currentThread().getName()))
        // // .forEach(System.out::println); // Non thread safe operation
        // // }).join();

        // // Consumer<String> printConsumer = System.out::println;
        // // printConsumer.accept("Hello, World!");

        // // Refer to static methods
        // // System::console;
        // // Function<String, Integer> parse = Integer::parseInt;
        // // parse.apply("123");

        // // String s = "Hello,";
        // // Function<String, String> toUpperCase = String::toUpperCase;
        // // toUpperCase.apply(s);

        // // List<String> list = new LinkedList<>(List.of("one", "two", "three"));
        // // list.forEach(System.out::println);
        // // list.stream()
        // // .map(String::toUpperCase)
        // // .map(String::trim)
        // // .forEach(System.out::println);

        // // list.stream()
        // // .map(s1 -> s1.toUpperCase())
        // // .map(s1 -> s1.trim())
        // // .collect(Collectors.toCollection(ArrayList::new));

        // // String::toUpperCase is equivalent to s1 -> s1.toUpperCase() are same

        // List<String> names = List.of("Alice", "Bob", "Anna", "Charlie", "David");
        // // Commonly used Functional Interfaces

        // // Predicate -> filter, anyMatch, allMatch, noneMatch
        // Predicate<String> startsWithA = str -> str.startsWith("A");

        // long count = names.stream()
        // .filter(startsWithA)
        // .count();

        // // Function -> map, flatMap
        // Function<String, String> toUpper = String::toUpperCase;
        // List<String> upperNames = names.stream()
        // .map(toUpper)
        // .toList();

        // // BiFunction -> collect
        // BiFunction<String, String, String> concatWithSpace = (a, b) -> a + " " + b;
        // String result = concatWithSpace.apply("Hello", "World");

        // // Consumer -> forEach, peek
        // Consumer<String> printName = System.out::println;
        // // names.stream()
        // // .forEach(printName);

        // // Supplier -> used in collectors, generating streams
        // Supplier<String> stringSupplier = () -> "Generated String";
        // String generated = stringSupplier.get();

        // List<Employee> employees = List.of(
        // new Employee("Alice", 30),
        // new Employee("Bob", 25),
        // new Employee("Anna", 28),
        // new Employee("Charlie", 35));

        // employees.stream()
        // .sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
        // .forEach(e -> System.out.println(e.getName() + " - " + e.getAge()));

        // employees.stream()
        // .sorted(Comparator.comparing(Employee::getAge))
        // .forEach(e -> System.out.println(e.getName() + " - " + e.getAge()));

        // List<Person> people = List.of(
        // new Person("Alice", 30, "123 Main St"),
        // new Person("Bob", 25, "456 Oak Ave"),
        // new Person("Anna", 28, "789 Pine Rd"),
        // new Person("Charlie", 35, "101 Maple Dr"));
        // Person alice = new Person("Alice", 30, "123 Main St");

        // String text = """
        // Name : %s
        // Age : %d
        // Address : %s
        // """.formatted(alice.name(), alice.age(), alice.address());
        // System.out.println(text);

        Employee emp1 = new Employee("Test", 30);
        Employee emp2 = new Employee("Test 2", 35);

        System.out.println(emp1.hashCode());
        System.out.println(emp2.hashCode());

        Map<Employee, String> employees = new HashMap<>();
        employees.put(emp1, "Developer");
        employees.put(emp2, "Manager");

        // emp1.setName("Test Updated");

        EmployeeDto dto1 = new EmployeeDto("E001", "Alice Smith", "Developer");
        EmployeeDto dto2 = new EmployeeDto("E002", "Bob Johnson", "Manager");

        

    }
}

record EmployeeDto(String empid, String fullName, String role) {
}

// Java Records
// 1. Data is immutable
// 2. Automatic generation of equals, hashCode, toString, constructors, and
// accessors
record Person(
        String name,
        int age,
        String address) implements Serializable {

    // Nested record
    record Address(String city, String state, String zipCode) {
    }

    public Person {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
    }

    public Person(String name, int age) {
        this(name, age, "Unknown");

    }

}

// Immutable DTO
final class Employee {

    private final String name;
    private final int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        return Objects.equals(age, other.age) && Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', age=" + age + "}";
    }
}

abstract sealed class Shape permits Circle, Rectangle {
}

final class Circle extends Shape {
}

sealed class Rectangle extends Shape permits Square, Parallelogram {
}

final class Square extends Rectangle {
}

non-sealed class Parallelogram extends Rectangle {

    static String printShape(Shape shape) {

        // Pattern Matching for switch
        return switch (shape) {
            case Circle c -> "This is a Circle";
            case Square s -> "This is a Square";
            case Parallelogram p -> "This is a Parallelogram";
            case Rectangle r -> "This is a Rectangle";
        };
    }
}

// Sealed interface
sealed interface Paytments permits Cash, CreditCard, UPI {
}

final class Cash implements Paytments {
}

final class CreditCard implements Paytments {
}

non-sealed class UPI implements Paytments {
}