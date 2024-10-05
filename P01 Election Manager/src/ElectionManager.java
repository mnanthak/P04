//////////////// FILE HEADER //////////////////////////
//
// Title: The Election Manager Program uses the methods defined in this class to manipulate
//////////////// electronic ballots
// Course: CS 300 Fall 2024
//
// Author: Mohnish Nanthakumar
// Email: mnanthakumar@wisc.edu
// Lecturer: Hobbes LeGault
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
// https://www.w3schools.com/java/java_try_catch.asp - taught me exception handling, which is used
//////////////// in the containsCandidate method in ElectionManager.java
// https://www.geeksforgeeks.org/java-util-arrays-deepequals-java/ - taught me about the
//////////////// Arrays.deepEquals() method, and helped me better understand the pre-written
//////////////// tester methods in ElectionManagerTester,java
// https://www.geeksforgeeks.org/arrays-copyof-in-java-with-examples/ - taught the Arrays.copyOf()
//////////////// method, which was used in my alphaSortArray method in ElectionManager.java
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

/**
 * This class models an electronic ballot for a presidential election
 */
public class ElectionManager {
  /**
   * Determines whether the given candidate, specified uniquely by name and party, is present in the
   * given list of candidates.
   * 
   * @param candidates    a 2D String array representing the list of candidates on the ballot
   * @param numCandidates an int representing the number of candidates on the ballot
   * @param name          a String representing the candidate's name
   * @param party         a String representing the candidate's party
   * @return true if the candidate is in the array, false otherwise
   */
  public static boolean containsCandidate(String[][] candidates, int numCandidates, String name,
      String party) {
    // loop through the list of non-null candidates and tries to find one with the matching name
    // and party
    for (int i = 0; i < numCandidates; i++) {
      if (candidates[i][0].equals(name) && candidates[i][1].equals(party)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Adds a candidate with the given name, party affiliation, and vote count to the given list of
   * candidates, maintaining the candidate list in alphabetical order by NAME, and returns the new
   * total number of candidates in the array.
   * 
   * @param candidates    a 2D String array representing the list of candidates on the ballot
   * @param numCandidates an int representing the number of candidates on the ballot
   * @param name          a String representing the candidate's name
   * @param party         a String representing the candidate's party
   * @param numVotes      an int representing the candidate's number of votes
   * @return the number of candidates on ballot after adding the candidate
   */
  public static int addCandidate(String[][] candidates, int numCandidates, String name,
      String party, int numVotes) {
    // group candidate details into a String array for ease of copying into an array
    String[] newCandidate = {name, party, Integer.toString(numVotes)};
    // if candidate already exists in list, don't add
    if (containsCandidate(candidates, numCandidates, name, party)) {
      return numCandidates;
    }
    // if candidate has a negative amount of votes, don't add
    if (numVotes < 0) {
      return numCandidates;
    }
    // if there is still space in the candidates list to add another candidate, add
    if (numCandidates < candidates.length) {
      // give next available spot in list to the newly added candidate
      candidates[numCandidates] = newCandidate;
      numCandidates++;
    }
    // sort the candidates list alphabetically
    alphaSortArray(candidates, numCandidates);

    return numCandidates;
  }

  /**
   * Removes the candidate specified uniquely by name and party from the given array of candidates,
   * maintaining the candidates array in alphabetical order by name.
   * 
   * @param candidates    a 2D String array representing the list of candidates on the ballot
   * @param numCandidates an int representing the number of candidates on the ballot
   * @param name          a String representing the candidate's name
   * @param party         a String representing the candidate's party
   * @return the number of candidates on ballot after removing the candidate
   */
  public static int dropCandidate(String[][] candidates, int numCandidates, String name,
      String party) {
    // if the candidate to drop is not in the list, don't drop, and return the current number
    // candidates
    if (!containsCandidate(candidates, numCandidates, name, party)) {
      return numCandidates;
    }
    // loop through the list of non-null candidates and set the desired candidate's details to null
    for (int i = 0; i < numCandidates; i++) {
      if ((candidates[i][0].equals(name)) && (candidates[i][1].equals(party))) {
        candidates[i] = null;
        numCandidates--;
      }
    }
    // push the nulls to the back of the array by creating a copy array, rearranging it, then
    // copying its contents to the original candidates list
    String[][] copyOfCandidates = new String[candidates.length][3];
    // by using a count variable, we are able to adjust the contents of the copy array at a rate
    // different than the original's
    int count = 0;
    for (int i = 0; i < candidates.length; i++) {
      // if there is a candidate at the index i, copy it to the copy array
      if (candidates[i] != null) {
        copyOfCandidates[count] = candidates[i];
        count++;
      }
    }
    // for all the null String arrays, change them them from {null, null, null} to null
    for (int i = numCandidates; i < candidates.length; i++) {
      copyOfCandidates[i] = null;
    }
    // re-copying the contents of the copy array back to the original
    for (int i = 0; i < copyOfCandidates.length; i++) {
      candidates[i] = copyOfCandidates[i];
    }
    return numCandidates;
  }

  /**
   * Finds the candidate with the majority (that is, >50%) of total votes cast.
   * 
   * @param candidates    a 2D String array representing the list of candidates on the ballot
   * @param numCandidates an int representing the number of candidates on the ballot
   * @return a String message containing the candidate's ballot details, or "CONTINGENT" if none of
   *         the candidates get the majority
   */
  public static String findWinner(String[][] candidates, int numCandidates) {
    // loop through the list of candidates to find the one with >50% of the vote
    for (int i = 0; i < numCandidates; i++) {
      int candidateVotes = Integer.valueOf(candidates[i][2]);
      double percentOfVotes = 100 * candidateVotes / calculateTotalVotes(candidates, numCandidates);
      if (percentOfVotes > 50) {
        return candidates[i][0] + " (" + candidates[i][1] + ") - " + percentOfVotes + "%";
      }
    }
    // if none of the candidates win the majority, the election ends up contingent
    return "CONTINGENT";
  }

  /**
   * Finds the candidate with the smallest number of votes cast.
   * 
   * @param candidates    a 2D String array representing the list of candidates on the ballot
   * @param numCandidates an int representing the number of candidates on the ballot
   * @return a String message containing the candidate's ballot details, or "UNCONTESTED" if there
   *         is only one candidate
   */
  public static String findLowestPollingCandidate(String[][] candidates, int numCandidates) {
    // if there are 0 - 1 candidates(s), the losing poll is uncontested
    if (numCandidates < 2) {
      return "UNCONTESTED";
    }
    // find the losing (minimum) number of votes
    int lowestVotes = (int) (calculateTotalVotes(candidates, numCandidates));
    for (int i = 0; i < numCandidates; i++) {
      int candidateVotes = Integer.valueOf(candidates[i][2]);
      // only change the lowest votes number if there is a lower amount of votes
      if (candidateVotes <= lowestVotes) {
        lowestVotes = candidateVotes;
      }
    }
    // find out how many candidates have the least amount of votes. We use a losingCandidatesCount
    // variable to keep track of the number of candidates on the ballot with the least amount of
    // votes
    int losingCandidatesCount = 0;
    for (int i = 0; i < numCandidates; i++) {
      int candidateVotes = Integer.valueOf(candidates[i][2]);
      // if a candidate has the least amount of votes, increase the losingCandidatesCount variable
      if (candidateVotes == lowestVotes) {
        losingCandidatesCount++;
      }
    }
    // create an array of the losing candidates' details to later use to print the ultimate loser's
    // details
    String[][] losingCandidates = new String[losingCandidatesCount][3];
    // if two or more candidates somehow got the same amount of losing votes, add their details to
    // the losingCandidates 2D String array
    if (losingCandidatesCount > 1) {
      // we use a count variable to keep track of the index of losingCandidates that we need to
      // insert our losing candidate's details into
      int count = 0;
      for (int i = 0; i < numCandidates; i++) {
        int candidateVotes = Integer.valueOf(candidates[i][2]);
        if (candidateVotes == lowestVotes) {
          losingCandidates[count] = candidates[i];
        }
        count++;
      }
      // sort the array alphabetically
      alphaSortArray(losingCandidates, losingCandidatesCount);

    }
    // if there is only one losing candidate at the bottom of the ballot, loop through the
    // candidates list, find the candidate, and copy its details to losingCandidates
    else {
      for (int i = 0; i < numCandidates; i++) {
        int candidateVotes = Integer.valueOf(candidates[i][2]);
        // if the candidate has the least amount of votes, copy its details to losingCandidates and
        // then break out of the loop
        if (candidateVotes == lowestVotes) {
          losingCandidates[0] = candidates[i];
          break;
        }
      }
    }
    // return the details of the lowest polling candidate in a String
    return losingCandidates[0][0] + " (" + losingCandidates[0][1] + ") - " + losingCandidates[0][2];
  }

  /**
   * Calculate the total number of votes cast on the ballot
   * 
   * @param candidates    a 2D String array representing the list of candidates on the ballot
   * @param numCandidates an int representing the number of candidates on the ballot
   * @return a double representing the total number of votes cast on the ballot
   */
  private static double calculateTotalVotes(String[][] candidates, int numCandidates) {
    // for every candidate in candidate list, sum up all of their votes
    double totalVotes = 0;
    for (int i = 0; i < numCandidates; i++) {
      int candidateVotes = Integer.valueOf(candidates[i][2]);
      totalVotes += candidateVotes;
    }
    return totalVotes;
  }

  /**
   * Sorts a provided 2D String array alphabetically by the names of the candidates on the ballot
   * 
   * @param candidates    a 2D String array representing the list of candidates on the ballot
   * @param numCandidates an int representing the number of candidates on the ballot
   */
  private static void alphaSortArray(String[][] candidates, int numCandidates) {
    // create a list containing only the names of the candidates in order to sort them
    // alphabetically
    String[] copyOfNames = new String[numCandidates];
    for (int i = 0; i < numCandidates; i++) {
      copyOfNames[i] = candidates[i][0];
    }

    Arrays.sort(copyOfNames);

    // create a list containing the information of the candidates, sorted alphabetically by name
    String[][] copyOfCandidates = new String[numCandidates][3];
    // for every name in the sorted list, add the corresponding candidate information to the new
    // list
    for (int i = 0; i < numCandidates; i++) {
      for (int j = 0; j < numCandidates; j++) {
        if (copyOfNames[i].equals(candidates[j][0])) {
          copyOfCandidates[i] = candidates[j];
        }
      }
    }
    // copy the sorted candidates' details back to the candidates list
    for (int i = 0; i < numCandidates; i++) {
      candidates[i] = copyOfCandidates[i];
    }
  }
}
