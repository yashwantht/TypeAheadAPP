package com.Typeahead;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by yashw on 11/8/2018.
 */
public class FileParse {

    public static void parseFile(String fileName,TypeAhead typeAhead,IndexFinder indexFinder){
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String currentLine = br.readLine();
            while (currentLine!=null){
                for(String index:indexFinder.findIndex(currentLine)) {
                    typeAhead.addMovie(index, currentLine);
                }
            }
            br.close();
        }
        catch (java.io.FileNotFoundException e){

        }
        catch(java.io.IOException e){

        }

    }
}
