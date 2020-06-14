package ru.job4j.syncro;

public final class DCLSingleton {
    private static volatile DCLSingleton inst;
    // На скоько я понял в данной ситуации может возникнуть момент
    // при котором нити могут одновременно пройти первую проверку, и после начала синхронихзации,
    // первая допущенная ветвь инициализирует переменную возможно возникновение ситуации при которой последующие ветви,
    // не "увидят" этого изменения (правильнее наверное не успеют заметить) и обработав имеющееся в регистре данные
    // (а тут вроде как ничего нет) после наступления своей очереди обработки второго услолвия могут инициализиовать
    // inst заново.
    public static DCLSingleton instOf() {
        if (inst == null) {
            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                }
            }
        }
        return inst;
    }

    private DCLSingleton() {
    }

}
