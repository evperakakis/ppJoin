package ppjoinSpatial;

import java.util.ArrayList;

public class SortClass {

    public static ArrayList<Entry> sortForPPjoin(ArrayList<Entry> list) { //sorts a List of Entries based on their value (text) size

        list.sort((e1, e2) -> {
            int size1 = e1.getArrVal().size();
            int size2 = e2.getArrVal().size();

            return (size1 - size2);

        });

        // creating hash map
        for (Entry e : list) {
            e.createHashMap();
        }


        return list;
    }

    public static ArrayList<Entry> sortForPPjoinFrequencySet(ArrayList<Entry> list) { //sorts a List of Entries based on their value (text) size

        // assign values of frequency to each entry
        for (Entry e : list) {
            e.frequencySet();
        }

        return list;

    }

}

