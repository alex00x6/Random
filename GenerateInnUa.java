import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateInnUa {

    /**
     * генерация ИНН Украины (10 символов)
     */
    public String generateInn() {
        String birth = processLength(ThreadLocalRandom.current().nextInt(20000, 40000), 5);
        String index = processLength(new Random().nextInt(9999), 4);
        String result = birth + index;
        String checksum = generateChecksumInn(result);
        result = result + checksum;
        return result;
    }

    private String generateChecksumInn(String input) {
        return String.valueOf(
                ((-1 * charAsInt(input.charAt(0)) +
                        5 * charAsInt(input.charAt(1)) +
                        7 * charAsInt(input.charAt(2)) +
                        9 * charAsInt(input.charAt(3)) +
                        4 * charAsInt(input.charAt(4)) +
                        6 * charAsInt(input.charAt(5)) +
                        10 * charAsInt(input.charAt(6)) +
                        5 * charAsInt(input.charAt(7)) +
                        7 * charAsInt(input.charAt(8))) %
                        11) %
                        10
        );
    }

    /**
     * метод форматирует число, добавляя при необходимости нули
     * т.е если нам нужна шестизначная цифра, а random сгенерировал 9999 - метод вернет значение 009999
     */
    String processLength(String input, int length) {
        if (input.length() < length) {
            return String.format("%0" + length + "d", input);
        }
        return input;
    }

    /**
     * метод форматирует число, добавляя при необходимости нули
     * т.е если нам нужна шестизначная цифра, а random сгенерировал 9999 - метод вернет значение 009999
     */
    String processLength(int input, int length) {
        String converted = String.valueOf(input);
        if (converted.length() < length) {
            return String.format("%0" + length + "d", input);
        }
        return String.valueOf(input);
    }

    int charAsInt(char c) {
        return Character.getNumericValue(c);
    }

}
