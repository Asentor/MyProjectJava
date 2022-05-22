import java.util.*;

public class Main {
    private static final ArrayList<String> romanNumbers = new ArrayList<>(Arrays.asList("O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"));
    private static final ArrayList<String> sign = new ArrayList<>(Arrays.asList("+", "-", "*", "/"));

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("Введите выражение в формате: 2 + 3");
        String input = sc.nextLine();

        String result = calc(input);
        System.out.println(result);
    }

    public static String calc(String input) throws Exception {
        String result = "";

        String[] words = input.split(" "); //  по пробелу

        if (words.length != 3) {
            throw new Exception("Не правильно введено выражение");
        }

        if (!sign.contains(words[1])) {
            throw new Exception("Не правильно введен знак");
        }

        if (isArabic(words[0], words[2])) {
            int operand1 = Integer.parseInt(words[0]);
            int operand2 = Integer.parseInt(words[2]);

            result = String.valueOf(calculate(operand1, operand2, words[1]));

        } else if (isRomanian(words[0], words[2])) {
            int operand1 = fromRomanToArabic(words[0]);
            int operand2 = fromRomanToArabic(words[2]);

            int r = calculate(operand1, operand2, words[1]);

            if (r < 1) {
                throw new Exception("Результатом работы калькулятора с римскими числами могут быть только положительные числа");
            }

            result = String.valueOf(romanNumbers.get(r)); // get у ArrayList - взятие по индексу
        } else {
            throw new Exception("Вы ввели числа из разных систем изчисления");
        }

        return result;
    }


    private static int fromRomanToArabic(String roman) {
        int arabic = romanNumbers.indexOf(roman);
        return arabic;
    }

    private static int calculate(int op1, int op2, String operator) {
        int result = 0;

        switch (operator) {
            case "+":
                result = op1 + op2;
                break;
            case "-":
                result = op1 - op2;
                break;
            case "*":
                result = op1 * op2;
                break;
            case "/":
                result = op1 / op2;
                break;
        }

        return result;
    }

    private static boolean isRomanian(String op1, String op2) throws Exception {
        int index1 = romanNumbers.indexOf(op1); // в случае если в списке нет элемента indexOf возвращает -1
        int index2 = romanNumbers.indexOf(op2);

        if (index1 > 10 || index2 > 10) {
            throw new Exception("Введены не подходящие числа");
        }

        if ((index1 > 0) && (index2 > 0)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isArabic(String op1, String op2) throws Exception {
        try {
            int a = Integer.parseInt(op1);
            int b = Integer.parseInt(op2);

            if ((a > 0 && a <= 10) && (b > 0 && b <= 10)) {
                return true;
            } else {
                throw new Exception("Введены не подходящие числа");
            }
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
