package Quests;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.file.Path;

public class QuestTwo {
    final private File file;

    public QuestTwo(String path) throws IOException {
        file = new File("src/main/resources/" + path);
        createFile();
    }

    public QuestTwo() throws IOException {
        file = new File("src/main/resources/test.txt");
        createFile();
    }

    public void writeToFile(String text) throws IOException {
        if (file.canWrite()) {
            try (PrintStream psout = new PrintStream(new FileOutputStream(file, true))) {
                psout.append(text);
            }
        }
    }


    public void readFile() throws IOException {
        if (file.canRead()) {
            try (FileInputStream fout = new FileInputStream(file)) {
                int c = '0';
                while ((c = fout.read()) != -1) {
                    System.out.print((char) c);
                }
            }
        } else {
            System.out.println("Can't read");
        }
    }

    private boolean createFile() throws IOException {
        return file.createNewFile();
    }

    public File getFile() {
        return file;
    }

    public boolean removeFile() {
        return file.delete();
    }
}
