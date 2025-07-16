package flightreservation;

import java.lang.reflect.Array;
import java.util.*;

public class VihslBhai {
    public static void main(String[] args)
    {
        String str="abcdef";
        String[] arr= {"a","bb","acd","ace","adf","bfd"};

        Map<Character, Integer> map = new HashMap<>();
        int finalVal = 0;
        for(int i=0 ; i<str.length(); i++) {
            map.put(str.charAt(i), i);
        }

        for(int j=0 ; j< arr.length ; j++) {
            String str1 = arr[j];
            boolean bool = true;
            if(bool) {
                finalVal++;
            }
            int val1 = 0;
            int val2 = 0;
            for(int k=0 ; k<str1.length() ; k++) {
                Character ch = str1.charAt(k);
                if(map.containsKey(ch)) {
                    val1 = val2;
                    val2 = map.get(ch);

                    if(val1 != 0 && val2 != 0) {
                        if (val2 < val1) {
                          bool = false;
                        }
                    }
                }
            }
        }
        System.out.println(finalVal-1);

    }
}
