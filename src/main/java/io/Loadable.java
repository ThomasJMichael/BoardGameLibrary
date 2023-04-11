package main.java.io;

import java.io.IOException;

/**
 * The Loadable interface is implemented by classes that can be loaded from a file.
 */
public interface Loadable {
    void load() throws IOException;
}
