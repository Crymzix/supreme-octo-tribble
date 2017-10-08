package li.chris;

/**
 * Created by Chris on 2017-10-07.
 */
public class Castles {

    public static int buildCastles(int[] landHeights) {

        if (landHeights == null || landHeights.length == 0) {
            return 0;
        }

        // Non-empty arrays will have at least 1 castle
        int castleCount = 1;

        for (int i = 1 ; i < landHeights.length; i++) {
            int previousIndex = i - 1;
            int nextIndex = i + 1;

            if (previousIndex >= 0 && nextIndex < landHeights.length) {
                int currentHeight = landHeights[i];
                int previousHeight = landHeights[previousIndex];
                int nextHeight = landHeights[nextIndex];

                // Repeat case. Iterate through the repeats.
                if (currentHeight == nextHeight) {
                    for (int j = i; j < landHeights.length; j++) {
                        currentHeight = landHeights[j];
                        nextIndex = j + 1;

                        if (nextIndex < landHeights.length) {
                            nextHeight = landHeights[nextIndex];
                        }

                        if (currentHeight != nextHeight) {
                            i = j;
                            break;
                        }
                    }
                }

                // Peak case.
                if (currentHeight > previousHeight && currentHeight > nextHeight) {
                    castleCount++;
                }

                // Valley case.
                if (currentHeight < previousHeight && currentHeight < nextHeight) {
                    castleCount++;
                }
            }
        }

        return castleCount;
    }
}
