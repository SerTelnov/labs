package turing.machine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Telnov Sergey on 04.06.2018.
 */

public class TuringMachine {

    private String start = "S";
    private String accepted = "AC";
    private String reject = "RJ";
    private char blank = '_';
    private int numberOfTapes;
    private HashMap<String, HashMap<TransitionKey, Transition>> actions = new HashMap<>();

    private char[][] tapes;
    private int pointers[];
    private String status;

    public TuringMachine(String fileName) {
        try {
            List<String> commands = Files.readAllLines(Paths.get(fileName));

            for (int i = 0; i != 4; i++) {
                String[] args = commands.get(i).split("\\s+");

                switch (args[0]) {
                    case "start:":
                        start = args[1];
                        break;
                    case "accept:":
                        accepted = args[1];
                        break;
                    case "reject:":
                        reject = args[1];
                        break;
                    case "blank:":
                        blank = args[1].charAt(0);
                        break;
                    default:
                        throw new RuntimeException("invalid command: " + args[0]);
                }
            }

            init(1, commands.subList(4, commands.size()));
        } catch (IOException e) {
            System.err.println("Can't read text from file: " + fileName);
            e.printStackTrace();
        }
    }

    public TuringMachine(String fileName, final int numberOfTapes) {
        try {
            List<String> commands = Files.readAllLines(Paths.get(fileName));

            init(numberOfTapes, commands.subList(1, commands.size()));
        } catch (IOException e) {
            System.err.println("Can't read lines from file: " + fileName);
            e.printStackTrace();
        }
    }

    /*
2
S 0 0 -> a 1 > 2 >
a 1 1 -> S 333 ^ szdf <
a 2 3 -> AC 333 ^ _ ^
     */

    private void init(final int numberOfTapes, List<String> commands) {
        assert numberOfTapes > 0;

        this.numberOfTapes = numberOfTapes;
        pointers = new int[numberOfTapes];
        tapes = new char[numberOfTapes][];

        commands.forEach(str -> {
            String[] args = str.split("\\s+");
            if (args.length > 0) {
                actions.putIfAbsent(args[0], new HashMap<>());
                char[] key = new char[numberOfTapes];

                for (int i = 0; i != numberOfTapes; i++) {
                    key[i] = args[i + 1].charAt(0);
                }

                TransitionKey keys = new TransitionKey(key);
                if (actions.get(args[0]).containsKey(keys)) {
                    throw new RuntimeException("duplicated action: " + str);
                }

                try {
                    actions.get(args[0])
                            .put(keys, new Transition(
                                    Arrays.copyOfRange(args, numberOfTapes + 2, args.length))
                            );
                } catch (Exception e) {
                    System.err.println("failed: " + str + "number: " + commands.indexOf(str));
                    throw e;
                }
            } else {
                throw new RuntimeException("invalid number of args: " + str);
            }
        });
    }

    public char getBlank() {
        return blank;
    }

    private void setTapes(String input) {
        char[] emptyTape = new char[input.length()];
        Arrays.fill(emptyTape, blank);

        tapes[0] = input.toCharArray();
        for (int i = 1; i != numberOfTapes; i++) {
            tapes[i] = Arrays.copyOf(emptyTape, input.length());
        }
    }

    private void setPointers(final int startPoint) {
        Arrays.fill(pointers, startPoint);
    }

    private TransitionKey getTransitionKey() {
        char[] key = new char[numberOfTapes];
        for (int i = 0; i != numberOfTapes; i++) {
            key[i] = tapes[i][pointers[i]];
        }
        return new TransitionKey(key);
    }

