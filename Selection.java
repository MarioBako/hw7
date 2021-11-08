import java.util.*;

public class Selection<E extends Comparable<E>> {
    ArrayList<E> input;

    public Selection(ArrayList<E> list){
        input = list;
    }

    public Object oneB(int k) {

        ArrayList<E> objects = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            objects.add(input.get(i));
        }

        Collections.sort(objects, Collections.reverseOrder());

        for (int i = k; i < input.size(); i++) {
            E current = input.get(i);

            if(current.compareTo(objects.get(k - 1)) > 0){
                objects.set(k - 1, current);

                boolean sorted = false;
                int index = k - 1;

                while(!sorted && index > 0){
                    if(objects.get(index).compareTo(objects.get(index - 1)) > 0){
                        Collections.swap(objects,index, index - 1);
                        index--;
                    } else {
                        sorted = true;
                    }
                }
            }
        }
        return objects.get(k - 1);
    }

    public Object sixA(int k) {
        PriorityQueue<E> q = new PriorityQueue<>(10, Collections.reverseOrder());
        for(E i : input){
            q.offer(i);
        }
        for (int i = 1; i < k; i++) {
            q.poll();
        }
        return q.peek();
    }

    public Object sixB(int k) {
        PriorityQueue<E> q = new PriorityQueue<>();

        for (int i = 0; i < k; i++) {
            q.offer(input.get(i));
        }

        for (int i = k; i < input.size(); i++) {
            if(input.get(i).compareTo(q.peek()) > 0){
                q.poll();
                q.offer(input.get(i));
            }
        }
        return q.peek();
    }

    public static void main(String[] args){
        ArrayList<Integer> testerList = new ArrayList<>();
        int n = (int)Math.pow(10, 7);
        int k = (int)Math.pow(10, 6);
        Random r = new Random();

        for (int i = 0; i < n; i++) {
            testerList.add(r.nextInt());
        }

        Selection oneB = new Selection(testerList);
        Selection sixA = new Selection(testerList);
        Selection sixB = new Selection(testerList);

        double start1b = System.currentTimeMillis();
        Object first = oneB.oneB(k);
        double end1b = System.currentTimeMillis();

        System.out.println(first);

        double start6a = System.currentTimeMillis();
        Object second = sixA.sixA(k);
        double end6a = System.currentTimeMillis();

        System.out.println(second);

        double start6b = System.currentTimeMillis();
        Object third = sixB.sixB(k);
        double end6b = System.currentTimeMillis();

        System.out.println(third);

        System.out.println("Method 1B: " + (end1b - start1b));
        System.out.println("Method 6A: " + (end6a - start6a));
        System.out.println("Method 6B: " + (end6b - start6b));
    }
}
