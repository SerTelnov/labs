package ru.telnov.labs.translationmethods.parsergenerator.generator.exception;

public class GeneratorException extends RuntimeException {

    public GeneratorException(Exception e) {
        super(e);
    }

    public GeneratorException(String message) {
        super(message);
    }
}
