package dev.Fade;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RandomHelper<T> {
    public static Integer[] getNonRepeatingSequence(int length) {
        Integer[] values = new Integer[length];
        for(int i =0; i < length; i++) {
            values[i] = i;
        }
        Collections.shuffle(Arrays.asList(values));
        return values;
    }
   
    public List<T> getSelection(int length, List<T> data ) {
        var indices = getNonRepeatingSequence(length);
        List<T> result = new LinkedList<T>();
        for(int i = 0; i < length; i++) {
            result.add(data.get((indices[i])));
        }
        return result;
    }
}
