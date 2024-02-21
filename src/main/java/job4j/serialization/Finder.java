package job4j.serialization;

import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Finder {
    public static void main(String[] args) {
        findFiles(args);
    }

    private static void findFiles(String[] args) {
        String directory = null, toFind = null, output = null;
        Search type = null;
        for (String arg : args) {
            String[] conf = arg.split("=");
            if (conf.length < 2) continue;
            switch (conf[0]) {
                case "-d" -> directory = conf[1];
                case "-n" -> toFind = conf[1];
                case "-t" ->
                        type = conf[1].equals("regex") ? Search.regex : conf[1].equals("mask") ? Search.mask : Search.match;
                case "-o" -> output = conf[1];
                default -> System.out.println("We couldn't match config parameter " + arg);
            }
        }
        if (directory == null || toFind == null || output == null) {
            throw new IllegalArgumentException("One of parameters is null");
        }
        findFiles(directory, toFind, type == null ? Search.match : type, output);
    }

    private static void findFiles(String directory, String toFind, Search type, String outputFile) {
        File root = new File(directory);
        String find = toFind;
        Predicate<String> matches = null;
        switch (type) {
            case match -> matches = text -> text.equals(find);
            case regex -> matches = text -> Pattern.matches(find, text);
            case mask -> {
                toFind = toFind.replaceAll("\\.", "[.]")
                        .replaceAll("\\*", ".*")
                        .replaceAll("\\?", ".");
                matches = text -> Pattern.matches(find, text);
            }
        }
        if (matches == null) {
            throw new IllegalArgumentException();
        }
        List<File> files = searchInDirectory(root, new ArrayList<>(), matches);
        try (FileWriter fileWriter = new FileWriter(outputFile)) {
            for (File file : files) {
                fileWriter.write(file.getAbsolutePath());
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<File> searchInDirectory(File directory, List<File> rsl, Predicate<String> matches) {
        if (directory.listFiles() == null) return new ArrayList<>();
        List<File> filesFound = new ArrayList<>(Arrays.asList(directory.listFiles()));
        for (File file : filesFound) {
            if (file.isDirectory()) {
                searchInDirectory(file, rsl, matches);
            } else if (matches.test(file.getName())) {
                rsl.add(file);
            }
        }
        return rsl;
    }
}

enum Search {
    mask,
    match,
    regex
}
