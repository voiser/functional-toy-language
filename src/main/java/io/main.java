package io;

import runtime.Export;

public class main {

    public static Export[] exports = new Export[] {
            new Export("puts", "a -> a", new String[] {"io.puts"}),
            new Export("File", "Str -> File", new String[0])
    };

}