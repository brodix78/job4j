package ru.job4j.hql;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class StoreTest {

    // Тестирование реализую через Store без rollback, чистил sql ручками через pqAdmin

    @Test
    public void saveGetByIdAndNameAndDeleteAllAfter() {
        Store store = new Store();
        Candidate one = Candidate.of("First", "Medium experience", 1000);
        Candidate two = Candidate.of("Second", "Big experience", 2000);
        Candidate three = Candidate.of("Third", "Great experience", 3000);
        store.saveOrUpdateObj(one);
        store.saveOrUpdateObj(two);
        store.saveOrUpdateObj(three);
        assert (one.getId() > 0 && two.getId() > 0 && three.getId() > 0);
        Candidate test = store.getCandidateById(one.getId());
        assertThat(test, is(one));
        test = store.getCandidateByName(two.getName());
        assertThat(test, is(two));
        int id = three.getId();
        assertThat(store.delCandidateById(three.getId()), is(1));
        assertNull(store.getCandidateById(id));
    }

}
