package ru.job4j.exam;

import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import java.util.Map;

public class CameraFactoryTest{

    @Test
    public void createCameraWithCorrectData() {
        Camera pattern = new Camera("1", "url", "videoUrl", "zasada", 100);
        Camera rsl = new CameraFactory().getInstance(Map.of("id", "1", "urlType", "url",
                "videoUrl", "videoUrl",
                "value", "zasada",
                "ttl", "100"));
        assertThat(pattern, is(rsl));
    }

    @Test
    public void createCameraWithWrongData() {
        Camera rsl = new CameraFactory().getInstance(Map.of("id", "1", "urlType", "url",
                "videoUrl", "videoUrl",
                "value", "zasada",
                "ttl", "asd"));
        Assert.assertNull(rsl);
    }

    @Test
    public void createCameraWithPoorData() {
        Camera rsl = new CameraFactory().getInstance(Map.of("id", "1", "urlType", "url",
                "videoUrl", "videoUrl",
                "ttl", "100"));
        Assert.assertNull(rsl);
    }

}