package me.rabidsalid.bedwars.Generators;

import me.rabidsalid.bedwars.Configs.Config;
import me.rabidsalid.bedwars.Generators.ElementGenerators.DiamondGenerator;
import me.rabidsalid.bedwars.Generators.ElementGenerators.EmeraldGenerator;
import me.rabidsalid.bedwars.Generators.ElementGenerators.GoldGenerator;
import me.rabidsalid.bedwars.Generators.ElementGenerators.IronGenerator;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

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
    // not a very good way of doing it because diamond and emerald locations are higher than block but it works so we up
    public boolean removeGenerator(Location location) {
        int playerX = (int) location.getX();
        int playerY = (int) location.getY();
        int playerZ = (int) location.getZ();
        for (Generator gen: generatorList) {
            int genX = (int) gen.getLocation().getX();
            int genY = (int) gen.getLocation().getY();
            int genZ = (int) gen.getLocation().getZ();
            if (genX == playerX && genY == playerY && genZ == playerZ) {
                generatorList.remove(gen);
                return true;
            }
        }
        return false;
    }

    public void saveGenerators() {
        FileConfiguration config = Config.getConfig();
        config.createSection("Generators");
        config.createSection("Generators.IronGenerators");
        config.createSection("Generators.GoldGenerators");
        config.createSection("Generators.DiamondGenerators");
        config.createSection("Generators.EmeraldGenerators");
        List<Location> ironGens = new ArrayList<>();
        List<Location> goldGens = new ArrayList<>();
        List<Location> diaGens = new ArrayList<>();
        List<Location> emGens = new ArrayList<>();
        for (Generator gen: generatorList) {
            if (gen instanceof IronGenerator) {
                ironGens.add(gen.getLocation());
            }
            else if (gen instanceof GoldGenerator) {
                goldGens.add(gen.getLocation());
            }
            else if (gen instanceof DiamondGenerator) {
                diaGens.add(gen.getLocation());
            }
            else if (gen instanceof EmeraldGenerator) {
                emGens.add(gen.getLocation());
            }
            else {
                throw new IllegalArgumentException("Something went wrong saving generators");
            }
        }
        config.set("Generators.IronGenerators", ironGens);
        config.set("Generators.GoldGenerators", goldGens);
        config.set("Generators.DiamondGenerators", diaGens);
        config.set("Generators.EmeraldGenerators", emGens);
        Config.saveConfig();
    }
    @SuppressWarnings("unchecked")
    public void loadGenerators() {
        FileConfiguration config = Config.getConfig();
        List<Location> ironGens = (ArrayList<Location>) config.get("Generators.IronGenerators");
        List<Location> goldGens = (ArrayList<Location>) config.get("Generators.GoldGenerators");
        List<Location> diaGens = (ArrayList<Location>) config.get("Generators.DiamondGenerators");
        List<Location> emGens = (ArrayList<Location>) config.get("Generators.EmeraldGenerators");

        for (Location loc: ironGens) {
            loc.setY(loc.getY()-0.5);
            generatorList.add(new IronGenerator(loc));
        }
        for (Location loc: goldGens) {
            loc.setY(loc.getY()-0.5);
            generatorList.add(new GoldGenerator(loc));
        }
        for (Location loc: diaGens) {
            loc.setY(loc.getY()-2.5);
            generatorList.add(new DiamondGenerator(loc));
        }
        for (Location loc: emGens) {
            loc.setY(loc.getY()-2.5);
            generatorList.add(new EmeraldGenerator(loc));
        }
    }
}
