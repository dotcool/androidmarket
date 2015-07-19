package com.example.netease.market;

import java.io.File;
import java.text.Collator;
import java.util.Comparator;

public class MyFileSorter  implements Comparator<File>{

	@Override
    public int compare(File object1, File object2) {
        
        int result = 0;
        
        result = compareByDir(object1, object2);
        
        return result;
    }
	
	private int compareByName(File object1, File object2) {
        
        Comparator<Object> cmp = Collator.getInstance(java.util.Locale.CHINA);
        
        return cmp.compare(object1.getName(), object2.getName());
    }
	
    private int compareByDir(File object1, File object2) {
        
        if (object1.isDirectory() && object2.isFile()) {
            return -1;
        } else if (object1.isDirectory() && object2.isDirectory()) {
            return compareByName(object1, object2);
        } else if (object1.isFile() && object2.isDirectory()) {
            return 1;
        } else {  //object1.isFile() && object2.isFile()) 
            return compareByName(object1, object2);
        }
    }
}

