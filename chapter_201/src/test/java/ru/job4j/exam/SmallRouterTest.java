package ru.job4j.exam;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SmallRouterTest {
    Explorer explorer = Mockito.spy(Explorer.class);
    CameraFactory factory = new CameraFactory();
    AtomicInteger downloaded = new AtomicInteger();
    AtomicInteger downloading = new AtomicInteger();
    ExecutorService executor = Mockito.spy(ExecutorService.class);


    @Test
    public void whenGetCorrectThenPatternCamera() throws ExecutionException, InterruptedException {
        SmallRouter<Camera> router = new SmallRouter<>(explorer, factory, executor,
                Map.of("sourceDataUrl", "one", "tokenDataUrl", "two"), downloaded, downloading);
        Explorer explorerOne = Mockito.spy(Explorer.class);
        Explorer explorerTwo = Mockito.spy(Explorer.class);
        Future<List<Map<String, String>>> futureOne = Mockito.spy(Future.class);
        Future<List<Map<String, String>>> futureTwo = Mockito.spy(Future.class);
        Mockito.when(explorer.getInstance("one")).thenReturn(explorerOne);
        Mockito.when(explorer.getInstance("two")).thenReturn(explorerTwo);
        Mockito.when(executor.submit(explorerOne)).thenReturn(futureOne);
        Mockito.when(executor.submit(explorerTwo)).thenReturn(futureTwo);
        Mockito.when(futureOne.get()).thenReturn(List.of(Map.of("id", "1", "urlType", "url",
                "videoUrl", "videoUrl")));
        Mockito.when(futureTwo.get()).thenReturn(List.of(Map.of("value", "zasada",
                "ttl", "100")));
        Camera pattern = new Camera("1", "url", "videoUrl", "zasada", 100);
        assertThat(router.call(), is (pattern));
    }

    @Test
    public void whenGetIncorrectDataThenNull() throws ExecutionException, InterruptedException {
        SmallRouter<Camera> router = new SmallRouter<>(explorer, factory, executor,
                Map.of("sourceDataUrl", "one", "tokenDataUrl", "two"), downloaded, downloading);
        Explorer explorerOne = Mockito.spy(Explorer.class);
        Explorer explorerTwo = Mockito.spy(Explorer.class);
        Future<List<Map<String, String>>> futureOne = Mockito.spy(Future.class);
        Future<List<Map<String, String>>> futureTwo = Mockito.spy(Future.class);
        Mockito.when(explorer.getInstance("one")).thenReturn(explorerOne);
        Mockito.when(explorer.getInstance("two")).thenReturn(explorerTwo);
        Mockito.when(executor.submit(explorerOne)).thenReturn(futureOne);
        Mockito.when(executor.submit(explorerTwo)).thenReturn(futureTwo);
        Mockito.when(futureOne.get()).thenReturn(List.of(Map.of("id", "1", "urlxType", "url",
                "videoUrl", "videoUrl")));
        Mockito.when(futureTwo.get()).thenReturn(List.of(Map.of("value", "zasada",
                "ttl", "100")));
        Camera pattern = new Camera("1", "url", "videoUrl", "zasada", 100);
        Assert.assertNull(router.call());
    }

    @Test
    public void whenGetExceptionThenReturnNull() throws ExecutionException, InterruptedException {
        SmallRouter<Camera> router = new SmallRouter<>(explorer, factory, executor,
                Map.of("sourceDataUrl", "one", "tokenDataUrl", "two"), downloaded, downloading);
        Explorer explorerOne = Mockito.spy(Explorer.class);
        Explorer explorerTwo = Mockito.spy(Explorer.class);
        Future<List<Map<String, String>>> futureOne = Mockito.spy(Future.class);
        Future<List<Map<String, String>>> futureTwo = Mockito.spy(Future.class);
        Mockito.when(explorer.getInstance("one")).thenReturn(explorerOne);
        Mockito.when(explorer.getInstance("two")).thenReturn(explorerTwo);
        Mockito.when(executor.submit(explorerOne)).thenReturn(futureOne);
        Mockito.when(executor.submit(explorerTwo)).thenReturn(futureTwo);
        Mockito.when(futureOne.get()).thenReturn(List.of(Map.of("id", "1", "urlxType", "url",
                "videoUrl", "videoUrl")));
        Mockito.when(futureTwo.get()).thenThrow(new InterruptedException());
        Camera pattern = new Camera("1", "url", "videoUrl", "zasada", 100);
        Assert.assertNull(router.call());
    }

}