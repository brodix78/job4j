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

public class CacheTwoTest {

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
        CacheTwo<String, String> fileCache = new CacheTwo<>(new TxtReader(folder.getRoot().getAbsolutePath()));
        assertThat(fileCache.get("one.txt"), is ("My cache test"));
        Assert.assertNull(fileCache.get("two.txt"));
    }

    @Test
    public void readTestWhenNoObject() {
        CacheTwo<String, String> fileCache = new CacheTwo<>(new TxtReader(folder.getRoot().getAbsolutePath()));
        Assert.assertNull(fileCache.get("two.txt"));
    }
}
