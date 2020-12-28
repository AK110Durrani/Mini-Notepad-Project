import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class LinkedList {

    MyNode head;
    MyNode tail;

    public LinkedList() {
        String filename = "dict.txt";
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filename));
            String line = bufferedReader.readLine();
            while (line != null) {
                AddAtFirst(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void CreateLLFromData(String data[]) {
        for (String d : data) {
            AddAtFirst(d);
        }
    }

    void AddAtFirst(String data) {

        MyNode node = new MyNode(data);
        if (head == null) {
            head = tail = node;

        } else if (head == tail) {
            node.next = head;
            head = node;
            head.next = tail;
        } else {
            node.next = head;

            head = node;
        }
    }


    void addAtLast(String data) {
        if (head == null) {
            head = tail = new MyNode(data);
            return;
        }
        tail.next = new MyNode(data);
        tail = tail.next;
    }

    class MyNode {
        MyNode(String n) {
            data = n;
        }

        String data;
        MyNode next;
    }

    public Vector<String> PredictWord(String word) {
        MyNode tmp = head;
        Vector<String> vector = new Vector<>();
        int i = 0;
        while (tmp != null && i < 5) {
            if (tmp.data.startsWith(word)) {
                vector.add(tmp.data);
                i++;
            }
            tmp = tmp.next;
        }

        return vector;
    }

/*
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();

        Vector<String> list = linkedList.PredictWord("d");
        System.out.println(list);
    }*/
}
