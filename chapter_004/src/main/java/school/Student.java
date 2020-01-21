package school;

import java.util.Objects;

public class Student {
    private int score;
    private String surname;
    public Student(int score) {
        this.score = score;
    }
    public Student(String surname, int score) {
        this.surname = surname;
        this.score = score;
    }
    public int getScore() {
        return score;
    }
    public String getSurname() {
        return surname;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Student student = (Student) obj;
        return Objects.equals(this.surname, student.surname);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.surname);
    }
}