package game;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Player;
import game.artifacts.consumables.Bloodberry;
import game.displays.FancyMessage;
import game.positions.*;
import game.positions.EnemyNest.Bush;
import game.positions.EnemyNest.Graveyard;
import game.positions.EnemyNest.Hut;
import game.positions.EnemyNest.spawners.ForestKeeperSpawner;
import game.positions.EnemyNest.spawners.RedWolfSpawner;
import game.positions.Void;
import game.positions.EnemyNest.spawners.HollowSoliderSpawner;
import game.positions.EnemyNest.spawners.WanderingUndeadSpawner;
import game.weapons.Broadsword;

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

        // Created game maps for Abandoned Village and Burial Grounds
        GameMap gameMap = new GameMap(groundFactory, map);
        GameMap burialGrounds = new GameMap(groundFactory, map2);
        GameMap ancientWoods = new GameMap(groundFactory, map3);

        world.addGameMap(gameMap);
        world.addGameMap(burialGrounds);
        world.addGameMap(ancientWoods);

        // Created Locked Gates for Burial Grounds and Abandoned Village
        LockedGate gateToBurialGrounds = new LockedGate(new MoveActorAction(burialGrounds.at(38,14), "to The Burial Grounds"));
        LockedGate gateToAbandonedVillage = new LockedGate(new MoveActorAction(gameMap.at(30,1), "to The Abandoned Village"));
        LockedGate gateToAncientWoods = new LockedGate(new MoveActorAction(ancientWoods.at(38,11), "to The Ancient Woods"));

        gameMap.at(30, 0).setGround(gateToBurialGrounds);
        burialGrounds.at(39,14).setGround(gateToAbandonedVillage);

        burialGrounds.at(29,4).setGround(gateToAncientWoods);
        ancientWoods.at(20,11).setGround(gateToBurialGrounds);

        Graveyard wanderingUndeadGraveyard1 = new Graveyard(new WanderingUndeadSpawner());
        Graveyard wanderingUndeadGraveyard2 = new Graveyard(new WanderingUndeadSpawner());

        gameMap.at(30,11).setGround(wanderingUndeadGraveyard1);
        gameMap.at(52,1).setGround(wanderingUndeadGraveyard2);

        Graveyard hollowSoliderGraveyard1 = new Graveyard(new HollowSoliderSpawner());
        Graveyard hollowSoliderGraveyard2 = new Graveyard(new HollowSoliderSpawner());
        Graveyard hollowSoliderGraveyard3 = new Graveyard(new HollowSoliderSpawner());

        burialGrounds.at(23,2).setGround(hollowSoliderGraveyard1);
        burialGrounds.at(38,11).setGround(hollowSoliderGraveyard2);
        burialGrounds.at(2,14).setGround(hollowSoliderGraveyard3);

        Hut hut1 = new Hut(new ForestKeeperSpawner());
        Hut hut2 = new Hut(new ForestKeeperSpawner());
        Hut hut3 = new Hut(new ForestKeeperSpawner());

        Bush bush1 = new Bush(new RedWolfSpawner());
        Bush bush2 = new Bush(new RedWolfSpawner());
        Bush bush3 = new Bush(new RedWolfSpawner());

        ancientWoods.at(10,1).setGround(hut1);
        ancientWoods.at(15,5).setGround(hut2);
        ancientWoods.at(19,9).setGround(hut3);

        ancientWoods.at(25,3).setGround(bush1);
        ancientWoods.at(30,7).setGround(bush2);
        ancientWoods.at(35,11).setGround(bush3);

        ancientWoods.at(38,5).addItem(new Bloodberry());
        ancientWoods.at(32,10).addItem(new Bloodberry());
        ancientWoods.at(28,11).addItem(new Bloodberry());


        // Printing the DESIGN BORNE logo
        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        // Adding the player on the map
        Player player = new Player();
        world.addPlayer(player, gameMap.at(29, 5));

        // Added the Broadsword on the map
        Item broadsword = new Broadsword();
        gameMap.at(27, 6).addItem(broadsword);

        world.run();
    }
}
