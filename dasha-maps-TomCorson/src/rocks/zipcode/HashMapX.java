package rocks.zipcode;

public interface HashMapX {

// fundamentals
public void set(String key, String value);
public String delete(String key);
public String get(String key);
public boolean isEmpty();
public long size();

// testing access
public Integer bucketSize(String key); // used for tests
}