package ru.telnov.labs.translationmethods.antlr;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;
import ru.telnov.labs.translationmethods.antlr.gen.LiteJavaLexer;
import ru.telnov.labs.translationmethods.antlr.gen.LiteJavaParser2;
import ru.telnov.labs.translationmethods.antlr.tokens.ClassToken;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

public class ParserTest {

    @Test
    public void helloWorldTest() throws IOException {
        customTest("Hello.java");
    }

    @Test
    public void loopTest() throws IOException {
        customTest("Loop.java");
    }

    @Test
    public void expressionTest() throws IOException {
        customTest("Expression.java");
    }

    @Test
    public void simpleClassTest() throws IOException {
        customTest("SimpleClass.java");
    }

    @Test
    public void conditionTest() throws IOException {
        customTest("Condition.java");
    }

    @Test
    public void whileTest() throws IOException {
        customTest("WhileCl.java");
    }

    @Test
    public void printClassTest() throws IOException {
        LiteJavaLexer lexer = new LiteJavaLexer(getCharStreamFromFile("WhileCl.java"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        LiteJavaParser2 parser = new LiteJavaParser2(tokens);
        ClassToken tree = parser.start().cl;

        tree.printClass();
    }

    private void customTest(final String filename) throws IOException {
        LiteJavaLexer lexer = new LiteJavaLexer(getCharStreamFromFile(filename));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        LiteJavaParser2 parser = new LiteJavaParser2(tokens);
        ClassToken clazz = parser.start().cl;

        System.out.println(clazz.toString());
        System.out.println("\n#######\n");

        Path tmp = Files.createTempFile(clazz.getClassName(), ".java");
        tmp.toFile().deleteOnExit();

        clazz.printPath(tmp);
        compile(tmp.toFile(), clazz.getClassName());
    }

    private CharStream getCharStreamFromFile(final String filename) throws IOException {
        return CharStreams.fromStream(this.getClass().getClassLoader()
                .getResourceAsStream(filename));
    }

    private void compile(File sourceFile, String classname) throws IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        File parentDirectory = sourceFile.getParentFile();
        parentDirectory.deleteOnExit();

        fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Collections.singletonList(parentDirectory));
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(
                Collections.singletonList(sourceFile));

        compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
        fileManager.close();

        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{parentDirectory.toURI().toURL()});

        try {
            classLoader.loadClass(classname);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Files.walk(parentDirectory.toPath())
                .filter(p -> p.getFileName().toString().startsWith(classname))
                .forEach(p -> {
                    try {
                        Files.delete(p);
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                });
    }
}
