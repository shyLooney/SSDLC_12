package Quests;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.print.Book;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class QuestThree {
    private Hero hero;
    private QuestTwo questTwo;

    public QuestThree() throws IOException {
        questTwo = new QuestTwo("my.json");
    }

    public void saveHero(String name, int hp, int level, Double xp) throws IOException {
        hero = new Hero(name, hp, level, xp);
        FileWriter fileWriter = new FileWriter(questTwo.getFile(), true);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(fileWriter, hero);
        questTwo.writeToFile("\n");
    }

    public void readSave() throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();

//            List<Hero> temp = Arrays.asList((mapper.readValue(questTwo.getFile(), Hero.class)));
            Hero temp = mapper.readValue(questTwo.getFile(), Hero.class);

//            temp.forEach(System.out::println);
            System.out.println(temp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void removeFile() {
        questTwo.removeFile();
    }

    @JsonAutoDetect
    static class Hero {
        private String name;
        private int hp;
        private int level;
        private Double xp;

        public Hero(String name, int hp, int level, Double xp) {
            this.name = name;
            this.hp = hp;
            this.level = level;
            this.xp = xp;
        }

        public Hero() {

        }

        @Override
        public String toString() {
            return "Hero{" +
                    "name='" + name + '\'' +
                    ", hp=" + hp +
                    ", level=" + level +
                    ", xp=" + xp +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getHp() {
            return hp;
        }

        public void setHp(int hp) {
            this.hp = hp;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public Double getXp() {
            return xp;
        }

        public void setXp(Double xp) {
            this.xp = xp;
        }
    }
}
