package ppjoinSpatial;

import java.util.List;

public class NgramsAndFrequency {

    private String myNgram;
    private int myFrequency;


    public NgramsAndFrequency(String textValue, int frequency) { //constructor of Ngrams

        this.myNgram = textValue;
        this.myFrequency = frequency;

    }

    public String getNgramVal() {
        return this.myNgram;
    }

    public int getFrequencyVal() {
        return this.myFrequency;
    }


    @Override
    public String toString() {
        return "\n{ Ngram = " + myNgram + ", Frequency = " + myFrequency + " }";
    }


    public static List<NgramsAndFrequency> NgramsAndFrequencySort(List<NgramsAndFrequency> list) {

        list.sort((e1, e2) -> {

            String ngram1 = e1.getNgramVal();
            String ngram2 = e2.getNgramVal();

            int compareFreq1 = Entry.hashMapGlobalFreq.get(ngram1);
            int compareFreq2 = Entry.hashMapGlobalFreq.get(ngram2);

            return (compareFreq2 - compareFreq1);

        });

        return list;
    }


}
