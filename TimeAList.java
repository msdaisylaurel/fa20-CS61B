import java.util.ArrayList;
import java.util.List;

/**
 * Class that collects timing information about AList construction.
 */
public class TimeAList {
    private static void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        ArrayList listOfNumberOfIndexes = new ArrayList();
        ArrayList listOfTimeTaken = new ArrayList();
        AList test = new AList<>();
        int by1K = 1000;

        for (int j = 0; j <= 7; j++) {
            listOfNumberOfIndexes.add(j, by1K);
            by1K *= 2;
        }

        for (int j = 0; j != listOfNumberOfIndexes.size(); j++) {
            Stopwatch sw = new Stopwatch();
            for (int i = 0; i != (int)listOfNumberOfIndexes.get(j); i++) {
                test.addLast(i);
            }
            double timeTaken = sw.elapsedTime();
            listOfTimeTaken.add(timeTaken);
        }
        printTimingTable(listOfNumberOfIndexes, listOfTimeTaken, listOfNumberOfIndexes);
    }


}
