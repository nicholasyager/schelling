package main.java;

import org.apache.commons.cli.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class Main {

    private static int width, height, numRaces, steps;
    private static double threshold;

    private static String outputDirectory;

    public static void main(String[] args) throws InterruptedException, IOException, ParseException {

        parseCLI(args);

        System.out.println(width + " " + height);
        World world = new World(width, height, numRaces, threshold);

        for (int i = 0; i < steps; i++) {
            File f = new File(outputDirectory+"/" + String.format("%03d", i) + ".png");
            f.mkdirs();
            ImageIO.write(world.getImage(), "PNG", f);
            world.step();
        }

    }

    private static Options makeOptions() {
        Options options = new Options();
        options.addOption("s", "steps", true, "The number of steps to simulate.");
        options.addOption("o", "output", true, "The output directory for the simulation.");
        options.addOption("wi", "width", true, "The width of the world.");
        options.addOption("he", "height", true, "The height of the world.");
        options.addOption("r", "races", true, "The number of races to simulate.");
        options.addOption("t", "threshold", true, "The minority threshold.");
        options.addOption(Option.builder("h").longOpt("help").build());

        return options;
    }

    private static void parseCLI(String... args) throws ParseException {
        Options options = makeOptions();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        if (cmd.hasOption("h")) {
            String header = "A Java-based Schelling segregation simulator.\n\n";
            String footer = "\nPlease report issues to yager@nicholasyager.com";
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("schelling", header, options, footer, true);
            System.exit(0);
        }

        if (cmd.hasOption("o")) {
            outputDirectory = cmd.getOptionValue("o");
        } else {
            outputDirectory = "images";
        }

        if (cmd.hasOption("s")) {
            steps = Integer.parseInt(cmd.getOptionValue("s"));
        } else {
            steps = 1000;
        }

        if (cmd.hasOption("wi")) {
            width = Integer.parseInt(cmd.getOptionValue("w"));
        } else {
            width = 500;
        }

        if (cmd.hasOption("he")) {
            height = Integer.parseInt(cmd.getOptionValue("h"));
        } else {
            height = 500;
        }

        if (cmd.hasOption("r")) {
            numRaces = Integer.parseInt(cmd.getOptionValue("r"));
        } else {
            numRaces = 2;
        }

        if (cmd.hasOption("t")) {
            threshold = Double.parseDouble(cmd.getOptionValue("t"));
        } else {
            threshold = 0.2;
        }
    }
}
