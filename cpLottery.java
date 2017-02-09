import java.util.HashMap;

public class cpLottery{

    private int TOTPEOPLE, TOTACCEPT, FRIENDSIZE;
    private double DENOM;
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
        while(otherTracker < (TOTPEOPLE - FRIENDSIZE)){
            groupSize = (int) (8*Math.random()) + 1;
            int otherID = (int)(125*Math.random()) + 1;

            //Ensure unique ID # for each group
            while(allGroups.containsKey(otherID)){
                otherID = (int)(125*Math.random()) + 1;
            }

            //Add group to Map
            allGroups.put(otherID, groupSize);

            otherTracker += groupSize;

        }

    }

    /**
     * Parameter-less method to calculate percentage chance of receiving
     * housing if all 12 friends apply as individuals
     */
    public void indivStyle(){
        HashMap indivFriends = new HashMap<Integer, Integer>(FRIENDSIZE);

        for(int indivCounter = 0; indivCounter < 12; indivCounter++){

            int friendID = (int)(125*Math.random()) + 1;

            //Ensure unique ID # for each group
            while(indivFriends.containsKey(friendID) || allGroups.containsKey(friendID)){
                friendID = (int)(125*Math.random()) + 1;
            }

            //Add group to Map
            indivFriends.put(friendID, 1);
        }

        //Get total number of friends selected
        int acceptedFriends = lottoProcess(indivFriends, allGroups);
        double acceptFriends = (double) acceptedFriends;

        System.out.println("The percentage of friends accepted when we all go single is: " + (acceptFriends/DENOM)*100 + "%");


    }

    /**
     * Method to determine percentage chance of individual receiving housing
     * if he or she applies as part of a group of either 4 or 6
     * @param myGroupSize
     */
    public void groupStyle(int myGroupSize){
        int numGroups = FRIENDSIZE/myGroupSize;

        HashMap groupFriends = new HashMap<Integer, Integer>(numGroups);

        for(int groupCounter = 0; groupCounter < numGroups; groupCounter++){

            int friendID = (int)(125*Math.random()) + 1;

            //Ensure unique ID # for each group
            while(groupFriends.containsKey(friendID) || allGroups.containsKey(friendID)){
                friendID = (int)(125*Math.random()) + 1;
            }

            //Add group to Map
            groupFriends.put(friendID, myGroupSize);
        }

        //Get total number of friends selected
        int acceptedFriends = lottoProcess(groupFriends, allGroups);
        double acceptFriends = (double) acceptedFriends;

        System.out.println("The percentage of friends accepted for groups of " + myGroupSize + " is: " + (acceptFriends/DENOM)*100 + "%");

    }

    /**
     * Method simulates lottery selection process based upon numbers selected
     * Returns number of friends who get in over the course of all iterations.
     * Returns total number of friends who get housing over all simulations
     * @param friends
     * @param others
     */
    public int lottoProcess(HashMap<Integer, Integer> friends, HashMap<Integer,Integer> others){
        //Map to hold lottery numbers
        HashMap<Integer, Integer> lottoNums = new HashMap<Integer, Integer>();

        int grandTotal = 0;
        int peopleIn = 0;
        int friendsIn = 0;
        int perfectScores = 0;

        //Run specified number of lottery simulations
        for(int i = 0; i < ITERATIONS; i++) {

            lottoNums.clear();
            peopleIn = 0;
            friendsIn = 0;

            //Make lottery selections until 74 people have received housing
            while (peopleIn < TOTACCEPT) {
                int selectID = (int) (125*Math.random()) + 1;

                //Weed out numbers not in either friends or others or numbers already chosen
                while ((!friends.containsKey(selectID) && !others.containsKey(selectID)) || lottoNums.containsKey(selectID)) {
                    selectID = (int) (125*Math.random()) + 1;
                }

                int currentSize = 0;
                //Determine which group got number, add group size to totals, ensure number can't be chosen again
                if (friends.containsKey(selectID)) {
                    currentSize = friends.get(selectID);
                    friendsIn += currentSize;
                    peopleIn += currentSize;
                    lottoNums.put(selectID, currentSize);
                } else if (others.containsKey(selectID)) {
                    currentSize = others.get(selectID);
                    peopleIn += currentSize;
                    lottoNums.put(selectID, currentSize);
                }
            }

            //If all 12 friends get housing, increment perfect round counter
            if(friendsIn == 12){
                perfectScores++;
            }

            grandTotal += friendsIn;
        }

        System.out.println("The number of perfect rounds out of " + ITERATIONS + " is " + perfectScores);

        return grandTotal;

    }

    public static void main(String[] args){
        int allPeople = 125;
        int acceptedPeople = 74;
        int friends = 12;

        char groupSize = args[0].charAt(0);
        int groupSizeInt = (int)(groupSize) - 48;
        System.out.println("The input group size is " + groupSizeInt);
        System.out.println("Running " + 1000 + " simulations to ensure accurarcy...");

        cpLottery ourLot = new cpLottery(allPeople, acceptedPeople, friends);
        ourLot.indivStyle();
        ourLot.groupStyle(groupSizeInt);


    }

}
