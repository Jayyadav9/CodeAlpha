import java.util.*;

public class StudentGradeTracker {

    static class Student {
        String name;
        ArrayList<Integer> grades;

        Student(String name) {
            this.name = name;
            grades = new ArrayList<>();
        }

        void addGrade(int grade) {
            grades.add(grade);
        }

        double getAverage() {
            int sum = 0;
            for (int grade : grades) {
                sum += grade;
            }
            return grades.size() > 0 ? (double) sum / grades.size() : 0;
        }

        int getHighest() {
            return Collections.max(grades);
        }

        int getLowest() {
            return Collections.min(grades);
        }

        void displayReport() {
            System.out.println("Student Name: " + name);
            System.out.println("Grades: " + grades);
            System.out.println("Average Score: " + getAverage());
            System.out.println("Highest Score: " + getHighest());
            System.out.println("Lowest Score: " + getLowest());
            System.out.println("-----------------------------------------");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int numStudents = sc.nextInt();

        for (int i = 0; i < numStudents; i++) {
            sc.nextLine(); // clear input buffer
            System.out.print("Enter name of student " + (i + 1) + ": ");
            String name = sc.nextLine();
            Student s = new Student(name);

            System.out.print("Enter number of grades for " + name + ": ");
            int numGrades = sc.nextInt();

            for (int j = 0; j < numGrades; j++) {
                System.out.print("Enter grade " + (j + 1) + ": ");
                int grade = sc.nextInt();
                s.addGrade(grade);
            }

            students.add(s);
        }

        System.out.println("\n----- STUDENT SUMMARY REPORT -----");
        for (Student s : students) {
            s.displayReport();
        }

        sc.close();
    }
}
