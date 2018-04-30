package ppjoinSpatial;

import java.util.*;

public class PPJoin {

    static List<String> temporaryPairs =  new ArrayList<>();


    public static void ppJoin(ArrayList<Entry> list1, ArrayList<Entry> list2, double simThreshold) {

        List<String> setOfPairs = new ArrayList<>();
        HashMap<String, List<InvIndex>> invIndexMap = new HashMap<>();

        //This is A from report
        HashMap<Entry, Integer> overlapMap = new HashMap<>();


        String token;
        int i;
        double prefixLength;


        for (Entry entryFromList2 : list2) {

            prefixLength = Math.round(entryFromList2.getNgramsAndFreq().size() - (simThreshold * entryFromList2.getNgramsAndFreq().size()) + 1);

            for (i = 0; i < prefixLength; i++) {

                token = entryFromList2.getNgram(i);

                InvIndex orderedValueAndPos = new InvIndex(entryFromList2, i);

                if (!invIndexMap.containsKey(token)) {
                    invIndexMap.put(token, new ArrayList<>());
                }


                if (!invIndexMap.get(token).contains(orderedValueAndPos)) {
                    invIndexMap.get(token).add(orderedValueAndPos);
                }

            }

        }

        System.out.println("\n------------------------Inverted Index (of list2)------------------------\n");

        System.out.println(invIndexMap);



        for (Entry entryFromList1 : list1) {

            prefixLength = Math.round(entryFromList1.getNgramsAndFreq().size() - (simThreshold * entryFromList1.getNgramsAndFreq().size()) + 1);

//            prefixLength = Math.max(1,prefixLength); //prefix should be at least 1

            System.out.println("\n" +"\n" + "EntryFromList1: " + entryFromList1);
            System.out.println("Prefix length of EntryFromList1: " + (int) prefixLength);

            overlapMap.clear();
            StringBuilder orderedEntryFromList1 = new StringBuilder();

            for (i = 0; i<prefixLength; i++) {
                System.out.println("Print i: " + i);


                token = entryFromList1.getNgram(i);
                System.out.println("Token (i): " + token);

                //Constructing Ordered by Global Frequency Entry Prefix
                orderedEntryFromList1.append(" ").append(token);


                int iFinal = i;
                String finalToken = token;
                invIndexMap.forEach((String ngramII, List<InvIndex> listOfEntryAndPos) -> {

                    //  if (invIndexMap.containsKey(entryFromList2.getNgram(j))) { NEED TO ADD SOMEHOW

                    if (ngramII.equals(finalToken)) {

                        for (int k = 0; k < listOfEntryAndPos.size(); k++) {

                            Entry entryFromList2 = listOfEntryAndPos.get(k).getIIEntry();
                            int j = listOfEntryAndPos.get(k).getIIPos();

                            System.out.println("ngramII : " + ngramII);
                            System.out.println("Entry From list2 : " + entryFromList2);
                            System.out.println("Entry From list2 (Ordered) : " + entryFromList2.getOrderedValue() + "\n Pos: " + j);
                            System.out.println("\niFinal : " + iFinal);

//                             SIZE FILTERING?????????
//                            if (entryFromList2.getNgramsAndFreq().size() >= simThreshold * entryFromList1.getNgramsAndFreq().size()) {
                            if (true) {

                                //Calculating a and ubound as they are named in report
                                double a = (simThreshold / (1 + simThreshold)) * (entryFromList1.getNgramsAndFreq().size() + entryFromList2.getNgramsAndFreq().size());

                                System.out.println("first a: " + a);

                                double ubound = 1 + Math.min(entryFromList1.getNgramsAndFreq().size() - iFinal, entryFromList2.getNgramsAndFreq().size() - j);

                                int currentEntryOverlap = overlapMap.getOrDefault(entryFromList2, 0);

                                System.out.println("??? CURRENT OVERLAP + UBOUND >= a   " + currentEntryOverlap + " + " + ubound + " > " + a);


                                if (currentEntryOverlap + ubound >= a) {

                                    currentEntryOverlap++;

                                    if (overlapMap.containsKey(entryFromList2)) {
                                        overlapMap.replace(entryFromList2, currentEntryOverlap);
                                    } else {
                                        overlapMap.put(entryFromList2, currentEntryOverlap);
                                    }

                                } else {

                                    if (overlapMap.containsKey(entryFromList2)) {
                                        overlapMap.replace(entryFromList2, 0);
                                    } else {
                                        overlapMap.put(entryFromList2, 0);
                                    }

                                    System.out.println("---------^^^^---------PRUNE   " + entryFromList2 + "\n");

                                }

                            }

                        }
                    }

                    });
            }


            System.out.println("\n------------------------DATA TO Verify------------------------\n");

            System.out.print("x = " + entryFromList1 + "ORDERED prefix x = " + orderedEntryFromList1 +  "  ||||\n   A = " + overlapMap
                    + "  ||||   simThreshold = " + simThreshold);

            System.out.println("\n x size: " + entryFromList1.getNgramsAndFreq().size() );


            ppJoinVerify(entryFromList1, overlapMap, simThreshold);


        }




    }

