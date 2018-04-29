package ppjoinSpatial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Entry {

    private String value;
    private double x;
    private double y;
    private List<String> arrayListValue =  new ArrayList<>();
    private List<NgramsAndFrequency> ngramAndFreq = new ArrayList<>();
    public static HashMap<String,Integer> hashMapGlobalFreq = new HashMap<>(); //STATIC!!! STRING = NGRAM , INTEGER = GLOBAL FREQUENCY

    //constructor of entries
    public Entry(String textValue, double xValue, double yValue) {

        this.value = textValue;
        this.x = xValue;
        this.y = yValue;

    }

    //constructor of entries with ArrayList Value
    public Entry(ArrayList<String> ngrams, double xValue, double yValue) {

        if (ngrams != null) {

            this.value = ngrams.toString();
            this.arrayListValue = (List<String>) ngrams.clone();
            this.x = xValue;
            this.y = yValue;
        }
        else {
            System.out.println("Error: Empty List Has Been Entered");
        }

    }

    public String getValue() {
        return this.value;
    }

    public List<String> getArrVal() {
        return this.arrayListValue;
    }

    public List<NgramsAndFrequency> getNgramsAndFreq() {
        return this.ngramAndFreq;
    }

    public String getNgram(int i) {
            return ngramAndFreq.get(i).getNgramVal();
    }

    public String getOrderedValue() {
        String orderedEntry = "";
        for (int i = 0; i<ngramAndFreq.size(); i++){
            orderedEntry = (orderedEntry + " " + ngramAndFreq.get(i).getNgramVal());
        }
        return orderedEntry;
    }

    public int getFrequency(int i) {
        return ngramAndFreq.get(i).getFrequencyVal();
    }

    public int hashMapGlobalFreqVal(String str) {
        return hashMapGlobalFreq.get(str);
    }

    @Override
    public String toString() {
        return "Entry{" + "Value=" + value + ", x=" + x + ", y=" + y + "}" +"\n";
    }


    public void createHashMap() {

        int counterOfGlobalFreq;
        String tempNgramToAdd;

        for (int i = 0; i < this.arrayListValue.size(); i++) {

            tempNgramToAdd = this.arrayListValue.get(i);

            if (hashMapGlobalFreq.containsKey(tempNgramToAdd)) {

                counterOfGlobalFreq = hashMapGlobalFreq.get(tempNgramToAdd);
                hashMapGlobalFreq.put(tempNgramToAdd, counterOfGlobalFreq + 1);

            }
            else {
                hashMapGlobalFreq.put(tempNgramToAdd, 1);
            }

        }

//        System.out.println("TESTING HASH MAP");
//        System.out.println(hashMapGlobalFreq);

    }

    public void frequencySet() {

        List<NgramsAndFrequency> ngramFreq = new ArrayList<>();
        String tempNgramToAdd;

        for (int i = 0; i < this.arrayListValue.size(); i++) {

            tempNgramToAdd = this.arrayListValue.get(i);

            //Next line is important! makes tempNgramToAdd temporary final to use in lambda function , REMOVE DUPLICATE ?????????????

            //??????????????????????????????
//
//            String finalTempNgramToAdd = tempNgramToAdd;
//            ngramFreq.removeIf(item -> item.getNgramVal().equals(finalTempNgramToAdd));

            ngramFreq.add(new NgramsAndFrequency(tempNgramToAdd, hashMapGlobalFreq.get(tempNgramToAdd)));

        }

        ngramFreq = NgramsAndFrequency.NgramsAndFrequencySort(ngramFreq);
        this.ngramAndFreq = ngramFreq;
    }


}