package kursinisShowcase;

import java.util.Random;
import kursinis.UnrolledLinkedList;
import kursinis.UnrolledLinkedListADT;
import kursinisShowcase.Passanger;

/**
 * A train management system using an unrolled linked list Passengers can be
 * inserted into the train, either anywhere or in a specific place No train
 * segments shall be kept full to increase passenger comfort The segment count
 * of the train can be used as an indicator of how many segments a train should
 * have for a specific amount of passengers.
 *
 * @author audri
 */
public class TrainManagementSystem {

    private UnrolledLinkedListADT<Passanger> passangers;

    public TrainManagementSystem(int segmentSize) {
        passangers = new UnrolledLinkedList<>(segmentSize);
    }

    public static void main(String[] args) {
        TrainManagementSystem system = new TrainManagementSystem(5);
        int numOfPassangers = 320;
        system.AddPassangersRandomly(numOfPassangers);

        System.out.println("Number of passangers: " + numOfPassangers);
        System.out.println("Estimated wagon count for this number of passangers: " + system.GetWagonCountEvaluation());
        System.out.println("Passanger distribution in the train: ");
        system.PrintPassangerDistribution();
    }

    public void AddPassangersRandomly(int numOfPassangers) {
        Random rd = new Random();
        for (int i = 0; i < numOfPassangers; ++i) {
            int indexBound = passangers.size() + 1;
            passangers.add(rd.nextInt(indexBound), Passanger.GenerateRandom());
        }
    }

    public int GetWagonCountEvaluation() {
        return passangers.getNodeCount();
    }

    private void PrintPassangerDistribution() {
        passangers.printElements();
    }
}
