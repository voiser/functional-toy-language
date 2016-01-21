package io;

import runtime.Thing;

public class File extends Thing {

    public static String[] isa = new String[] {"Eq"};

    public static String[] fields = new String[] {
      "path : Str"
    };

    public final Thing path;

    public File(Thing path) {
        this.path = path;
    }
}
