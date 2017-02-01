import java.util.HashMap;

public class cpLottery{

    private int TOTPEOPLE, TOTACCEPT, DENOM;
    private int ITERATIONS = 1000;

    private HashMap allGroups = new HashMap<Integer, Integer>();

    /**
     * Constructor builds number of people in lottery, number to be accepted,
     * group sizes, and denominator for final percentage calculation
     * @param totPeople
     * @param totAccept
     * @param friendSize
     */

    public cpLottery(int totPeople, int totAccept, int friendSize){
        TOTPEOPLE = totPeople;
        TOTACCEPT = totAccept;
        DENOM = friendSize*ITERATIONS;

        int otherTracker = 0;
        int groupSize;

        //Build map for other groups
        while(otherTracker < 88){
            groupSize = (int) (8*Math.random()) + 1;
            System.out.println(groupSize);

            int otherID = (int)(100*Math.random()) + 1;

            //Ensure unique ID # for each group
            while(allGroups.containsKey(otherID)){
                otherID = (int)(100*Math.random()) + 1;
            }

            //Add group to Map
            allGroups.put(otherID, groupSize);

            otherTracker += groupSize;
        }

        System.out.println("The set of group numbers is " + allGroups.keySet());
        System.out.println("The set of group sizes is " + allGroups.values());
        System.out.println("The total number of groups is " + allGroups.size());




    }

    public void indivStyle(){

    }

    public void groupStyle(){

    }

    public static void main(String[] args){
        int allPeople = 100;
        int acceptedPeople = 74;
        int friends = 12;

        cpLottery ourLot = new cpLottery(allPeople, acceptedPeople, friends);

    }

}
