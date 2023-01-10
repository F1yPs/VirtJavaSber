package week1;

import java.util.Comparator;

public class sortDistrict implements Comparator<Main> {
    @Override
    public int compare(Main o1, Main o2) {
        return o1.district.charAt(0) - o2.district.charAt(0);
    }
}
