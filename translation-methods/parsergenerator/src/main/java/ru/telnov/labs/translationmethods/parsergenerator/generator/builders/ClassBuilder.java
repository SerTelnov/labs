package ru.telnov.labs.translationmethods.parsergenerator.generator.builders;

import ru.telnov.labs.translationmethods.parsergenerator.tokens.Terminal;
import ru.telnov.labs.translationmethods.parsergenerator.utils.Constants;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.telnov.labs.translationmethods.parsergenerator.utils.Constants.TABS;

public final class ClassBuilder {

    private StringBuilder stringBuilder;
    private int currLever = 0;
    private String srcPackageName;

    public ClassBuilder(final String packageName) {
        this.srcPackageName = packageName;
    }

    public void buildClass(Clazz clazz) {
        initFile();
        clazz.build(this);
        print(clazz.getClassName());
    }

    public void buildHelperClass(String helperClassName) {
        initFile();
        final String classNode = helperClassName + ".java";
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(classNode);

        byte[] buffer = new byte[1024];
        try (OutputStream os = Files.newOutputStream(getPath(classNode))) {
            os.write(stringBuilder.toString().getBytes());

            int reader;
            while ((reader = is.read(buffer)) != -1) {
                os.write(buffer, 0, reader);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public void buildEnum(List<Terminal> list) {
        initFile();

        newLine("public enum")
                .append(Constants.ENUM_TYPE_CLASS)
                .appendEndLine("{\n");

        List<Terminal> tokenValues = new ArrayList<>(list);
        tokenValues.add(Constants.END_LINE_TERMINAL);

        appendLine(tokenValues.stream()
                .map(terminal -> String.format("%s(\"%s\")", terminal.getName(), terminal.getValue()))
                .collect(Collectors.joining(",\n" + TABS[currLever], "", ";\n")));

        appendLine("public final String pattern;\n")
                .newLine("private TypeToken(String s)")
                .appendEndLine("{")
                .appendLine("this.pattern = s;")
                .appendLine("}")
                .appendLine("}");

        print(Constants.ENUM_TYPE_CLASS);
    }

    private Path getPath(String filename) throws IOException {
        Path mainDir = Paths.get(Constants.GEN_PACKAGE_NAME);

        if (Files.notExists(mainDir)) {
            Files.createDirectories(mainDir);
        }

        Path srcDir = Paths.get(Constants.GEN_PACKAGE_NAME + "/" + srcPackageName);

        if (Files.notExists(srcDir)) {
            Files.createDirectories(srcDir);
        }

        return Paths.get(srcDir.toString() + "/" + filename);
    }

    private void print(String filename) {
        try (BufferedWriter bw = Files.newBufferedWriter(getPath(filename + ".java"))) {
            bw.write(stringBuilder.toString());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public ClassBuilder appendLine(String line) {
        int curBalance = currLever;
        balance(line);

        if (curBalance > currLever) {
            curBalance = currLever;
        }
        stringBuilder.append(TABS[curBalance])
                .append(line)
                .append('\n');
        return this;
    }

    public ClassBuilder newLine() {
        stringBuilder.append(TABS[currLever]);
        return this;
    }

    public ClassBuilder appendEndLine(String s) {
        balance(s);
        stringBuilder.append(s)
                .append('\n');
        return this;
    }

    public ClassBuilder newLine(String s) {
        addNewLine(s);
        stringBuilder.append(' ');
        return this;
    }

    public ClassBuilder addNewLine(String s) {
        balance(s);
        stringBuilder.append(TABS[currLever])
                .append(s);
        return this;
    }

    public ClassBuilder append(String s) {
        stringBuilder.append(s).append(' ');
        balance(s);
        return this;
    }

    public ClassBuilder appendArgs(List<Arg> args) {
        stringBuilder.append(args.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(", ", "(", ")")));
        return this;
    }

    public ClassBuilder add(String s) {
        stringBuilder.append(s);
        balance(s);
        return this;
    }

    private void balance(String s) {
        for (int i = 0; i != s.length(); i++) {
            if (s.charAt(i) == '{') {
                currLever++;
            } else if (s.charAt(i) == '}') {
                currLever--;
            }
        }
    }

    private void initFile() {
        stringBuilder = new StringBuilder();
        currLever = 0;

        appendLine(Constants.FILE_HEADER);
        appendLine("\n");

        if (!srcPackageName.isEmpty()) {
            newLine("package")
                    .add(srcPackageName.replaceAll("/", "."))
                    .appendEndLine(";");
            appendLine("");
        }
    }
}
