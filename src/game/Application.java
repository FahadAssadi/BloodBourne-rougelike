package game;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Player;
import game.displays.FancyMessage;
import game.positions.*;
import game.positions.EnemyNest.Graveyard;
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

        // Created game maps for Abandoned Village and Burial Grounds
        GameMap gameMap = new GameMap(groundFactory, map);
        GameMap burialGrounds = new GameMap(groundFactory, map2);

        world.addGameMap(gameMap);
        world.addGameMap(burialGrounds);

        // Created Locked Gates for Burial Grounds and Abandoned Village
        LockedGate gateToBurialGrounds = new LockedGate(new MoveActorAction(burialGrounds.at(38,14), "to The Burial Grounds"));
        LockedGate gateToAbandonedVillage = new LockedGate(new MoveActorAction(gameMap.at(30,1), "to The Abandoned Village"));

        gameMap.at(30, 0).setGround(gateToBurialGrounds);
        burialGrounds.at(39,14).setGround(gateToAbandonedVillage);

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
