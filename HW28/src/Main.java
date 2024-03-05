import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static final long KB_SIZE = 1024;
    private static final long MB_SIZE = KB_SIZE * 1024;
    private static final long GB_SIZE = MB_SIZE * 1024;

    public static void main(String[] args) {
        System.out.println("============= ДЗ 2 =============");
        System.out.println("Введите путь к файлу или папке: ");
        String path = new Scanner(System.in).nextLine();
        printSize(path);

        System.out.println("============= ДЗ 3 =============");
        setSale();
    }

    private static void printSize(String path) {
        File file = new File(path);
        long size;
        try {
            if (file.isFile()) {
                size = file.length();
            } else {

                size = Files.walk(Path.of(path))
                        .filter(p -> p.toFile().isFile())
                        .mapToLong(p -> p.toFile().length())
                        .sum();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        sizeConverter(size);
    }
    private static void sizeConverter(double size) {
        if (size <= KB_SIZE) {
            System.out.printf("Ваша папка или фаил весит: %.2f байт.\n", size);
        } else if (size <= MB_SIZE) {
            System.out.printf("Ваша папка или фаил весит: %.2f килобайт.\n", size/KB_SIZE);
        } else if (size <= GB_SIZE) {
            System.out.printf("Ваша папка или фаил весит: %.2f мегабайт.\n", size/MB_SIZE);
        } else {
            System.out.printf("Ваша папка или фаил весит: %.2f гигабайт.\n", size/GB_SIZE);
        }
    }

    private static void setSale() {
        try {
            List<String> strings = Files.readAllLines(Path.of("HW28/data/car_price.txt"));
            List<Car> cars = strings.stream()
                    .map(s -> {
                            String[] items = s.split("\\s");
                            if (items.length != 3){
                                throw new RuntimeException("Wrong line!!!");
                            }
                            return new Car(items[0],
                                    Integer.parseInt(items[1]),
                                    Double.parseDouble(items[2])
                            );
            }).collect(Collectors.toList());

            int correctYear = LocalDate.now().getYear();
            List<String> carBySale = cars.stream()
                    .map(car -> {
                        int age22 = correctYear - car.getAge();
                        double discount = age22 > 5 ? 0.05 : 0.02;
                        double discountPrice = car.getPrice() * (1 - discount);
                        return car.getMark() + " " + car.getAge() + " " + discountPrice;
                    }).collect(Collectors.toList());
            Files.write(Path.of("HW28/data/car_prise2.txt"), carBySale);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
