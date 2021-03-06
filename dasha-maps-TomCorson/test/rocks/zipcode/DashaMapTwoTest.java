package rocks.zipcode;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;

public class DashaMapTwoTest {
    @Test
    public void set() {
        DashaMapTwo dm = new DashaMapTwo();

        dm.set("aaa", "5");
        Integer expected = 1;
        Integer actual = dm.bucketSize("a");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void set2() {
        DashaMapTwo dm = new DashaMapTwo();

        Integer expected = 0;
        Integer actual = dm.bucketSize("a");
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void set3() {
        DashaMapTwo dm = new DashaMapTwo();

        dm.set("aaa", "5");
        dm.set("aa", "2");
        Integer expected = 2;
        Integer actual = dm.bucketSize("a");
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void get() {
        DashaMapTwo dm = new DashaMapTwo();

        dm.set("aaa", "5");
        String expected = "5";
        String actual = dm.get("aaa");
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void get2() {
        DashaMapTwo dm = new DashaMapTwo();

        dm.set("aaa", "5");
        dm.set("aba", "3");
        dm.set("aac", "1");
        String expected = "5";
        String actual = dm.get("aaa");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void isEmpty() {
        DashaMapTwo dm = new DashaMapTwo();

        Boolean expected = false;

        dm.set("aaa", "5");

        Boolean actual = dm.isEmpty();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void isEmpty2() {
        DashaMapTwo dm = new DashaMapTwo();

        Boolean expected = true;
        Boolean actual = dm.isEmpty();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void size() {
        DashaMapTwo dm = new DashaMapTwo();

        dm.set("aaa", "5");
        dm.set("ab", "5");
        Long expected = 2L;
        Long actual = dm.size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void bucketSize() {
        DashaMapTwo dm = new DashaMapTwo();

        dm.set("aaa", "5");
        dm.set("ab", "5");
        Integer expected = 1;
        Integer actual = dm.bucketSize("b");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testReadList() {
        DashaMapTwo dm = new DashaMapTwo();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "word-list.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] words = line.split(" ",2);
                dm.set(words[0], words[1]);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Long actual = dm.size();
        Long expected = 124L;
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testDelete(){
        DashaMapOne dm = new DashaMapOne();
        dm.set("tom","2");
        dm.set("tomato", "1");
        dm.set("too", "1");
        dm.delete("tomato");
        Integer expected = 2;
        Integer actual = dm.bucketSize("t");
        assertEquals(expected,actual);
    }
    @Test
    public void testDelete2(){
        DashaMapOne dm = new DashaMapOne();
        dm.set("tom","2");
        dm.set("tomato", "1");
        dm.set("too", "1");
        dm.delete("tomato");
        String expected = null;
        String actual = dm.get("tomato");
        assertEquals(expected,actual);
    }
    @Test
    public void testDelete3(){
        DashaMapOne dm = new DashaMapOne();
        dm.set("tom","2");
        dm.set("tomato", "1");
        dm.set("too", "1");
        dm.delete("tom");
        String expected = null;
        String actual = dm.get("tom");
        assertEquals(expected,actual);
    }


}