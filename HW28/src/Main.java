import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

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
    }
    private static void sizeConverter(double size) {
        if (size <= KB_SIZE) {
            System.out.printf("Ваша папка или фаил весит: %.2f байт.", size);
        } else if (size <= MB_SIZE) {
            System.out.printf("Ваша папка или фаил весит: %.2f килобайт.", size/KB_SIZE);
        } else if (size <= GB_SIZE) {
            System.out.printf("Ваша папка или фаил весит: %.2f мегабайт.", size/MB_SIZE);
        } else {
            System.out.printf("Ваша папка или фаил весит: %.2f гигабайт.", size/GB_SIZE);
        }
    }

    private static void setSale() {
        //todo Тут написать код для ДЗ #3
    }
}
