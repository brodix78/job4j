package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DummyBotTest {
    @Test
    public void WhenGreetBot() {
        assertThat(
                DummyBot.answer("Привет, Бот."),
                is("Привет, умник.")
        );
    }

    @Test
    public void WhenByeBot() {
        assertThat(
                DummyBot.answer("Пока, Бот."),
                is("До скорой встречи.")
        );
    }

    @Test
    public void  WhenUknownBot() {
        assertThat(
                DummyBot.answer("Уляля дружищеб чего тупим?"),
                is("Это ставит меня в тупик. Задайте другой вопрос.")
        );
    }
}
