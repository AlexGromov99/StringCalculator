import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.nextLine();
        char action;
        String[] data;
        String[] parts = exp.split(" ");
        if ((parts[0].length()>12) || (parts[2].length() > 12)) throw new Exception("Строка не должна быть длиннее 10 символов");
        if (exp.contains(" + ")) {
            data = exp.split(" \\+ ");
            action = '+';
        } else if (exp.contains(" - ")) {
            data = exp.split(" - ");
            action = '-';
        } else if (exp.contains(" * ")) {
            data = exp.split(" \\* ");
            action = '*';
        } else if (exp.contains(" / ")) {
            data = exp.split(" / ");
            action = '/';
        }else{
            throw new Exception("Некорректный знак действия");
        }

        if (!data[0].contains("\"")) throw new Exception("1 аргумент должен быть строкой");

        if (action == '*' || action == '/') {
            if (data[1].contains("\"")) throw new Exception("Строчку можно делить или умножать только на число");
        }
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].replace("\"", "");
        }

        if (action == '+') {
            printInQuotes(data[0] + data[1]);
        } else if (action == '*') {
            int multiplier = Integer.parseInt(data[1]);
            if (multiplier < 1 || multiplier > 10) {
                throw new Exception("Число должно быть от 1 до 10");
            }
            String result = "";
            for (int i = 0; i < multiplier; i++) {
                result+=data[0];
            }
            if (result.length()>40){
                String first40 = result.substring(0,40);
                printInQuotes(first40+"...");}
            else {
                printInQuotes (result);
            }
        } else if (action == '-') {
            int index = data[0].indexOf(data[1]);
            if(index == -1){
                printInQuotes(data[0]);
            }else{
                String result = data[0].substring(0, index);
                result+=data[0].substring(index+data[1].length());
                printInQuotes(result);
            }
        }else{
            int newLen = data[0].length()/Integer.parseInt(data[1]);
            String result = data[0].substring(0,newLen);
            printInQuotes(result);
        }


    }
    static void printInQuotes(String text){
        System.out.println("\""+text+"\"");
    }
}