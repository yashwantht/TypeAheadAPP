package com.Typeahead;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yashw on 11/8/2018.
 */
public class IndexFinder {
    private String delimiter;
    private List<Integer> indexedStrings;
    public  IndexFinder(String delimiter, List<Integer> indexedStrings){
        this.delimiter = delimiter;
        this.indexedStrings = indexedStrings;
    }
    public IndexFinder(){
        this.delimiter="\t";
        this.indexedStrings = new ArrayList<Integer>();
        this.indexedStrings.add(3);
    }
    public List<String> findIndex(String input){
        String[] inputArray= input.split(delimiter);
        List<String> result = new ArrayList<String>();
        for(int i:indexedStrings){
            if(inputArray.length >=i && i>0){
                result.addAll(Arrays.asList(inputArray[i-1].split(" ")));
            }
        }
        return result;
    }

}
