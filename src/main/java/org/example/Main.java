package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long underagesCount = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        List<String> conscripts = persons.stream()
                .filter(x -> x.getAge() >= 18 && x.getAge() < 27 && x.getSex() == Sex.MAN)
                .map(x -> x.getFamily())
                .collect(Collectors.toList());
        List<Person> higherEduWorkers = persons.stream()
                .filter(x -> (x.getAge() >= 18) && (x.getAge() < 65) && (x.getSex() == Sex.MAN) && (x.getEducation() == Education.HIGHER) ||
                        (x.getAge() >= 18) && (x.getAge() < 60) && (x.getSex() == Sex.WOMAN) && (x.getEducation() == Education.HIGHER))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        higherEduWorkers.stream()
                .forEach(x -> System.out.println(x.getName() + " " + x.getFamily() + ", " + x.getSex() + ", age of " + x.getAge()));

    }
}