package main.java.io;

import java.io.IOException;

/**
 * The Savable interface is implemented by classes that can be saved to a file.
 */
public interface Savable {
    void save() throws IOException;
}
