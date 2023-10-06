package game;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.World;
import game.actors.enemies.ForestWatcher;
import game.actors.merchants.IsolatedTraveller;
import game.actors.Player;
import game.artifacts.consumables.Bloodberry;
import game.artifacts.weapons.GiantHammer;
import game.artifacts.weapons.GreatKnife;
import game.misc.displays.FancyMessage;
import game.positions.*;
import game.positions.enemynests.Bush;
import game.positions.enemynests.Graveyard;
import game.positions.enemynests.Hut;
import game.positions.enemynests.spawners.ForestKeeperSpawner;
import game.positions.enemynests.spawners.RedWolfSpawner;
import game.positions.Void;
import game.positions.enemynests.spawners.HollowSoliderSpawner;
import game.positions.enemynests.spawners.WanderingUndeadSpawner;

import java.util.Arrays;
import java.util.List;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Fahad Assadi
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(
                new Dirt(), new Wall(), new Floor(), new Puddle(),
                new Void()
        );

        // The Abandoned Village map
        List<String> map = Arrays.asList(
                "...........................................................",
                "...#######.................................................",
                "...#__.......................................++++..........",
                "...#..___#...................................+++++++.......",
                "...###.###................#######..............+++.........",
                "..........................#_____#................+++.......",
                "........~~................#_____#.................+........",
                ".........~~~..............###_###................++........",
                "...~~~~~~~~....+++.........................................",
                "....~~~~~........+++++++..................###..##...++++...",
                "~~~~~~~..............+++..................#___..#...++.....",
                "~~~~~~.................++.................#..___#....+++...",
                "~~~~~~~~~.................................#######.......++."
        );

        // The Burial Ground map
        List<String> map2 = Arrays.asList(
                "...........+++++++........~~~~~~++....~~",
                "...........++++++.........~~~~~~+.....~~",
                "............++++...........~~~~~......++",
                "............+.+.............~~~.......++",
                "..........++~~~.......................++",
                ".........+++~~~....#######...........+++",
                ".........++++~.....#_____#.........+++++",
                "..........+++......#_____#........++++++",
                "..........+++......###_###.......~~+++++",
                "..........~~.....................~~...++",
                "..........~~~..................++.......",
                "...........~~....~~~~~.........++.......",
                "......~~....++..~~~~~~~~~~~......~......",
                "....+~~~~..++++++++~~~~~~~~~....~~~.....",
                "....+~~~~..++++++++~~~..~~~~~..~~~~~...."
        );

        // The Ancient Woods map
        List<String> map3 = Arrays.asList(
                "....+++..............................+++++++++....~~~....~~~",
                "+...+++..............................++++++++.....~~~.....~~",
                "++...............#######..............++++.........~~.......",
                "++...............#_____#...........................~~~......",
                "+................#_____#............................~~......",
                ".................###_###............~...............~~.....~",
                "...............................~.+++~~..............~~....~~",
                ".....................~........~~+++++...............~~~...~~",
                "....................~~~.........++++............~~~~~~~...~~",
                "....................~~~~.~~~~..........~........~~~~~~.....~",
                "++++...............~~~~~~~~~~~........~~~.......~~~~~~......",
                "+++++..............~~~~~~~~~~~........~~~........~~~~~......"
        );

        // The Boss Map (Abxervyer)
        List<String> map4 = Arrays.asList(
                "~~~~.......+++......~+++++..............",
                "~~~~.......+++.......+++++..............",
                "~~~++......+++........++++..............",
                "~~~++......++...........+..............+",
                "~~~~~~...........+.......~~~++........++",
                "~~~~~~..........++++....~~~~++++......++",
                "~~~~~~...........+++++++~~~~.++++.....++",
                "~~~~~..............++++++~~...+++.....++",
                "......................+++......++.....++",
                ".......................+~~............++",
                ".......................~~~~...........++",
                "........................~~++...........+",
                ".....++++...............+++++...........",
                ".....++++~..............+++++...........",
                "......+++~~.............++++...........~",
                ".......++..++++.......................~~",
                "...........+++++......................~~",
                "...........++++++.....................~~",
                "..........~~+++++......................~",
                ".........~~~~++++..................~~..~"
        );

        // Created game maps for Abandoned Village, Burial Ground, Ancient Woods, and the Boss Map (Abxervyer)
        GameMap gameMap = new GameMap(groundFactory, map);
        GameMap burialGrounds = new GameMap(groundFactory, map2);
        GameMap ancientWoods = new GameMap(groundFactory, map3);
        GameMap bossMap = new GameMap(groundFactory, map4);
        world.addGameMap(gameMap);
        world.addGameMap(burialGrounds);
        world.addGameMap(ancientWoods);
        world.addGameMap(bossMap);

        // Created Locked Gates to allow possible travel between all maps in the game
        LockedGate gateToBurialGrounds = new LockedGate(new MoveActorAction(burialGrounds.at(38,14), "to The Burial Grounds"));
        LockedGate gateToAbandonedVillage = new LockedGate(new MoveActorAction(gameMap.at(30,1), "to The Abandoned Village"));
        LockedGate gateToAncientWoods = new LockedGate(new MoveActorAction(ancientWoods.at(38,11), "to The Ancient Woods"));
        LockedGate gateToBossMap = new LockedGate(new MoveActorAction(bossMap.at(6,1), "to The Boss Map"));

        // Set the travel Locked Gates in an arbitrary location in the respective maps
        gameMap.at(30, 0).setGround(gateToBurialGrounds);
        burialGrounds.at(39,14).setGround(gateToAbandonedVillage);
        burialGrounds.at(29,4).setGround(gateToAncientWoods);
        ancientWoods.at(20,11).setGround(gateToBurialGrounds);
        ancientWoods.at(6, 1).setGround(gateToBossMap);

        /*
        Create and set the Graveyard Spawners for the Wandering Undead in the Abandoned Village
         */
        Graveyard wanderingUndeadGraveyard1 = new Graveyard(new WanderingUndeadSpawner());
        Graveyard wanderingUndeadGraveyard2 = new Graveyard(new WanderingUndeadSpawner());

        gameMap.at(30,11).setGround(wanderingUndeadGraveyard1);
        gameMap.at(52,1).setGround(wanderingUndeadGraveyard2);

        /*
        Create and set the Graveyard Spawners for the Hollow Soldier in the Burial Ground
         */
        Graveyard hollowSoliderGraveyard1 = new Graveyard(new HollowSoliderSpawner());
        Graveyard hollowSoliderGraveyard2 = new Graveyard(new HollowSoliderSpawner());
        Graveyard hollowSoliderGraveyard3 = new Graveyard(new HollowSoliderSpawner());

        burialGrounds.at(23,2).setGround(hollowSoliderGraveyard1);
        burialGrounds.at(38,11).setGround(hollowSoliderGraveyard2);
        burialGrounds.at(2,14).setGround(hollowSoliderGraveyard3);

        /*
        Create and set the Hut Spawners for the Forest Keeper in the Ancient Woods
         */
        Hut hut1 = new Hut(new ForestKeeperSpawner());
        Hut hut2 = new Hut(new ForestKeeperSpawner());
        Hut hut3 = new Hut(new ForestKeeperSpawner());

        ancientWoods.at(10,1).setGround(hut1);
        ancientWoods.at(15,5).setGround(hut2);
        ancientWoods.at(19,9).setGround(hut3);

        /*
        Create and set the Hut Spawners for the Forest Keeper in the Boss Map (Abxervyer)
         */
        Hut bossHut1 = new Hut(new ForestKeeperSpawner());
        Hut bossHut2 = new Hut(new ForestKeeperSpawner());
        Hut bossHut3 = new Hut(new ForestKeeperSpawner());

        bossMap.at(6, 3).setGround(bossHut1);
        bossMap.at(3, 11).setGround(bossHut2);
        bossMap.at(8, 10).setGround(bossHut3);

        /*
        Create and set the Bush Spawners for the Red Wolf in the Ancient Woods
         */
        Bush bush1 = new Bush(new RedWolfSpawner());
        Bush bush2 = new Bush(new RedWolfSpawner());
        Bush bush3 = new Bush(new RedWolfSpawner());

        ancientWoods.at(25,3).setGround(bush1);
        ancientWoods.at(30,7).setGround(bush2);
        ancientWoods.at(35,11).setGround(bush3);

        /*
        Create and set the Bush Spawners for the Red Wolf in the Boss Map (Abxervyer)
         */
        Bush bossBush1 = new Bush(new RedWolfSpawner());
        Bush bossBush2 = new Bush(new RedWolfSpawner());
        Bush bossBush3 = new Bush(new RedWolfSpawner());

        bossMap.at(18, 3).setGround(bossBush1);
        bossMap.at(20, 5).setGround(bossBush2);
        bossMap.at(19, 10).setGround(bossBush3);

        // Add relevant items and weapons to be found in various maps in arbitrary locations
        gameMap.at(27, 6).addItem(new Broadsword());
        ancientWoods.at(38,5).addItem(new Bloodberry());
        ancientWoods.at(32,10).addItem(new Bloodberry());
        ancientWoods.at(28,11).addItem(new Bloodberry());
        bossMap.at(9,15).addItem(new GiantHammer());

        // Add the Isolated Traveller actor to the Ancient Woods
        ancientWoods.at(20,3).addActor(new IsolatedTraveller());

        /*
        Add the Boss (Abxervyer - The Forest Watcher) to the Boss Map (Abxervyer)
        Pass in a Locked Gate object back to the Ancient Woods to its constructor
        as that's its post-death formation that appears once it is defeated
         */
        bossMap.at(20, 10).addActor(new ForestWatcher(gateToAncientWoods));

        // Printing the DESIGN BORNE logo
        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        // Adding the player to the Abandoned Village map
        Player player = new Player();
        world.addPlayer(player, gameMap.at(29, 5));

//        // TESTING: Starting in Ancient Woods
//        Player player = new Player();
//        player.addBalance(50000);
//        world.addPlayer(player, ancientWoods.at(20, 5));
//        ancientWoods.at(20,6).addItem(new GreatKnife());

        // Run the game world
        world.run();
    }
}
