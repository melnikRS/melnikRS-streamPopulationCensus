import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        System.out.println("--- Количество несовершеннолетних ---");
        System.out.println(persons.stream()
                .filter(a -> a.getAge() < 18)
                .count());
        System.out.println();

        System.out.println("--- Список фамилий призывников ---");
        System.out.println(persons.stream()
                .filter(s -> s.getSex().equals(Sex.MAN))
                .filter(a -> a.getAge() >= 18 || a.getAge() <  27)
                .map(Person::getFamily)
                .collect(Collectors.toList()));
        System.out.println();

        System.out.println("--- Список потенциально работоспособных людей с высшим образованием ---");
        System.out.println(persons.stream()
                .filter(e -> e.getEducation() == Education.HIGHER)
                .filter(a -> a.getAge() >= 18)
                .filter(s -> s.getSex() == Sex.WOMAN ? s.getAge() <= 60 : s.getAge() <= 65)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList()));
    }
}