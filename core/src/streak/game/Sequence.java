package streak.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Sequence {

    private List<Integer> sequence;
    private int head;
    private Random random;
    private int numberOfButtons;

    public Sequence(int[] sequenceAsArray) {
        sequence = new ArrayList<Integer>();
        for (int num : sequenceAsArray) {
            sequence.add(num);
        }
        random = new Random();
        numberOfButtons = 4;
    }

    public Sequence() {
        sequence = new ArrayList<Integer>();
        random = new Random();
        numberOfButtons = 4;
        head = -1;
    }

    public int next() {
        head += 1;
        if (!(head < getLength())) {
            head = getLength() - 1;
        }
        return sequence.get(head);
    }

    public void reset() {
        head = -1;
    }

    public void resetAndAdd() {
        head = -1;
        int randomNum = random.nextInt((numberOfButtons - 1) + 1) + 1;
        sequence.add(randomNum);
    }

    public int getLength() {
        return sequence.toArray().length;
    }

    public int get(int ndx) {
        return sequence.get(ndx);
    }

    public boolean atEnd() {
        return head == getLength() - 1;
    }

    public String toString() {
        return Arrays.toString(sequence.toArray());
    }
}
