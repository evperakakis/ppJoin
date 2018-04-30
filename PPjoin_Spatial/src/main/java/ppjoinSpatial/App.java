package ppjoinSpatial;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class App {
    public String getGreeting() {
        return "START\n------------------";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());

        ArrayList<Entry> entryList1 = new ArrayList<>();
        ArrayList<Entry> entryList2 = new ArrayList<>();

        //Reading Data From File (value as Ngrams)
        entryList1 = readFromDataFile.readToNgrams(entryList1, "src/main/java/ppjoinSpatial/resources/data1.txt", 3);
        entryList2 = readFromDataFile.readToNgrams(entryList2, "src/main/java/ppjoinSpatial/resources/data2.txt", 3);

        //Sorting Lists Based on size (acceding order) & orderByFrequency is Called forEach Entry
        entryList1 = SortClass.sortForPPjoin(entryList1);
        entryList2 = SortClass.sortForPPjoin(entryList2);

        entryList1 = SortClass.sortForPPjoinFrequencySet(entryList1);
        entryList2 = SortClass.sortForPPjoinFrequencySet(entryList2);



        System.out.println("List 1:");
        System.out.println(entryList1);
        System.out.println("------------------");
        System.out.println("List 2:");
        System.out.println(entryList2);
        //at this point hashmap for global ordering & order by text size have been set


        System.out.println("------------------");
        System.out.println("Hash Map:");
        System.out.println(Entry.hashMapGlobalFreq);


        System.out.println("------------------");
        System.out.println("Tests on entryList2 :");

        //System.out.println(entryList2.get(6).getOccurrence() + " appears---> " + entryList2.get(6).getArrVal());

//        System.out.println(entryList1.get(4).getNgramsAndFreq());
//
//        System.out.println(entryList2.get(4).getNgramsAndFreq());
//
//        System.out.println(entryList2.get(5).getNgramsAndFreq());
//
//        System.out.println(entryList2.get(6).getNgramsAndFreq());
//        System.out.println(entryList2.get(6).getNgram(13));
//        System.out.println(entryList2.get(6).getFrequency(13));


        System.out.println("--------LONDONS----------");

        System.out.println(entryList1.get(1).getNgramsAndFreq());
        System.out.println(entryList2.get(1).getNgramsAndFreq());

        System.out.println(entryList1.get(1).hashMapGlobalFreqVal("OND"));
        System.out.println(entryList2.get(1).hashMapGlobalFreqVal("OND"));
        System.out.println(Entry.hashMapGlobalFreq.get("LON"));



        System.out.println("------------------");
        System.out.println("------------------");
        System.out.println("------------------");
        System.out.println("PPjoin Starting :");


        double simThreshold = 0.53;
        PPJoin.ppJoin(entryList1, entryList2, simThreshold);



        System.out.println("\n------------------");
        System.out.println("STOP");

    }

//    public static List<String> union(List<String> list1, List<String> list2) {
//        HashSet<String> set = new HashSet<String>();
//
//        set.addAll(list1);
//        set.addAll(list2);
//
//        return new ArrayList<String>(set);
//    }
}
