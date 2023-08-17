package com.pohorilyi;

public class AngryFrogs {

    public int bruteForceSolution(int[] blocks) {
        if (blocks.length <= 2) {
            return blocks.length;
        }
        int maxDistance = 0;
        for (int i = 0; i < blocks.length; i++) {
            int maxDistanceFromIBlock = 1;
            //go right
            for (int r = i + 1; r < blocks.length; r++) {
                if (blocks[r] >= blocks[r - 1]) {
                    maxDistanceFromIBlock++;
                } else {
                    break;
                }
            }
            //go left
            for (int l = i - 1; l >= 0; l--) {
                if (blocks[l] >= blocks[l + 1]) {
                    maxDistanceFromIBlock++;
                } else {
                    break;
                }
            }
            maxDistance = Math.max(maxDistance, maxDistanceFromIBlock);
        }
        return maxDistance;
    }

    public int stepsSolution(int[] blocks) {
        if (blocks.length <= 2) {
            return blocks.length;
        }
        int currentMaxDistance = 1;
        int maxDistance = 1;
        int prevNonFlatStep = 0;
        int previousFlatStepsCount = 0;

        for (int i = 1; i < blocks.length; i++) {
            int step = blocks[i] - blocks[i - 1];
            //if direction changes from up to down - reset max distance and add all previous flat steps
            if (prevNonFlatStep > 0 && step < 0) {
                currentMaxDistance = 2 + previousFlatStepsCount;
            } else {
                currentMaxDistance++;
            }
            maxDistance = Math.max(maxDistance, currentMaxDistance);
            if (step != 0) {
                prevNonFlatStep = step;
                previousFlatStepsCount = 0;
            } else {
                previousFlatStepsCount++;
            }
        }
        return maxDistance;
    }
}
