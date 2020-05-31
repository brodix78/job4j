package cache;

import com.antkorwin.commonutils.gc.GcUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FileCacheTest {

    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @Before
    public void addFiles() throws IOException {
        File content = folder.newFile("one.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(content))) {
            writer.write("My cache test");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readTest() {
        FileCache fileCache = new FileCache(folder.getRoot().getAbsolutePath());
        assertThat(fileCache.get("one.txt"), is ("My cache test"));
    }

    @Test
    public void memoryCleanTest() {
        FileCache fileCache = new FileCache(folder.getRoot().getAbsolutePath());
        String tmp = fileCache.get("one.txt");
        tmp =null;
        Assert.assertNotNull(fileCache.getMapForTest("one.txt").get());
        GcUtils.tryToAllocateAllAvailableMemory();
        Assert.assertNull(fileCache.getMapForTest("one.txt").get());
    }
}