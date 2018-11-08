package com.Typeahead;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.Future;

/**
 * Created by yashw on 11/8/2018.
 */
public class Main {
    public static void main(String[] args) {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        String line="";
        TypeAheadExecutor typeAheadExecutor = new TypeAheadExecutor();
        TypeAhead root = new TypeAhead();
        IndexFinder indexFinder = new IndexFinder();
        while(!line.equals("quit")) {
            try {
                // Reading data using readLine
                line = reader.readLine();
                String[] splitString = line.split(" ");
                if (splitString.length > 0) {
                    if (splitString[0].equals("process-file")) {
                        if (splitString.length == 2) {
                            Future future = typeAheadExecutor.executorService.submit(()->{
                                FileParse.parseFile(splitString[1], root, indexFinder);
                            });
                        }
                    }
                    else if(splitString[0].equals("query")){
                        if(splitString.length == 2){
                            String query = splitString[1];
                            for(String movie:root.searchMovie(query,10)){
                                System.out.println(movie);
                            }
                        }
                    }
                    else if(splitString[0].equals("quit")){
                        typeAheadExecutor.shutdownExecutorService(100);
                    }
                }
            } catch (java.io.IOException e) {

            }
        }
    }
}
