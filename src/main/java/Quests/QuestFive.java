package Quests;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class QuestFive {
    private File file;
    final private String pathZip = "src/main/resources/output.zip";
    public QuestFive() {
        file = new File("");
    }

    public void writeToZip(File file) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(pathZip));
             FileInputStream fis = new FileInputStream(file)) {
            ZipEntry entry1 = new ZipEntry(file.getName());
            zout.putNextEntry(entry1);

            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);

            zout.write(buffer);
            zout.closeEntry();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void readUnzip() {
        try(ZipInputStream zin = new ZipInputStream(new FileInputStream(pathZip)))
        {
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                file = new File("src/main/resources/" + entry.getName());

                FileOutputStream fout = new FileOutputStream(file.getPath());
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);

                }
                System.out.println("File name: " + file.getPath());
                System.out.println("Length: " + file.length() + " B");
                System.out.println("Last modified: " + new SimpleDateFormat().format(new Date(file.lastModified())));

                QuestTwo questTwo = new QuestTwo(file.getName());
                System.out.println("Text in file: ");
                questTwo.readFile();

                fout.flush();
                zin.closeEntry();
                fout.close();

            }
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
    }

    public void remove() {
        file.delete();
        File file1 = new File(pathZip);
        file1.delete();
    }
}
