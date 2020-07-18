package ru.job4j.exam;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.BaseStubbing;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SmallRouter.class, BigRouter.class})

public class BigRouterTest {

    Explorer explorer = Mockito.spy(Explorer.class);
    CameraFactory factory = new CameraFactory();
    AtomicInteger downloaded = new AtomicInteger();
    AtomicInteger downloading = new AtomicInteger();
    ExecutorService executor = Mockito.spy(ExecutorService.class);

    @Test
    public void whenReceiveTwoLinksThenReturnsTwoFuture() throws Exception {
        BigRouter<Camera> router = new BigRouter<>(explorer, factory, executor, downloaded, downloading);
        Map<String, String> one = Map.of("sourceDataUrl", "one", "tokenDataUrl", "two");
        Map<String, String> two = Map.of("sourceDataUrl", "three", "tokenDataUrl", "four");
        Mockito.when(explorer.call()).thenReturn(List.of(one, two));
        Future<Camera> future = Mockito.spy(Future.class);
        SmallRouter<Camera> small = Mockito.spy(new SmallRouter<>(explorer, factory, executor,
                one, downloaded, downloading));
        PowerMockito.whenNew(SmallRouter.class).withArguments(explorer, factory, executor,
                one, downloaded, downloading).thenReturn(small);
        PowerMockito.whenNew(SmallRouter.class).withArguments(explorer, factory, executor,
                two, downloaded, downloading).thenReturn(small);
        Mockito.when(executor.submit(small)).thenReturn(future);
        assertThat(router.call(), is(List.of(future, future)));
    }

    @Test
    public void whenReceiveTwoLinksOneCorrectThenReturnsOneFuture() throws Exception {
        BigRouter<Camera> router = new BigRouter<>(explorer, factory, executor, downloaded, downloading);
        Map<String, String> one = Map.of("sourceDataUrl", "one", "tokenDataUrl", "two");
        Map<String, String> two = Map.of("sourceUrl", "three", "tokenDataUrl", "four");
        Mockito.when(explorer.call()).thenReturn(List.of(one, two));
        Future<Camera> future = Mockito.spy(Future.class);
        SmallRouter<Camera> small = Mockito.spy(new SmallRouter<>(explorer, factory, executor,
                one, downloaded, downloading));
        PowerMockito.whenNew(SmallRouter.class).withArguments(explorer, factory, executor,
                one, downloaded, downloading).thenReturn(small);
        PowerMockito.whenNew(SmallRouter.class).withArguments(explorer, factory, executor,
                two, downloaded, downloading).thenReturn(small);
        Mockito.when(executor.submit(small)).thenReturn(future);
        assertThat(router.call(), is(List.of(future)));
    }
}