    private void run(String tapeString, final int startPoint) {
        status = start;
        setTapes(tapeString);
        setPointers(startPoint);

        long stepsCounter = 0;
        HashMap<String, Integer> statusCounter = new HashMap<>();
        statusCounter.put(start, 1);

        while (!(status.equals(accepted) || status.equals(reject))) {
            TransitionKey key = getTransitionKey();

            try {
                actions
                        .get(status)
                        .get(key)
                        .move();
            } catch (Exception e) {
                throw new RuntimeException(
                        String.format("no command: <%s> <%s>", status, key), e);
            }

            if (stepsCounter > 10000000) {
                throw new RuntimeException("too many steps\nCurrent tape:" + Arrays.toString(tapes[0]) + "\n" +
                        statusCounter
                                .entrySet()
                                .stream()
                                .sorted(Comparator.comparing(Map.Entry::getValue))
                                .map(it -> String.format("status: %s counter: %d", it.getKey(), it.getValue()))
                                .collect(Collectors.joining("\n"))
                );
            }
            stepsCounter++;

            statusCounter.putIfAbsent(status, 0);
            statusCounter.put(status, statusCounter.get(status) + 1);
        }
    }

    private void run(String tapeString) {
        int index = 0;
        while (index < tapeString.length() && tapeString.charAt(index) == blank) {
            index++;
        }

        if (index > 0) {
            index--;
        }

        run(tapeString, index);
    }

    public boolean solve(String tape) {
        run(tape);
        return status.equals(accepted);
    }

    public boolean solve(String tape, final int startPoint) {
        run(tape, startPoint);
        return status.equals(accepted);
    }

    public String solveAndGetTape(String input) {
        run(input);

        StringBuilder builder = new StringBuilder();
        int pointer = pointers[0];
        char[] tape = tapes[0];

        for (int i = pointer; i != tape.length; i++) {
            if (tape[i] == blank) {
                break;
            } else {
                builder.append(tape[i]);
            }
        }
        return builder.toString();
    }

    private enum MoveSymbol {
        NEXT,
        PREV,
        HALT;

        @Override
        public String toString() {
            if (this.equals(MoveSymbol.NEXT)) {
                return ">";
            } else if (this.equals(MoveSymbol.PREV)) {
                return "<";
            } else {
                return "^";
            }
        }
    }

    private class TransitionKey {
        private char[] data;

        public TransitionKey(String[] symbols) {
            data = new char[numberOfTapes];

            for (int i = 0; i != numberOfTapes; i++) {
                data[i] = symbols[i].charAt(0);
            }
        }

        TransitionKey(char[] data) {
            this.data = data;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(data);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof TransitionKey) {
                for (int i = 0; i != numberOfTapes; i++) {
                    if (data[i] != ((TransitionKey) obj).data[i]) {
                        return false;
                    }
                }
            }
            return true;
        }

        @Override
        public String toString() {
            return Arrays.toString(data);
        }
    }

    private class Transition {

        private final String nextAction;
        private char[] newSymbols;
        private MoveSymbol[] moves;

        Transition(String[] commands) {
            nextAction = commands[0];
            newSymbols = new char[numberOfTapes];
            moves = new MoveSymbol[numberOfTapes];

            for (int j = 0; j < numberOfTapes; j++) {
                newSymbols[j] = commands[j * 2 + 1].charAt(0);
                String curr = commands[j * 2 + 2];

                switch (curr) {
                    case ">":
                        moves[j] = MoveSymbol.NEXT;
                        break;
                    case "<":
                        moves[j] = MoveSymbol.PREV;
                        break;
                    default:
                        moves[j] = MoveSymbol.HALT;
                        break;
                }
            }
        }

        void move() {
            status = nextAction;

            for (int i = 0; i != numberOfTapes; i++) {
                tapes[i][pointers[i]] = newSymbols[i];

                if (moves[i].equals(MoveSymbol.NEXT)) {
                    pointers[i]++;
                } else if (moves[i].equals(MoveSymbol.PREV)) {
                    pointers[i]--;
                }
            }
        }

        @Override
        public String toString() {
            return String.format("%s %s %s", nextAction, Arrays.toString(newSymbols), Arrays.toString(moves));
        }
    }
}
