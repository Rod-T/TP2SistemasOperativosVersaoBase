import java.util.ArrayList;

/** @author - Rodrigo Tavares
 * @docente - Aníbal Ponte
 * */

public class Main {

    public static void main(String[] args) {
        /*String file = args[0];
        int threads = Integer.parseInt(args[1]);
        int maxTime = Integer.parseInt(args[2]);*/

        String file = "prob08_3.txt";
        int threads = 4;
        int maxTime = 2000;

        Parameters params = Parameters.readfile(file);
        MyThread.maxTime = maxTime;

        System.out.println("Problem = " + file + " Number threads = " + threads + " Time = " + 10);
        System.out.println(params);

        try {
            for (int i = 0; i < 10; i++) {
                SavedData data = new SavedData(params);
                MyThread.data = data;
                ArrayList<MyThread> myThreads = new ArrayList<>();

                for (int j = 0; j < threads; j++) {
                    myThreads.add(new MyThread(params));
                    myThreads.get(j).start();
                }

                for (int j = 0; j < threads; j++) {
                    myThreads.get(j).join();
                }

                SavedData newData = data.get();
                System.out.println("------------------------------------- Test " + (i + 1) + " -----------------------------------");
                System.out.println(newData.bestIndividual.getPlateList());
                System.out.println("Eval = " + newData.bestIndividual.getFinalEval() + " Best Iteration = " + newData.bestIteration + " Best Time = " + (newData.bestTime/1000.0));
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
