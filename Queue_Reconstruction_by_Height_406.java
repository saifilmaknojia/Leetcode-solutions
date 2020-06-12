import java.util.*;

class Queue_Reconstruction_by_Height_406 {
    public int[][] reconstructQueue(int[][] people) {
        // sort array by
        // height descendingly
        // if two heights are same, sort ascendingly by number of people in front

        int len = people.length;

        Arrays.sort(people, (x, y) -> {
            if (x[0] != y[0])
                return y[0] - x[0];
            else
                return x[1] - y[1];
        });

        List<int[]> temp_move = new ArrayList<>();

        for (int[] ppl : people)
            temp_move.add(ppl[1], ppl);

        return temp_move.toArray(new int[len][]);
    }
}