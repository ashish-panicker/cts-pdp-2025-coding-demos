# Java Day 3

## Generics

Allows us to create a type that can be used with multiple data types or classes. This allows us to ensure type safety.

**Type Parameters**

`T` - Type Parameter
`E` - Element Type
`K` - Key Type
`V` - Value Type


## Annotations

They are metadata that can be applied on:
- Classes
- Methods
- Fields
- Parameters

They do not execute any code but provide extra information to the compiler or frameworks.

Example: `@Override` `@Deprecated`

An annotation is essentialy:
- A special interface
- Uses the `@interface` keyword
- The methods of the interface are the annotation's attributes


```java
public @interface Author {
    String name() default "";
}
```

**Meta Annotations**

They are annotations that can be applied on other annotations.

`@Retention` `@Target` `@Documented` `@Inherited` `@Repeatable`