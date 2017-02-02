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
            //groupSize = 1;
            //System.out.println(groupSize);

            int otherID = (int)(125*Math.random()) + 1;

            //Ensure unique ID # for each group
            while(allGroups.containsKey(otherID)){
                //System.out.println(otherID);
                otherID = (int)(125*Math.random()) + 1;
            }

            //Add group to Map
            allGroups.put(otherID, groupSize);

            otherTracker += groupSize;
           // System.out.println(otherTracker);

        }
       // TOTPEOPLE = otherTracker + FRIENDSIZE;

        /*
        System.out.println("The set of group numbers is " + allGroups.keySet());
        System.out.println("The set of group sizes is " + allGroups.values());
        System.out.println("The total number of groups is " + allGroups.size());
        */

    }

    public void indivStyle(){
        HashMap indivFriends = new HashMap<Integer, Integer>(FRIENDSIZE);

        for(int indivCounter = 0; indivCounter < 12; indivCounter++){

            int friendID = (int)(125*Math.random()) + 1;

            //Ensure unique ID # for each group
            while(indivFriends.containsKey(friendID) || allGroups.containsKey(friendID)){
                //System.out.println(friendID);
                friendID = (int)(125*Math.random()) + 1;

            }

            //Add group to Map
            indivFriends.put(friendID, 1);
        }

        /*
        System.out.println("The set of indiv numbers is " + indivFriends.keySet());
        System.out.println("The set of indiv sizes is " + indivFriends.values());
        System.out.println("The total number of my friends is " + indivFriends.size());
        */

        //Get total number of friends selected
        int acceptedFriends = lottoProcess(indivFriends, allGroups);
        double acceptFriends = (double) acceptedFriends;

        System.out.println("The percentage of friends accepted when we all go single is: " + (acceptFriends/DENOM)*100 + "%");


    }

    public void groupStyle(int myGroupSize){
        int numGroups = FRIENDSIZE/myGroupSize;
        //System.out.println(numGroups);

        HashMap groupFriends = new HashMap<Integer, Integer>(numGroups);

        for(int groupCounter = 0; groupCounter < numGroups; groupCounter++){

            int friendID = (int)(125*Math.random()) + 1;

            //Ensure unique ID # for each group
            while(groupFriends.containsKey(friendID) || allGroups.containsKey(friendID)){
                //System.out.println(friendID);
                friendID = (int)(125*Math.random()) + 1;

            }

            //Add group to Map
            groupFriends.put(friendID, myGroupSize);
        }

        /*
        System.out.println("The set of friend group numbers is " + groupFriends.keySet());
        System.out.println("The set of friend group sizes is " + groupFriends.values());
        System.out.println("The total number of friend groups is " + groupFriends.size());
        */

        //Get total number of friends selected
        int acceptedFriends = lottoProcess(groupFriends, allGroups);
        double acceptFriends = (double) acceptedFriends;

        System.out.println("The percentage of friends accepted for groups of " + myGroupSize + " is: " + (acceptFriends/DENOM)*100 + "%");

    }

    /**
     * Method simulates lottery selection process based upon numbers selected
     * Returns number of friends who get in over the course of all iterations
     * @param friends
     * @param others
     */
    public int lottoProcess(HashMap<Integer, Integer> friends, HashMap<Integer,Integer> others){
        //Map to hold lottery numbers
        HashMap<Integer, Integer> lottoNums = new HashMap<Integer, Integer>();

    /*    System.out.println("The set of indiv numbers is " + friends.keySet());
        System.out.println("The set of indiv sizes is " + friends.values());
        System.out.println("The total number of my friends is " + friends.size());

        System.out.println("The set of group numbers is " + others.keySet());
        System.out.println("The set of group sizes is " + others.values());
        System.out.println("The total number of groups is " + others.size()); */

        int grandTotal = 0;
        int peopleIn = 0;
        int friendsIn = 0;
        int perfectScores = 0;

        //Iterate through selected number of times
        for(int i = 0; i < ITERATIONS; i++) {

            lottoNums.clear();
            peopleIn = 0;
            friendsIn = 0;

            while (peopleIn < TOTACCEPT) {
                int selectID = (int) (125*Math.random()) + 1;

                //Weed out numbers not in either friends or others or numbers already chosen
                while ((!friends.containsKey(selectID) && !others.containsKey(selectID)) || lottoNums.containsKey(selectID)) {
                    selectID = (int) (125*Math.random()) + 1;
                }

                int currentSize = 0;
                //Determine which group got number, add group size to totals
                if (friends.containsKey(selectID)) {
                    currentSize = friends.get(selectID);
                    friendsIn += currentSize;
                   // System.out.println("The number of friends accepted is now: " + friendsIn);
                    peopleIn += currentSize;
                    lottoNums.put(selectID, currentSize);
                } else if (others.containsKey(selectID)) {
                    currentSize = others.get(selectID);
                    peopleIn += currentSize;
                    lottoNums.put(selectID, currentSize);
                }


                //System.out.println("Total accepted is " + peopleIn);
            }

            if(friendsIn == 12){
                perfectScores++;
            }

            grandTotal += friendsIn;
           // System.out.println("Grand total of friends accepted is " + grandTotal);
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
