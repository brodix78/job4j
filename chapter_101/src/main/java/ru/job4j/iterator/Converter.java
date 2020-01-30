package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter<Integer> implements Iterator {

    private Iterator<Iterator<Integer>> iterators;

    private Iterator<Integer> iterator;

    public Converter(Iterator<Iterator<Integer>> iterators) {
        this.iterators = iterators;
        /* Здесь меня просите поставить проверку hasNext - зачем ?
        *  любой hasNext, как и последующий iterators.next()  выкидывают тут NullPointerException в
        *  случае если пытаться обработать Iterator<Iterator<Integer>> = null, в противном случае
        *  this.iterator = this.iterators.next() штатно отрабатывает и нет необходимости наворачивать
        *  локальный hasNext.
        *  Вот хоть убейте: не понимаю какой в нем (hasNext) здесь смысл?
        */
        this.iterator = this.iterators.next();
    }

    @Override
    public boolean hasNext() {
        while (!this.iterator.hasNext() && this.iterators.hasNext()) {
                this.iterator = this.iterators.next();
            }
        return this.iterator.hasNext();
    }

    @Override
    public Object next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException("Storage is over");
        }
        return this.iterator.next();
    }
}
