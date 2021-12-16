package Mytest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SolutiongoodDaysToRobBank {
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        int length = security.length;
        List<Integer> list = new ArrayList<>();
        if(time==0){
            for(int i=0;i<length;i++){
                list.add(i);
            }
            return list;
        }

        Set<Integer> ascset = new HashSet<Integer>();
        Set<Integer> descset = new HashSet<Integer>();
        Set<Integer> resset = new HashSet<Integer>();

        int count = 0;

        for(int i=0;i<length-1;i++){
            if(security[i]<=security[i+1]){
                count++;
                if(count>=time){
                    ascset.add(i-time+1);
                }
            }else if(security[i]>security[i+1]){
                count=0;
            }
        }

        int desccount = 0;
        for(int i=0;i<length-1;i++){
            if(security[i]>=security[i+1]){
                desccount++;
                if(desccount>=time){
                    descset.add(i+1);
                }
            }else if(security[i]<security[i+1]){
                desccount=0;
            }
        }

        resset.addAll(descset);
        resset.retainAll(ascset);
        for(Integer i:resset){
            list.add(i);
        }

        return list;
    }
}
