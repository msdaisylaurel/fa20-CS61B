import java.util.ArrayList;
import java.util.List;

/**
 * Class that collects timing information about SLList getLast method.
 */
public class TimeSLList {
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
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE

        ArrayList listOfNumberOfIndexes = new ArrayList();
        ArrayList listOfTimeTaken = new ArrayList();
        ArrayList listOfNumberOfOps = new ArrayList();
        SLList test = new SLList<>();
        int by1K = 1000;
        int ops = 10000;

        for (int j = 0; j <= 6; j++) {
            listOfNumberOfIndexes.add(j, by1K);
            by1K *= 2;
        }

        for (int j = 0; j != listOfNumberOfIndexes.size(); j++) {
            for (int i = 0; i != (int)listOfNumberOfIndexes.get(j); i++) {
                test.addLast(i);
            }
            Stopwatch sw = new Stopwatch();
            for (int i = 0; i != ops; i++) {
                test.getLast();
            }
            double timeTaken = sw.elapsedTime();
            listOfTimeTaken.add(timeTaken);
            listOfNumberOfOps.add(ops);
        }
        printTimingTable(listOfNumberOfIndexes, listOfTimeTaken, listOfNumberOfOps);
    }
}
