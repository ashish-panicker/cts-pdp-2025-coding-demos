package com.example.list;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListMain {
    // List is an ordered collection of elements
    // List uses zero based indexing
    // List allows duplicates
    public static void main(String[] args) throws InterruptedException {
        // List.of() returns an unmodifiable list
        List<String> list = new ArrayList<>(List.of("A", "B", "C"));
        list.add("D");
        list.add("D");
        System.out.println(list);
        // Most common implementation of List is ArrayList which uses Dynamic Array

        // Another implementation is LinkedList which uses Doubly Linked List
        List<Integer> numbers = new LinkedList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        System.out.println(numbers);

        // Another implementation of List is CopyOnWriteArrayList
        List<String> copyNameList = new CopyOnWriteArrayList<>(); // []
        copyNameList.add("John"); // [John]
        copyNameList.add("Mary"); // New array created and [John, Mary]
        System.out.println(copyNameList);
        System.out.println();
        // Creates an immutable copy of the list
        for (String name : copyNameList) {
            // Why is Jhonson not printed in the output?
            // This line will create a new array, copy the old array values,
            // and add Johnson to it, and then set the reference to the new array
            copyNameList.add("Johnson");
            System.out.println(name);
        }

        // Wraps the ArrayList into a synchronized list
        List<String> syncedNamesList = Collections.synchronizedList(new ArrayList<>());
        List<String> asyncNamesList = new ArrayList<>();


        Runnable writer = () -> {
            for (int i = 0; i < 100; i++) {
                asyncNamesList.add(String.valueOf(i));
            }
        };

        Thread writerThread1 = new Thread(writer);
        Thread writerThread2 = new Thread(writer);
        Thread writerThread3 = new Thread(writer);

        writerThread1.start();
        writerThread2.start();
        writerThread3.start();

        writerThread1.join();
        writerThread2.join();
        writerThread3.join();

        System.out.println("Size of syncedNamesList: " + asyncNamesList.size() + ".");

        List<Book> books = new ArrayList<>();
        books.add(new Book("War and Peace", "Leo Tolstoy"));
        books.add(new Book("Old Man and the Sea", "Ernest Hemingway"));
        books.add(new Book("The Catcher in the Rye", "Jane Austen"));
        books.forEach(System.out::println);

        // Sort by author
        System.out.println("Sort by author:");
        books.sort((b1, b2) -> b1.getAuthor().compareTo(b2.getAuthor()));
        books.forEach(System.out::println);

        // Sort by title
        System.out.println("Sort by title:");
        books.sort(Comparator.comparing(Book::getTitle));
        books.forEach(System.out::println);

        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });

        Collections.sort(books, new Book.TitleComparator());

        System.out.println();
        ListIterator<Book> bookListIterator = books.listIterator();
        while (bookListIterator.hasNext()) {
            System.out.println(bookListIterator.next());
        }
        System.out.println();
        while (bookListIterator.hasPrevious()) {
            System.out.println(bookListIterator.previous());
        }

        List<String> list1 = new ArrayList<>();
        list1.add("A");
        list1.add("B");
        list1.add("C");
        for (String s : list1) {
            // Fail fast iterators
            // expectedModCount vs modCount
            // modCount != expectedModCount -> throw new ConcurrentModificationException()
            // list1.add("D"); Error
            System.out.println(s);
        }

        // Fail safe iterators
        // CopyOnWriteArrayList, CopyOnWriteArraySet

        List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5); // Returns a fixed size list
        // numList.add(6); // Error
        numList.set(2, 10);
        System.out.println(numList);

        numList = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        // subList(fromIndex, toIndex) -> fromIndex (inclusive) to toIndex (exclusive)
        List<Integer> subList = numList.subList(1, 2); // [2]
        // sublist creates a view based on the original list
        // when a sublist is modified, the original list is also modified
        subList.set(0, 10);
        System.out.println(numList);
        // structural changes to the original list invalidates the sublist
        numList.add(6);
        System.out.println(subList.get(0));

    }
}

class Book implements Comparable<Book> {

    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Book [title=" + title + ", author=" + author + "]";
    }

    @Override
    public int compareTo(Book o) {
        return this.title.compareTo(o.title);
    }

    static class TitleComparator implements Comparator<Book> {
        @Override
        public int compare(Book b1, Book b2) {
            return b1.getTitle().compareTo(b2.getTitle());
        }
    }
}


