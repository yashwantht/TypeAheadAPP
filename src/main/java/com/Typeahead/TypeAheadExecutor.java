package com.Typeahead;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by yashw on 11/8/2018.
 */
public class TypeAheadExecutor {
    public   ExecutorService executorService ;


    public  TypeAheadExecutor() {
        executorService =  newExecutorService(3);
    }




    public  void shutdownExecutorService(int timeout) {
        try {
            if(!executorService.awaitTermination(timeout, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        }catch(final InterruptedException ie) {
            executorService.shutdownNow();
        }
    }


    public static ExecutorService newExecutorService(int scalingFactor) {
        int nThreads = Runtime.getRuntime().availableProcessors() * scalingFactor;
        return Executors.newFixedThreadPool(nThreads);
    }
}
