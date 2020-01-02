package ru.job4j.pojo;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class College {

    public static void main(String[] args) {
        Student student = new Student();
        student.setName("Иванов Иван Иванович");
        student.setGroup("1AC");
        Calendar calendar = new GregorianCalendar(2019, 8, 2);
        Date date = calendar.getTime();
        student.setDateIn(date);
        System.out.println("Ф.И.О.: " + student.getName());
        System.out.println("Группа: " + student.getGroup());
        System.out.println("Дата принятия: " + student.getDateIn());
    }
}
