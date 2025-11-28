package com.example.swt_expr;

public class SwitchExprMain {

    // https://dev.java/learn/language-basics/switch-expression/

    public static void main(String[] args) {
        int day = 1;
        switch (day){
            case 1: System.out.println("Monday"); break;
            case 2: System.out.println("Tuesday"); break;
            case 3: System.out.println("Wednesday"); break;
            case 4: System.out.println("Thursday"); break;
            case 5: System.out.println("Friday"); break;
            case 6: System.out.println("Saturday"); break;
            case 7: System.out.println("Sunday"); break;
            default: System.out.println("Invalid day");
        }

        // switch-expressions
        String weekday = switch (day) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            case 7 -> "Sunday";
            default -> throw new IllegalArgumentException("Invalid day: " + day);
        };

        String dayOfWeek = switch (day) {
            case 1 -> {
                System.out.println("Monday");
                yield "Weekday";
            }
            default -> "Weekend";
        };

    }
}
