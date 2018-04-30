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


        double simThreshold = 0.50;
        PPJoin.ppJoin(entryList1, entryList2, simThreshold);
//
//        Entry entryXDEL = entryList1.get(2);
//        System.out.println(entryXDEL.getOrderedValue());
//
//        Entry entryYDEL = entryList2.get(2);
//        System.out.println(entryYDEL.getOrderedValue());

//
//
//        System.out.println("\n\n----------------------------------------------------------------------------------------------------------------");
//        System.out.println("\n\n----------------------BRUTE FORCE----------------------\n\n");
//
//        List<String> tokenSet1 =  new ArrayList<>();
//        List<String> tokenSet2 =  new ArrayList<>();
//        List<String> tokenSetUnion =  new ArrayList<>();
//        List<String> tokenSetIntersection =  new ArrayList<>();
//
//        System.out.println("List 1:");
//        System.out.println(entryList1);
//        System.out.println("------------------");
//        System.out.println("List 2:");
//        System.out.println(entryList2);
//
//        Entry entryFromList1 = entryList1.get(2);
//        Entry entryFromList2 = entryList2.get(3);
//
//        for (int i=0; i<entryList1.get(2).getNgramsAndFreq().size(); i++) {
//            tokenSet1.add(entryFromList1.getNgram(i));
//            tokenSetIntersection.add(entryFromList1.getNgram(i));
//        }
//
//        for (int i=0; i<entryList2.get(3).getNgramsAndFreq().size(); i++) {
//            tokenSet2.add(entryFromList2.getNgram(i));
//        }
//        System.out.println("------------------");
//        System.out.println(tokenSet1);
//        System.out.println("------------------");
//        System.out.println(tokenSet2);
//
//        tokenSetIntersection.retainAll(tokenSet2);
//
//        System.out.println("---------tokenSetIntersection---------");
//        System.out.println(tokenSetIntersection);
//
//        tokenSetUnion.addAll(tokenSet1);
//        tokenSetUnion.addAll(tokenSet2);
//
//
//        tokenSetUnion = union(tokenSet1,tokenSet2);
//
//        System.out.println("---------tokenSetUnion---------");
//        System.out.println(tokenSetUnion);
//
//        double interSize = tokenSetIntersection.size();
//        System.out.println("\ninterSize: " + interSize);
//
//        double unionSize = tokenSet1.size() + tokenSet2.size() - interSize;
//        System.out.println("\nunionSize: " + unionSize);
//
//
//        double jacc = interSize/unionSize;
//        System.out.println("jacc: " + jacc);





        System.out.println("\n------------------");
        System.out.println("STOP");

    }

    public static List<String> union(List<String> list1, List<String> list2) {
        HashSet<String> set = new HashSet<String>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<String>(set);
    }
}
