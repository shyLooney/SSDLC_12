package Quests;

import javax.swing.filechooser.FileSystemView;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;

public class QuestOne {
    private File[] roots;
    private FileSystemView fsv;

    public QuestOne () {
        fsv = FileSystemView.getFileSystemView();
        roots = File.listRoots();
    }

    public void showInfo() {
        for (File root : roots) {
            try {
                System.out.println("___________________________________________________________");
                BasicFileAttributeView basicFileAttributeView = Files.getFileAttributeView(root.toPath(), BasicFileAttributeView.class);
                BasicFileAttributes attributes = basicFileAttributeView.readAttributes();

                System.out.println("Drive Name: " + root);
                System.out.println("Total Space (GB): " + root.getTotalSpace() / 1024.0 / 1024 / 1024);
                System.out.println("Description: " + fsv.getSystemTypeDescription(root));
                System.out.println("Is Drive: " + fsv.isDrive(root));
                System.out.println("Is File System: " + fsv.isFileSystem(root));
                System.out.println("Is File System Root: " + fsv.isFileSystemRoot(root));
                System.out.println("Is Floppy Drive: " + fsv.isFloppyDrive(root));
                System.out.println("Is Hidden File: " + fsv.isHiddenFile(root));
                System.out.println("Is Traversable: " + fsv.isTraversable(root));


                System.out.println("File Key: " + attributes.fileKey());
                System.out.println("Is Regular File: " + attributes.isRegularFile());
                System.out.println("Is Other: " + attributes.isOther());
                System.out.println("Is SymbolicLink: " + attributes.isSymbolicLink());
                System.out.println("Is Directory: " + attributes.isDirectory());
                System.out.println("Type: " + Files.getFileStore(Path.of(root.getPath())).type());
                // type file system?
                System.out.println("___________________________________________________________");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
