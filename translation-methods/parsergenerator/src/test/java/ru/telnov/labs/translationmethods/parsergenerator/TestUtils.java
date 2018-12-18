package ru.telnov.labs.translationmethods.parsergenerator;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;
import ru.telnov.labs.translationmethods.parsergenerator.generator.out.brackets.Node;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public final class TestUtils {

    private final ClassLoader classLoader = this.getClass().getClassLoader();

    public CharStream getCharStreamFromFile(final String filename) throws IOException {
        return CharStreams.fromStream(classLoader.getResourceAsStream(filename));
    }

    public Path getPath(String filename) {
        try {
            return Paths.get(classLoader.getResource(filename).toURI());
        } catch (URISyntaxException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }
}
