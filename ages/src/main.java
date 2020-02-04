public class main {
    public static void main(String[] args) {
        int age1 = 25;
        int age2 = 30;
        int age3 = 40;
        int min;
        int middle;
        int max;
        int age;
        for (age = 1; age < 41; age++) {
            if (age == age1) {
                min = age;
                System.out.println("Minimal age: " + min);
            } else if (age == age2) {
                middle = age;
                System.out.println("Middle age: " + middle);
            } else if (age == age3) {
                max = age;
                System.out.println("Maximum age: " + max);
            }
        }
    }
}