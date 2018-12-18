package ru.telnov.labs.translationmethods.parsergenerator.generator;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.telnov.labs.translationmethods.parsergenerator.TestUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class GeneratorTest {

    private static TestUtils utils = new TestUtils();
    private static final String PACKAGE_NAME = "ru/telnov/labs/translationmethods/parsergenerator/generator/out/";

    @BeforeClass
    public static void initParser() throws IOException {
        Path path = utils.getPath("grammars");

        Files.walk(path)
                .filter(Files::isDirectory)
                .map(Path::getFileName)
                .map(Path::toString)
                .filter(p -> !p.equals("grammars"))
                .forEach(dir -> {
                    Generator parserGenerator = new Generator(PACKAGE_NAME + "" + dir);
                    try {
                        String filePath = "grammars/" + dir + "/";
                        parserGenerator.generate(
                                utils.getCharStreamFromFile(filePath + "Grammar.gr"),
                                utils.getCharStreamFromFile(filePath + "Lexer.gr"));
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                });
    }

    @Test
    public void arithmetic_expressionTest() throws IOException {
        test("arithmetic_expression");
    }

    @Test
    public void logic_expressionTest() throws IOException {
        test("logic_expression");
    }

    @Test
    public void bracketsTest() throws IOException {
        test("brackets");
    }

    @Test
    public void helloTest() throws IOException {
        test("hello");
    }

    private void test(String filename) throws IOException {
        Path path = Paths.get("gen_parser/" + PACKAGE_NAME + filename);
        long numberOfFiles = Files.walk(path)
                .filter(Files::isRegularFile)
                .count();

            assertEquals(6, numberOfFiles);
    }
}
