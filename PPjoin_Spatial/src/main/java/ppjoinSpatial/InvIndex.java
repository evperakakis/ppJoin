package ppjoinSpatial;

public class InvIndex {
    private Entry invIndexEntry;
    private int invIndexPosition;

    public InvIndex(Entry entryToAdd, int posToAdd ) {

        this.invIndexEntry = entryToAdd;
        this.invIndexPosition = posToAdd;

    }

    public Entry getIIEntry() {
        return this.invIndexEntry;
    }

    public int getIIPos() {
        return this.invIndexPosition;
    }


    @Override
    public String toString() {
        return  "{ " + invIndexEntry + ", Position= " + invIndexPosition + "}" + "\n\n";
    }



    // overide equals so i can use invIndexMap.get(token).contains(orderedValueAndPos) in PPjoin
    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final InvIndex other = (InvIndex) obj;
        if (invIndexEntry == null) {
            if (other.invIndexEntry != null)
                return false;
        } else if (!invIndexEntry.equals(other.invIndexEntry))
            return false;
        return true;
    }
}