    public static void ppJoinVerify (Entry entryFromList1, HashMap<Entry,Integer> overlapMap, double simThreshold) {


        System.out.println("\n------------------------VERIFY------------------------\n");


        double prefixLengthX = Math.round(entryFromList1.getNgramsAndFreq().size() - (simThreshold * entryFromList1.getNgramsAndFreq().size()) + 1);


        overlapMap.forEach((entryFromOverlapMap, overlap) -> {

            int intersection;
            double ubound;

            List<String> tokenSet1 =  new ArrayList<>();
            List<String> tokenSet2 =  new ArrayList<>();

            System.out.println("entryFromList1.getNgramsAndFreq().size(): " + entryFromList1.getNgramsAndFreq().size());

            System.out.println("entryFromOverlapMap.getNgramsAndFreq().size(): " + entryFromOverlapMap.getNgramsAndFreq().size());


            double a = (simThreshold / (1 + simThreshold)) * (entryFromList1.getNgramsAndFreq().size() + entryFromOverlapMap.getNgramsAndFreq().size());

            System.out.println("real a: " + a);


            if (overlap > 0) {

                double prefixLengthY = Math.round(entryFromOverlapMap.getNgramsAndFreq().size() - (simThreshold * entryFromOverlapMap.getNgramsAndFreq().size()) + 1);

                System.out.println( "\nprefix length y: " + prefixLengthY + " || total length y: " + entryFromOverlapMap.getNgramsAndFreq().size() +"\n" );

                // wx and wy are last token of prefix as named in Verify
                String wx = entryFromList1.getNgram( (int) prefixLengthX - 1);
                int wxFreq = entryFromList1.getFrequency( (int) prefixLengthX - 1);


                System.out.println(entryFromList1);

                System.out.println(entryFromOverlapMap);


                String wy = entryFromOverlapMap.getNgram( (int) prefixLengthY - 1);
                int wyFreq = entryFromOverlapMap.getFrequency( (int) prefixLengthY - 1);


                System.out.println( "\n\n last token of prefix x: " + wx + " || prefix length x : " + (int) prefixLengthX + " || total length x: "
                        + entryFromList1.getNgramsAndFreq().size() + " || last token of prefix y: " + wy);

                // valueO ===> O as named in Verify
                int valueO = overlap;

                System.out.println("\nwxFreq: " + wxFreq +" wyFreq: " + wyFreq);



                if (wxFreq < wyFreq) {

                    System.out.println("wxFreq < wyFreq");

                    ubound = valueO + entryFromList1.getNgramsAndFreq().size() - prefixLengthX;

                    System.out.println("\nubound: " + (int) ubound + "\na:" + a + "\nvalueO: " +valueO);


                    if (ubound >= a) {

                        for (int i = (int) prefixLengthX; i < entryFromList1.getNgramsAndFreq().size(); i++) {

                            System.out.println(entryFromList1.getNgram(i));

                            tokenSet1.add(entryFromList1.getNgram(i));
                        }

                        for (int i = overlap; i < entryFromOverlapMap.getNgramsAndFreq().size(); i++) {

                            tokenSet2.add(entryFromOverlapMap.getNgram(i));
                        }

                        System.out.println("tokenSet1: " + tokenSet1);
                        System.out.println("tokenSet2: " + tokenSet2);

                        tokenSet1.retainAll(tokenSet2);

                        intersection = tokenSet1.size();

                        valueO = valueO + intersection;

                    }

                }
                else {

                    ubound = valueO + entryFromOverlapMap.getNgramsAndFreq().size() - prefixLengthY;

                    System.out.println("wxFreq >= wyFreq");
                    System.out.println("\nubound: " + (int) ubound + "\na: " + a + "\nvalueO: " +valueO);


                    if (ubound >= a) {

                        for (int i = overlap; i < entryFromList1.getNgramsAndFreq().size(); i++) {

                            tokenSet1.add(entryFromList1.getNgram(i));
                        }

                        for (int i = (int) prefixLengthY; i < entryFromOverlapMap.getNgramsAndFreq().size(); i++) {

                            tokenSet2.add(entryFromOverlapMap.getNgram(i));
                        }

                        System.out.println("tokenSet1: " + tokenSet1.toString());
                        System.out.println("tokenSet2: " + tokenSet2.toString());


                        tokenSet1.retainAll(tokenSet2);

                        System.out.println("tokenSet1 after retainALL: " + tokenSet1.toString());

                        intersection = tokenSet1.size();

                        System.out.println("intersection: " + intersection);


                        valueO = valueO + intersection;

                    }

                }


                System.out.println("\nFinal valueO: " + valueO);


                if (valueO >= a) {

                    temporaryPairs.add(entryFromList1.toString() + "    ++++PAIR++++ \n" + entryFromOverlapMap.toString() + "\n\n");

                    System.out.println("\n\n----------------------PAIRS----------------------");

                    System.out.println(temporaryPairs);

                }

                System.out.println("\nHELLO FROM THE END OF VERIFY\n");


            }

        });

        System.out.println("\n\n----------------------------------------------------------------------------------------------------------------");
        System.out.println("\n\n----------------------PAIRS----------------------\n\n");



        System.out.println(temporaryPairs);




    }




}
