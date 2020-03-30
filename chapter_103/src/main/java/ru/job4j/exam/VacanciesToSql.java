package ru.job4j.exam;

import java.util.List;
import java.util.Properties;

public class VacanciesToSql extends SqlTable{

    private List<Vacancy> vacancies;

    public VacanciesToSql(Properties initData, List<Vacancy> vacancies) {
        this.initData = initData;
        this.vacancies = vacancies;
        this.tableName = "vacancies";
        this.tableInit = "CREATE TABLE vacancies (id SERIAL PRIMARY KEY, name VARCHAR(200), text TEXT, link VARCHAR(500))";
    }

    public void transfer() {
        for (Vacancy vacancy : vacancies) {
            if (receiveFromBase(statement("SELECT * FROM vacancies WHERE name=?", List.of(vacancy.name))).size() == 0) {
                updateBase(statement("INSERT INTO vacancies(name, text, link) VALUES(?, ?, ?)",
                        List.of(vacancy.name, vacancy.text, vacancy.link)));
            }
        }
    }
}