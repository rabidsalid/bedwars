package me.rabidsalid.bedwars.Generators;

import java.util.ArrayList;

public class GeneratorManager {
    private final ArrayList<Generator> generatorList;

    public GeneratorManager() {
        generatorList = new ArrayList<>();
    }

    public ArrayList<Generator> getGeneratorList() {
        return generatorList;
    }

    public void addGenerator(Generator gen) {
        generatorList.add(gen);
    }

    public void removeGenerator(Generator gen) {
        generatorList.remove(gen);
    }
}
