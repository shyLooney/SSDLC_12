import Quests.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException {
//        QuestTwo questTwo = new QuestTwo("abobka.txt");
//        questTwo.writeToFile("fdsfdsfsdfsdf");
//        QuestFive questFive = new QuestFive();
//        questFive.writeToZip(questTwo.getFile());
//        questFive.readUnzip();
//        questFive.remove();
//        System.out.println();

        Scanner scan = new Scanner(System.in);
        QuestOne questOne = new QuestOne();
        QuestTwo questTwo;
        QuestThree questThree = new QuestThree();
        QuestFour questFour = new QuestFour();
        QuestFive questFive = new QuestFive();

        boolean infoM = true;
        while (infoM) {
            System.out.println("MENU");
            System.out.println("________________________________________________");
            System.out.println("Choose: ");
            System.out.println("1. Quest one");
            System.out.println("2. Quest two");
            System.out.println("3. Quest three");
            System.out.println("4. Quest four");
            System.out.println("5. Quest five");
            System.out.println("6. Exit");
            System.out.println("________________________________________________");
            System.out.print("➔ ");
            switch (scan.nextInt()) {
                case 1:
                    questOne.showInfo();
                    break;
                case 2:
                    System.out.print("Create file...\nWrite filename ➔ ");
                    String filename = scan.next();
                    questTwo = new QuestTwo(filename);
                    boolean info = true;
                    while (info) {
                        System.out.println("MENU");
                        System.out.println("________________________________________________");
                        System.out.println("Choose: ");
                        System.out.println("1. Write to file");
                        System.out.println("2. Read file");
                        System.out.println("3. Remove file");
                        System.out.println("4. Exit");
                        System.out.println("________________________________________________");
                        System.out.print("➔ ");
                        switch (scan.nextInt()) {
                            case 1:
                                System.out.print("Write string ➔ ");
                                questTwo.writeToFile(scan.next());
                                break;
                            case 2:
                                System.out.println("File's content:\n______");
                                questTwo.readFile();
                                System.out.println("\n______");
                                break;
                            case 3:
                                questTwo.removeFile();
                                System.out.println("File was removed");
                                info = false;
                                break;
                            default:
                            case 4:
                                info = false;
                                break;
                        }
                    }
                    break;
                case 3:
                    boolean info3 = true;
                    while (info3) {
                        System.out.println("MENU");
                        System.out.println("________________________________________________");
                        System.out.println("Choose: ");
                        System.out.println("1. Create object and write to file");
                        System.out.println("2. Read file");
                        System.out.println("3. Remove file");
                        System.out.println("4. Exit");
                        System.out.println("________________________________________________");
                        System.out.print("➔ ");
                        switch (scan.nextInt()) {
                            case 1:
                                System.out.print("Creating Hero's object...\n\tName: ");
                                String name = scan.next();
                                System.out.print("\tHP: ");
                                int hp = scan.nextInt();
                                System.out.print("\tLevel: ");
                                int level = scan.nextInt();
                                System.out.print("\tXP: ");
                                double xp = scan.nextDouble();
                                questThree.saveHero(name, hp, level, xp);
                                break;
                            case 2:
                                System.out.println("File's content:\n______");
                                questThree.readSave();
                                System.out.println("\n______");
                                break;
                            case 3:
                                questThree.removeFile();
                                System.out.println("File was removed");
                                info3 = false;
                                break;
                            default:
                            case 4:
                                info3 = false;
                                break;
                        }
                    }

                    //hero.close();
                    break;
                case 4:
                    questFour = new QuestFour();
                    boolean info4 = true;
                    while (info4) {
                        System.out.println("MENU");
                        System.out.println("________________________________________________");
                        System.out.println("Choose: ");
                        System.out.println("1. Create object and write to file");
                        System.out.println("2. Read file");
                        System.out.println("3. Remove file");
                        System.out.println("4. Exit");
                        System.out.println("________________________________________________");
                        System.out.print("➔ ");
                        switch (scan.nextInt()) {
                            case 1:
                                System.out.print("Creating Employe's object...\n\tName: ");
                                String name = scan.next();
                                System.out.print("\tProfession: ");
                                String prf = scan.next();
                                System.out.print("\tAge: ");
                                Integer age = scan.nextInt();
                                questFour.writeXML(questFour.new Employee(name, prf, age));
                                break;
                            case 2:
                                System.out.println("File's content:\n______");
                                questFour.showXML();
                                System.out.println("\n______");
                                break;
                            case 3:
                                questFour.remove();
                                System.out.println("File was removed");
                                info4 = false;
                                break;
                            default:
                            case 4:
                                info4 = false;
                                break;
                        }
                    }
                    break;
                case 5:
                    questFive = new QuestFive();
                    boolean info5 = true;
                    while (info5) {
                        System.out.println("MENU");
                        System.out.println("________________________________________________");
                        System.out.println("Choose: ");
                        System.out.println("1. Choose file and add to zip");
                        System.out.println("2. Unzip and read file");
                        System.out.println("3. Remove files");
                        System.out.println("4. Exit");
                        System.out.println("________________________________________________");
                        System.out.print("➔ ");
                        switch (scan.nextInt()) {
                            case 1:
                                System.out.print("Write filename ➔ ");
                                questFive.writeToZip(new QuestTwo(scan.next()).getFile());
                                break;
                            case 2:
                                System.out.println("File's content:\n______");
                                questFive.readUnzip();
                                System.out.println("\n______");
                                break;
                            case 3:
                                questFive.remove();
                                System.out.println("Files was removed");
                                info5 = false;
                                break;
                            default:
                            case 4:
                                info5 = false;
                                break;
                        }
                    }
                    break;
                case 6:
                default:
                    infoM = false;
                    break;
            }
        }

    }
}
