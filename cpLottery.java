import java.util.HashMap;

public class cpLottery{

    private int TOTPEOPLE, TOTACCEPT, DENOM, FRIENDSIZE;
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
        FRIENDSIZE = friendSize;
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
        HashMap indivFriends = new HashMap<Integer, Integer>(FRIENDSIZE);

        for(int indivCounter = 0; indivCounter < 12; indivCounter++){

            int friendID = (int)(100*Math.random()) + 1;

            //Ensure unique ID # for each group
            while(indivFriends.containsKey(friendID) || allGroups.containsKey(friendID)){
                System.out.println(friendID);
                friendID = (int)(100*Math.random()) + 1;

            }

            //Add group to Map
            indivFriends.put(friendID, 1);
        }

        System.out.println("The set of indiv numbers is " + indivFriends.keySet());
        System.out.println("The set of indiv sizes is " + indivFriends.values());
        System.out.println("The total number of my friends is " + indivFriends.size());


    }

    public void groupStyle(){

    }

    public void lottoProcess(HashMap<Integer, Integer> friends, HashMap<Integer,Integer> others){

    }

    public static void main(String[] args){
        int allPeople = 100;
        int acceptedPeople = 74;
        int friends = 12;

        cpLottery ourLot = new cpLottery(allPeople, acceptedPeople, friends);
        ourLot.indivStyle();

    }

}
