package li.chris;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by Chris on 2017-10-07.
 */
public class Transformers {

    /**
     * Data for each Transformer will be read line by line as they are inputted. Once there is a
     * new line that does not contain any content (by just pressing ENTER again without inputting
     * anything) the battleTransformers() method is called and the desired output will be printed
     * to console.
     */
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        List<Transformer> transformers = new ArrayList<>();

        boolean shouldBattle = false;
        while (!shouldBattle) {
            String line = in.nextLine();
            if (!line.isEmpty()) {
                String[] attributes = line.split(",");
                if (attributes.length == 10) {
                    int[] techSpecs = new int[8];
                    for (int i = 2; i < attributes.length; i++) {
                        techSpecs[i - 2] = Integer.parseInt(attributes[i].trim());
                    }
                    Transformer transformer = new Transformer(attributes[0].trim(), attributes[1].trim(), techSpecs);
                    transformers.add(transformer);
                } else {
                    System.out.println("Invalid number of parameters.");
                }
            } else {
                shouldBattle = true;
            }
        }

        System.out.println("\n");
        battleTransformers(transformers);
    }

    public static void battleTransformers(List<Transformer> transformers) {

        List<Transformer> autobots = new ArrayList<>();
        List<Transformer> decepticons = new ArrayList<>();

        for (Transformer transformer : transformers) {
            if (transformer.getTeam().equals(Transformer.AUTOBOT_TEAM)) {
                autobots.add(transformer);
            } else if (transformer.getTeam().equals(Transformer.DECEPTICON_TEAM)) {
                decepticons.add(transformer);
            }
        }

        // Based on the example, we will sort the teams in descending rank order.
        Collections.sort(autobots, Collections.reverseOrder());
        Collections.sort(decepticons, Collections.reverseOrder());

        int rounds = Math.min(autobots.size(), decepticons.size());
        int battleCount = 0;
        int autobotWinCount = 0;
        int decepticonWinCount = 0;

        for (int round = 0; round < rounds; round++) {
            battleCount++;
            Transformer autobot = autobots.get(round);
            Transformer decepticon = decepticons.get(round);

            if (autobot.getName().equals(Transformer.OPTIMUS_PRIME_NAME) || autobot.getName().equals(Transformer.PREDAKING_NAME)) {
                if (!decepticon.getName().equals(Transformer.OPTIMUS_PRIME_NAME) && !decepticon.getName().equals(Transformer.PREDAKING_NAME)) {
                    autobotWinCount++;
                    decepticon.setDefeated(true);
                    continue;
                } else {
                    break;
                }
            } else if (decepticon.getName().equals(Transformer.OPTIMUS_PRIME_NAME) || decepticon.getName().equals(Transformer.PREDAKING_NAME)) {
                if (!autobot.getName().equals(Transformer.OPTIMUS_PRIME_NAME) && !autobot.getName().equals(Transformer.PREDAKING_NAME)) {
                    decepticonWinCount++;
                    autobot.setDefeated(true);
                    continue;
                } else {
                    break;
                }
            }

            int courageDifference = autobot.getCourage() - decepticon.getCourage();
            int strengthDifference = autobot.getStrength() - decepticon.getStrength();

            if (courageDifference >= 4 && strengthDifference >= 3) {
                autobotWinCount++;
                decepticon.setDefeated(true);
                continue;
            } else if (courageDifference <= -4 && strengthDifference <= -3) {
                decepticonWinCount++;
                autobot.setDefeated(true);
                continue;
            }

            int skillDifference = autobot.getSkill() - decepticon.getSkill();
            if (skillDifference >= 3) {
                autobotWinCount++;
                decepticon.setDefeated(true);
                continue;
            } else if (skillDifference <= -3) {
                decepticonWinCount++;
                autobot.setDefeated(true);
                continue;
            }

            if (autobot.getOverallRating() > decepticon.getOverallRating()) {
                autobotWinCount++;
                decepticon.setDefeated(true);
            } else if (autobot.getOverallRating() < decepticon.getOverallRating()) {
                decepticonWinCount++;
                autobot.setDefeated(true);
            } else {
                autobot.setDefeated(true);
                decepticon.setDefeated(true);
            }
        }

        if (battleCount == 1) {
            System.out.printf("%d battle\n", battleCount);
        } else {
            System.out.printf("%d battles\n", battleCount);
        }

        String winningTeamPrompt;
        String losingTeamPrompt;

        List<Transformer> winningTeam;
        List<Transformer> losingTeam;

        int winDifference = autobotWinCount - decepticonWinCount;
        if (winDifference > 0) {
            winningTeamPrompt = String.format("Winning team (%s):", "Autobots");
            winningTeam = autobots;
            losingTeamPrompt = String.format("Survivors from the losing team (%s):", "Decepticons");
            losingTeam = decepticons.stream()
                    .filter(transformer -> !transformer.isDefeated())
                    .collect(Collectors.toList());
        } else if (winDifference < 0) {
            winningTeamPrompt = String.format("Winning team (%s):", "Decepticons");
            winningTeam = decepticons;
            losingTeamPrompt = String.format("Survivors from the losing team (%s):", "Autobots");
            losingTeam = autobots.stream()
                    .filter(transformer -> !transformer.isDefeated())
                    .collect(Collectors.toList());
        } else {
            System.out.println("No winners");
            return;
        }

        for (int i = 0; i < winningTeam.size(); i++) {
            Transformer transformer = winningTeam.get(i);
            if (i == winningTeam.size() - 1) {
                winningTeamPrompt += " " + transformer.getName();
            } else {
                winningTeamPrompt += " " + transformer.getName() + ",";
            }
        }

        for (int i = 0; i < losingTeam.size(); i++) {
            Transformer transformer = losingTeam.get(i);
            if (i == losingTeam.size() - 1) {
                losingTeamPrompt += " " + transformer.getName();
            } else {
                losingTeamPrompt += " " + transformer.getName() + ",";
            }
        }

        System.out.println(winningTeamPrompt);
        System.out.println(losingTeamPrompt);
    }

    public static class Transformer implements Comparable<Transformer> {

        public static final String AUTOBOT_TEAM = "A";
        public static final String DECEPTICON_TEAM = "D";

        public static final String OPTIMUS_PRIME_NAME = "Optimus Prime";
        public static final String PREDAKING_NAME = "Predaking";

        private String name;
        private String team;
        private int[] techSpecs;
        private int overallRating;

        private boolean isDefeated = false;

        /**
         * @param name the name of the Transformer.
         * @param team a String representing the team the Transformer is on, "A" or "D"
         * @param techSpecs an array of Integers representing the Transformer's criteria ratings,
         *                  ordered from indexes 0 to 7, like so: Strength, Intelligence, Speed,
         *                  Endurance, Rank, Courage, Firepower and Skill.
         */
        public Transformer(String name, String team, int[] techSpecs) {
            this.name = name;
            this.team = team;
            this.techSpecs = techSpecs;
            setOverallRating();
        }

        public int getStrength() {
            return techSpecs[0];
        }

        public int getIntelligence() {
            return techSpecs[1];
        }

        public int getSpeed() {
            return techSpecs[2];
        }

        public int getEndurance() {
            return techSpecs[3];
        }

        public int getRank() {
            return techSpecs[4];
        }

        public int getCourage() {
            return techSpecs[5];
        }

        public int getFirepower() {
            return techSpecs[6];
        }

        public int getSkill() {
            return techSpecs[7];
        }

        private void setOverallRating() {
            this.overallRating = getStrength() + getIntelligence() + getSpeed() + getEndurance() + getFirepower();
        }

        public int getOverallRating() {
            return overallRating;
        }

        public String getName() {
            return name;
        }

        public String getTeam() {
            return team;
        }

        public int[] getTechSpecs() {
            return techSpecs;
        }

        public boolean isDefeated() {
            return isDefeated;
        }

        public void setDefeated(boolean defeated) {
            isDefeated = defeated;
        }

        @Override
        public int compareTo(Transformer transformer) {
            if (this.getRank() > transformer.getRank()) {
                return 1;
            } else if (this.getRank() < transformer.getRank()) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
