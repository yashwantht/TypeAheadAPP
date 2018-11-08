package com.Typeahead;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

/**
 * Created by yashw on 11/8/2018.
 */
public class TypeAhead  {
    ConcurrentSkipListSet<String> typeAheadResult;
    ConcurrentHashMap<Character, TypeAhead> typeAheadNode;
    public TypeAhead(){
        this.typeAheadResult = new ConcurrentSkipListSet<String>();
        this.typeAheadNode = new ConcurrentHashMap<Character, TypeAhead>();
    }
    // Adds movie to the index
    public void addMovie(String indexString,String displayName){
        char[] inputCharacters = indexString.toCharArray();
        Queue<Character> inputQueue= new LinkedList<Character>();
    }


    // add movies internal function
    private void addMovie(Queue<Character> indexQueue,String displayName){
        if(indexQueue.size() ==0){
            this.typeAheadResult.add(displayName);
            return;
        }
        else{
            this.typeAheadResult.add(displayName);
            char nextChar = indexQueue.poll();
            if(this.typeAheadNode.keySet().contains(nextChar)){
                this.typeAheadNode.get(nextChar).addMovie(indexQueue,displayName);
            }
            else{
                TypeAhead typeAhead = new TypeAhead();
                this.typeAheadNode.put(nextChar,typeAhead);
                typeAhead.addMovie(indexQueue,displayName);
            }
        }
    }

    //Searcher for the movie in the index
    public List<String> searchMovie(String indexString,int limit){
        List<String> searchResult = new ArrayList<String>();
        char[] inputCharacters = indexString.toCharArray();
        Queue<Character> inputQueue= new LinkedList<Character>();
        for(char ch:inputCharacters){
            inputQueue.add(ch);
        }
        return searchMovie(inputQueue,limit);
    }

    private List<String> searchMovie(Queue<Character> inputCharcaters,int limit){
        List<String> searchResult = new ArrayList<String>();
        if(inputCharcaters.size()==0) {
            searchResult= this.typeAheadResult.stream().limit(limit).collect(Collectors.<String>toList());
        }
        else{
            char topChar = inputCharcaters.poll();
            if(this.typeAheadNode.keySet().contains(topChar)){
                return this.typeAheadNode.get(topChar).searchMovie(inputCharcaters,limit);
            }
        }
        return searchResult;
    }
}
