package utils;

public class MathMagic {
    // https://www.geeksforgeeks.org/shuffle-a-given-array-using-fisher-yates-shuffle-algorithm/
    // https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
    // Returns array of random index-s
    // Collections.shuffle();
    public static int[] getShuffleIndexes(int numElements) {
        int[] resultArr = new int[numElements];

        for (int i = 0; i < numElements; i++) {
            resultArr[i] = i;
        }

        int randomIndex = 0, tmp = 0;
        for (int i = numElements - 1; i >= 0; i--) {
            randomIndex = (int) (Math.random() * numElements);
            tmp = resultArr[randomIndex];
            resultArr[randomIndex] = resultArr[i];
            resultArr[i] = tmp;
        }

        return resultArr;
    }

    public static double calculateEuclideanDistance(double[] firstObservation, double[] secondObservation) {
        double resultSum = 0.0;

        for (int i = 0; i < firstObservation.length; i++) {
            double sumPower = Math.pow(firstObservation[i] - secondObservation[i], 2); // will be always positive value
            resultSum += sumPower;
        }

        return Math.sqrt(resultSum);
    }

    public static double cityDistance(double[] firstObservation, double[] secondObservation) {
        double resultSum = 0.0;

        for (int i = 0; i < firstObservation.length; i++) {
            double sumAbs = Math.abs(firstObservation[i] - secondObservation[i]);
            resultSum += sumAbs;
        }

        return resultSum;
    }
}
