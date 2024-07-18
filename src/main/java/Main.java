import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("СТРОКОВЫЙ КАЛЬКУЛЯТОР принимаем следующие операции(С учётом кавычек): \n" +
                "\"Первая строка\" + или - \"Вторая строка\" \n" +
                "\"Первая строка\" * или / на число от 0 до 10");
        Scanner scanner = new Scanner(System.in);
        System.out.println(lineCheckingMethod(scanner.nextLine()));


    }

    private static StringBuilder lineCheckingMethod(String input) {
        input.trim();
        StringBuilder answer = new StringBuilder();
//       Проверка на начальную кавычку
        if(input.charAt(0) == '"'){
//            Проверка на наличие второй кавычки
            if(input.indexOf("\"", 1)== -1){
                try {
                    throw new Exception();
                } catch (Exception e) {
                    throw new IllegalArgumentException(e);
                }
            } else {
//               Добавление первой строки в StringBuilder
                answer.append(input.substring(0,(input.indexOf("\"", 1)) ));
//                Проверка на математические действия
                String afterFirstString = input.substring((input.indexOf("\"", 1))+1).trim();
                switch (afterFirstString.charAt(0)) {
                    case ('+'): return plusMethod(afterFirstString,answer);
                    case ('-'): return minusMethod(afterFirstString,answer);
                    case ('*'): return multiplyMethod(afterFirstString,answer);
                    case ('/'): return divideMethod(afterFirstString,answer);
                    default:
                        try {
                            throw new Exception();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                }
            }
        }
        return answer;
    }

    private static StringBuilder divideMethod(String afterFirstString, StringBuilder answer) {
        StringBuilder newAnswer = new StringBuilder();
        afterFirstString = afterFirstString.substring(1).trim();
        for (int i = 0; i < afterFirstString.length(); i++) {
            if(!Character.isDigit(afterFirstString.charAt(i)))
                try {
                    throw new Exception();
                } catch (Exception e) {
                    throw new IllegalArgumentException(e);
                }
        }
        int dividerNumber = Integer.valueOf(afterFirstString);
        if(dividerNumber > 10 || dividerNumber < 0)
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        String divideString = answer.substring(1);
        divideString = divideString.substring(0,divideString.length() / dividerNumber);
        newAnswer.append("\"");
        newAnswer.append(divideString);
        newAnswer.append("\"");
        return newAnswer;
    }

    private static StringBuilder multiplyMethod(String afterFirstString, StringBuilder answer) {
        afterFirstString = afterFirstString.substring(1).trim();
        for (int i = 0; i < afterFirstString.length(); i++) {
            if(!Character.isDigit(afterFirstString.charAt(i)))
                try {
                    throw new Exception();
                } catch (Exception e) {
                    throw new IllegalArgumentException(e);
                }
        }
        int multiplyNumber = Integer.valueOf(afterFirstString);
        if(multiplyNumber > 10 || multiplyNumber < 0)
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        String addForMultiply = answer.substring(1);
        multiplyNumber = multiplyNumber-1;
        while (multiplyNumber>0){
            answer.append(addForMultiply);
            multiplyNumber--;
        }
        answer.append("\"");
        if(answer.length()>40) {
            answer = new StringBuilder(answer.substring(0, 40));
            answer.append("...");
        }

        return answer;
    }

    private static StringBuilder minusMethod(String afterFirstString, StringBuilder answer) {
        int firstQuotes=0;
        int secondQuotes=afterFirstString.charAt(afterFirstString.length()-1);
        StringBuilder newAnswer = new StringBuilder(answer);
        for (int i = 1; i < afterFirstString.length(); i++) {
            if(!Character.isWhitespace(afterFirstString.charAt(i))){
                if(afterFirstString.charAt(i)=='"' & secondQuotes=='"'){
                    firstQuotes=i;
                    break;
                }else try {
                    throw new Exception();
                } catch (Exception e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }
        String s = String.valueOf(answer);
        String secondWordWithOutQuotes = afterFirstString.substring(firstQuotes+1,afterFirstString.length()-1);
        if(s.contains(secondWordWithOutQuotes)){
            s = s.replace(secondWordWithOutQuotes,"");
            newAnswer = new StringBuilder(s);

        }

        return newAnswer.append("\"");
    }

    private static StringBuilder plusMethod(String afterFirstString, StringBuilder answer) {
        int firstQuotes=0;
        int secondQuotes=afterFirstString.charAt(afterFirstString.length()-1);

        for (int i = 1; i < afterFirstString.length(); i++) {
            if(!Character.isWhitespace(afterFirstString.charAt(i))){
                if(afterFirstString.charAt(i)=='"' & secondQuotes=='"'){
                    firstQuotes=i;
                    break;
                }else try {
                    throw new Exception();
                } catch (Exception e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }
        return answer.append(afterFirstString.substring(firstQuotes+1));
    }


}
