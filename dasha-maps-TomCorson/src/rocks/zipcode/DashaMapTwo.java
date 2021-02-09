package rocks.zipcode;

public class DashaMapTwo implements HashMapX{


    class Node {
        String key;
        String value;
        DashaMapTwo.Node next;

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private DashaMapTwo.Node[] hasharray;
    //does the keys sets values to -1
    public DashaMapTwo() {
        this.hasharray = new DashaMapTwo.Node[26];
        int i = 0;
        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
            hasharray[i] = new DashaMapTwo.Node(String.valueOf(alphabet), "-1");
            i++;
        }
    }
    //gives you first letter?
    private String HashFunctionTwo(String input) {
        if (input.length() > 1) {
            return (String.valueOf(input.charAt(1)).toLowerCase());
        }
        if(input.length() > 0){
            return (String.valueOf(input.charAt(0)).toLowerCase());
        }
        return null;
    }
    //finds index of kh
    private Integer findHead(String kh) {
        int i = 0;
        for (DashaMapTwo.Node n : hasharray) {
            if (n.key.equals(kh)) {
                return i;
            }
            i++;
        }
        return -1;
    }
    //
    private void appendTo(String kh, DashaMapTwo.Node n) {
        Integer bucket = findHead(kh);
        if (bucket != -1) {
            n.next =  hasharray[bucket].next;
            hasharray[bucket].next = n;
        }
    }

    private DashaMapTwo.Node findIn(String keyword) {
        String keyhash =  HashFunctionTwo(keyword);
        Integer bucket = findHead(keyhash);
        DashaMapTwo.Node n = hasharray[bucket].next;
        while (n != null && !n.key.equals(keyword)) {
            n = n.next;
        }
        return n;
    }

    @Override
    public void set(String key, String value) {
        String keyhash =  HashFunctionTwo(key);
        DashaMapTwo.Node newval = new DashaMapTwo.Node(key, value);
        appendTo(keyhash, newval);
    }

    @Override
    public String delete(String key) {
        String firstLetter = HashFunctionTwo(key);
        Integer arrayIndexOfKey = findHead(firstLetter);

        if(bucketSize(key) > 0){
            return "Item does not exist";
        }
        else {
            DashaMapTwo.Node temp = hasharray[arrayIndexOfKey];
            DashaMapTwo.Node previous = null;
            while (temp != null && !temp.key.equals(key)) {
                previous = temp;
                temp = temp.next;
            }
            if(temp == null){return "not found";}
            previous.next = temp.next;

        }
        return "deleted";
    }

    @Override
    public String get(String key) {
        String keyhash =  HashFunctionTwo(key);
        DashaMapTwo.Node newnode = findIn(key);
        if (newnode != null) {
            return newnode.value;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < hasharray.length; i++) {
            if (hasharray[i].next != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public long size() {
        long s = 0;
        for (DashaMapTwo.Node n : hasharray) {
            Integer l = bucketSize(n.key);
            s += l;
        }
        return s;
    }

    @Override
    public Integer bucketSize(String key) {
        Integer foundhead = findHead(key);
        if (foundhead != -1) {
            DashaMapTwo.Node p = hasharray[foundhead].next;
            int n = 0;
            while (p != null) {
                p = p.next;
                n++;
            }
            return n;
        }

        return 0;
    }
}



