//////////////// FILE HEADER //////////////////////////
//
// Title: The Election Manager Program uses the methods defined in this class to manipulate
//////////////// imaginary ballots
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
 * This class is meant for the developer's purpose of testing ELection Manager
 */
public class ElectionManagerTester {

/**
 * test the containsCandidate() method in a scenario when the 2D String array is empty
 * @return true if the method detects no candidates, false otherwise
 */
  public static boolean testContainsEmpty() {
    // for your use: an "empty" candidate list
    String[][] candidateList = {null, null, null, null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    int size = 0;
    String targetName = "Charmander";
    String targetParty = "Water";
    boolean expected = false;

    // (1b) call the method we are testing
    boolean actual =
        ElectionManager.containsCandidate(candidateList, size, targetName, targetParty);

    // (2) verify that the expected method return value and the actual return value match
    if (expected != actual)
      return false;

    // (3) since THIS method should not modify the array, check it against a copy we made
    if (!Arrays.deepEquals(candidateList, candidateCopy))
      return false;

    // (4) if we have not yet returned false, we can now return true as all tests have passed!
    return true;
  }
/**
 * test if the containsCandidate method can detect the absence of a provided candidate
 * @return true if the method detects that the given candidate is not in the list, false otherwise
 */
  public static boolean testDoesNotContain() {
    // (1a) set up the test variables
    String[][] candidateList = {{"Slowpoke", "Water", "3"}, {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"}, null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    int size = 3;
    String targetName = "Charmander";
    String targetParty = "Water";
    boolean expected = false;

    // (1b) call the method we are testing
    boolean actual =
        ElectionManager.containsCandidate(candidateList, size, targetName, targetParty);

    // (2) verify that the expected method return value and the actual return value match
    if (expected != actual)
      return false;

    // (3) since THIS method should not modify the array, check it against a copy we made
    if (!Arrays.deepEquals(candidateList, candidateCopy))
      return false;

    // (4) if we have not yet returned false, we can now return true as all tests have passed!
    return true;
  }

  /**
   * PROVIDED TESTER METHOD: example test method for verifying whether a candidate has already been
   * added to the race.
   * 
   * NOTE: This method ONLY tests scenarios where the candidate IS PRESENT in the list; situations
   * where the candidate is not present or the list is empty should be tested in the other contains
   * tester methods.
   * 
   * @return false if any of the scenarios we test have results other than what we expect; true ONLY
   *         if all of our expectations are met by the method we are testing
   */
  public static boolean testDoesContain() {

    // (1a) set up the test variables
    String[][] candidateList = {{"Slowpoke", "Water", "3"}, {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"}, null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    int size = 3;
    String targetName = "Wooper";
    String targetParty = "Water";
    boolean expected = true;

    // (1b) call the method we are testing
    boolean actual =
        ElectionManager.containsCandidate(candidateList, size, targetName, targetParty);

    // (2) verify that the expected method return value and the actual return value match
    if (expected != actual)
      return false;

    // (3) since THIS method should not modify the array, check it against a copy we made
    if (!Arrays.deepEquals(candidateList, candidateCopy))
      return false;

    // (4) if we have not yet returned false, we can now return true as all tests have passed!
    return true;

  }

  public static boolean testAddToEmpty() {
    // (1a) set up the test variables
    String[][] candidateList = {null, null, null};
    String newName = "Goldeen";
    String newParty = "Water";
    int newVotes = 5;

    String[][] expectedList = {{"Goldeen", "Water", "5"}, null, null};
    int size = 0;
    int expected = 1;

    // (1b) call the method we are testing
    int actual = ElectionManager.addCandidate(candidateList, size, newName, newParty, newVotes);
    // (2) verify that the expected method return value and the actual return value match
    if (expected != actual)
      return false;

    // (3) this method modifies the input array; verify that it was modified correctly
    if (!Arrays.deepEquals(candidateList, expectedList))
      return false;

    // (4) if we have not yet returned false, we can now return true as all tests have passed!
    return true;
  }

  /**
   * PROVIDED TESTER METHOD: example test method for verifying whether a new candidate has been
   * added correctly to the race.
   * 
   * @return false if any of the scenarios we test have results other than what we expect; true ONLY
   *         if all of our expectations are met by the method we are testing
   */
  public static boolean testAddToNonEmpty() {

    // (1a) set up the test variables
    String[][] candidateList = {{"Slowpoke", "Water", "3"}, {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"}, null, null, null};
    String newName = "Goldeen";
    String newParty = "Water";
    int newVotes = 5;

    String[][] expectedList = {{"Goldeen", "Water", "5"}, // alphabetically first, new candidate
                                                          // will be added here
        {"Slowpoke", "Water", "3"}, {"Squirtle", "Water", "127"}, {"Wooper", "Water", "300"}, null,
        null}; // now only TWO null values in this length-6 array!
    int size = 3;
    int expected = 4;

    // (1b) call the method we are testing
    int actual = ElectionManager.addCandidate(candidateList, size, newName, newParty, newVotes);
    // (2) verify that the expected method return value and the actual return value match
    if (expected != actual)
      return false;

    // (3) this method modifies the input array; verify that it was modified correctly
    if (!Arrays.deepEquals(candidateList, expectedList))
      return false;

    // (4) if we have not yet returned false, we can now return true as all tests have passed!
    return true;
  }

  public static boolean testAddCandidateErrors() {
    // (1a) set up the test variables
    String[][] candidateList = {{"Slowpoke", "Water", "3"}, {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"}, null, null, null};
    String newName = "Goldeen";
    String newParty = "Water";
    int newVotes = -5;

    String[][] expectedList = {{"Slowpoke", "Water", "3"}, {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"}, null, null, null};
    int size = 3;
    int expected = 3;

    // (1b) call the method we are testing
    int actual = ElectionManager.addCandidate(candidateList, size, newName, newParty, newVotes);
    // (2) verify that the expected method return value and the actual return value match
    if (expected != actual)
      return false;

    // (3) this method modifies the input array; verify that it was modified correctly
    if (!Arrays.deepEquals(candidateList, expectedList))
      return false;

    // (4) if we have not yet returned false, we can now return true as all tests have passed!
    return true;
  }

  public static boolean testAddToFull() {
    // (1a) set up the test variables
    String[][] candidateList =
        {{"Slowpoke", "Water", "3"}, {"Squirtle", "Water", "127"}, {"Wooper", "Water", "300"}};
    String newName = "Goldeen";
    String newParty = "Water";
    int newVotes = 5;

    String[][] expectedList =
        {{"Slowpoke", "Water", "3"}, {"Squirtle", "Water", "127"}, {"Wooper", "Water", "300"}};
    int size = 3;
    int expected = 3;

    // (1b) call the method we are testing
    int actual = ElectionManager.addCandidate(candidateList, size, newName, newParty, newVotes);
    // (2) verify that the expected method return value and the actual return value match
    if (expected != actual)
      return false;

    // (3) this method modifies the input array; verify that it was modified correctly
    if (!Arrays.deepEquals(candidateList, expectedList))
      return false;

    // (4) if we have not yet returned false, we can now return true as all tests have passed!
    return true;
  }

  //// DROP CANDIDATE: one provided tester method, two (2) TODO

  public static boolean testDropOnlyCandidate() {
    // (1a) set up the test variables
    String[][] candidateList = {{"Slowpoke", "Water", "3"}, null, null, null};
    String name = "Slowpoke";
    String party = "Water";

    String[][] expectedList = {null, null, null, null};
    int size = 1;
    int expected = 0;

    // (1) call the method we are testing
    int actual = ElectionManager.dropCandidate(candidateList, size, name, party);

    // (2) verify that the expected method return value and the actual return value match
    if (expected != actual)
      return false;

    // (3) this method modifies the input array; verify that it was modified correctly
    if (!Arrays.deepEquals(candidateList, expectedList))
      return false;

    // (4) if we have not yet returned false, we can now return true as all tests have passed!
    return true;
  }

  public static boolean testDropFirstCandidate() {
    // (1a) set up the test variables
    String[][] candidateList = {{"Slowpoke", "Water", "3"}, {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"}, null, null};
    String name = "Slowpoke";
    String party = "Water";

    String[][] expectedList =
        {{"Squirtle", "Water", "127"}, {"Wooper", "Water", "300"}, null, null, null};
    int size = 3;
    int expected = 2;

    // (1) call the method we are testing
    int actual = ElectionManager.dropCandidate(candidateList, size, name, party);

    // (2) verify that the expected method return value and the actual return value match
    if (expected != actual)
      return false;

    // (3) this method modifies the input array; verify that it was modified correctly
    if (!Arrays.deepEquals(candidateList, expectedList))
      return false;

    // (4) if we have not yet returned false, we can now return true as all tests have passed!
    return true;
  }

  /**
   * PROVIDED TESTER METHOD: example test method for verifying whether trying to drop a candidate
   * who is not running in the race correctly has NO effect on the candidate list.
   * 
   * @return false if any of the scenarios we test have results other than what we expect; true ONLY
   *         if all of our expectations are met by the method we are testing
   */
  public static boolean testDropCandidateNotRunning() {

    // (1a) set up the test variables
    String[][] candidateList = {{"Slowpoke", "Water", "3"}, {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"}, null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    String name = "Goldeen";
    String party = "Water";
    int size = 3;
    int expected = 3;

    // (1b) call the method we are testing
    int actual = ElectionManager.dropCandidate(candidateList, size, name, party);

    // (2) verify that the expected method return value and the actual return value match
    if (expected != actual)
      return false;

    // (2a) sometimes you may want to REPEAT the process with slightly different variables:
    name = "Slowpoke";
    party = "Fire"; // try with a name that's present but a different PARTY; should still not drop
    actual = ElectionManager.dropCandidate(candidateList, size, name, party);
    if (expected != actual)
      return false;

    // (3) this scenario should NOT modify the input array; check it against a copy we made
    if (!Arrays.deepEquals(candidateList, candidateCopy))
      return false;

    // (4) if we have not yet returned false, we can now return true as all tests have passed!
    return true;

  }

  //// FIND WINNER: one provided tester method, two (2) TODO

  public static boolean testUncontestedWinner() {
    // (1a) set up the test variables
    String[][] candidateList = {{"Slowpoke", "Water", "3"}, null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    String expectedName = "Slowpoke";
    String expectedParty = "(Water)";
    double expectedVotePct = 3.0 / (3) * 100;
    int size = 1;

    // (1b) call the method we are testing
    String result = ElectionManager.findWinner(candidateList, size);

    // (2) verify that the expected method return value and the actual return value match
    // NOTE: for a String, this takes a little more processing to do sensitively.
    // We expect this result to be "Wooper (Water) - 75.0%" but there may be some weirdness
    // especially with that percentage. See how we do it here:

    String[] resultPieces = result.split(" "); // get the space-separated pieces of the string

    if (resultPieces.length != 4)
      return false; // incorrect formatting
    if (!resultPieces[3].endsWith("%"))
      return false; // no % at the end

    if (!resultPieces[0].equals(expectedName) || !resultPieces[1].equals(expectedParty))
      return false; // wrong name or wrong party

    if (!resultPieces[2].equals("-"))
      return false; // forgot the "-" between party and %

    // do a range check on the calculated vote percentage, since it's not always going to come out
    // exactly the same:
    double actualVotePct =
        Double.valueOf(resultPieces[3].substring(0, resultPieces[3].length() - 1));
    if (Math.abs(actualVotePct - expectedVotePct) > 0.01)
      return false;

    // (3) this scenario should NOT modify the input array; check it against a copy we made
    if (!Arrays.deepEquals(candidateList, candidateCopy))
      return false;

    // (4) if we have not yet returned false, we can now return true as all tests have passed!
    return true;
  }

  /**
   * PROVIDED TESTER METHOD: example test method for verifying the results of an election where one
   * candidate has received a clear majority of the votes cast.
   * 
   * @return false if any of the scenarios we test have results other than what we expect; true ONLY
   *         if all of our expectations are met by the method we are testing
   */
  public static boolean testClearWinner() {

    // (1a) set up the test variables
    String[][] candidateList = {{"Slowpoke", "Water", "3"}, {"Squirtle", "Water", "97"},
        {"Wooper", "Water", "300"}, null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    String expectedName = "Wooper";
    String expectedParty = "(Water)";
    double expectedVotePct = 300.0 / (300 + 97 + 3) * 100;
    int size = 3;

    // (1b) call the method we are testing
    String result = ElectionManager.findWinner(candidateList, size);

    // (2) verify that the expected method return value and the actual return value match
    // NOTE: for a String, this takes a little more processing to do sensitively.
    // We expect this result to be "Wooper (Water) - 75.0%" but there may be some weirdness
    // especially with that percentage. See how we do it here:

    String[] resultPieces = result.split(" "); // get the space-separated pieces of the string

    if (resultPieces.length != 4)
      return false; // incorrect formatting
    if (!resultPieces[3].endsWith("%"))
      return false; // no % at the end

    if (!resultPieces[0].equals(expectedName) || !resultPieces[1].equals(expectedParty))
      return false; // wrong name or wrong party

    if (!resultPieces[2].equals("-"))
      return false; // forgot the "-" between party and %

    // do a range check on the calculated vote percentage, since it's not always going to come out
    // exactly the same:
    double actualVotePct =
        Double.valueOf(resultPieces[3].substring(0, resultPieces[3].length() - 1));
    if (Math.abs(actualVotePct - expectedVotePct) > 0.01)
      return false;

    // (3) this scenario should NOT modify the input array; check it against a copy we made
    if (!Arrays.deepEquals(candidateList, candidateCopy))
      return false;

    // (4) if we have not yet returned false, we can now return true as all tests have passed!
    return true;
  }

  public static boolean testContingentElection() {

    // (1a) set up the test variables
    String[][] candidateList = {{"Slowpoke", "Water", "33"}, {"Squirtle", "Water", "30"},
        {"Wooper", "Water", "50"}, null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    String expectedResult = "CONTINGENT";
    int size = 3;

    // (1b) call the method we are testing
    String actualResult = ElectionManager.findWinner(candidateList, size);

    // (2) verify that the expected method return value and the actual return value match
    if (!expectedResult.equals(actualResult)) {
      return false;
    }

    // (3) this scenario should NOT modify the input array; check it against a copy we made
    if (!Arrays.deepEquals(candidateList, candidateCopy))
      return false;

    // (4) if we have not yet returned false, we can now return true as all tests have passed!
    return true;

  }

  //// FIND LOWEST-POLLING: no provided tester methods, three (3) TODO

  public static boolean testUncontestedLowestPolling() {
    // (1a) set up the test variables
    String[][] candidateList = {{"Slowpoke", "Water", "33"}, null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    String expectedResult = "UNCONTESTED";
    int size = 1;

    // (1b) call the method we are testing
    String actualResult = ElectionManager.findLowestPollingCandidate(candidateList, size);

    // (2) verify that the expected method return value and the actual return value match
    if (!expectedResult.equals(actualResult)) {
      return false;
    }

    // (3) this scenario should NOT modify the input array; check it against a copy we made
    if (!Arrays.deepEquals(candidateList, candidateCopy))
      return false;

    // (4) if we have not yet returned false, we can now return true as all tests have passed!
    return true;

  }

  public static boolean testLowestUniqueVoteCount() {
    // (1a) set up the test variables
    String[][] candidateList = {{"Slowpoke", "Water", "33"}, {"Squirtle", "Water", "30"},
        {"Wooper", "Water", "50"}, null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    String expectedResult = "Squirtle (Water) - 30";
    String expectedName = "Squirtle";
    String expectedParty = "(Water)";
    int size = 3;

    // (1b) call the method we are testing
    String actualResult = ElectionManager.findLowestPollingCandidate(candidateList, size);

    // (2) verify that the expected method return value and the actual return value match
    // NOTE: for a String, this takes a little more processing to do sensitively.
    // We expect this result to be "Squirtle (Water) - 30" but there may be some weirdness
    // See how we do it here:

    String[] resultPieces = actualResult.split(" ");

    if (resultPieces.length != 4) {
      return false; // incorrect formatting
    }
    if (!resultPieces[0].equals(expectedName) || !resultPieces[1].equals(expectedParty))
      return false; // wrong name or wrong party

    if (!resultPieces[2].equals("-"))
      return false; // forgot the "-" between party and %

    // (2) verify that the expected method return value and the actual return value match
    if (!expectedResult.equals(actualResult)) {
      return false;
    }

    // (3) this scenario should NOT modify the input array; check it against a copy we made
    if (!Arrays.deepEquals(candidateList, candidateCopy)) {
      return false;
    }

    // (4) if we have not yet returned false, we can now return true as all tests have passed!
    return true;

  }

  public static boolean testLowestVoteCountTied() {
    // (1a) set up the test variables
    String[][] candidateList = {{"Slowpoke", "Water", "30"}, {"Squirtle", "Water", "30"},
        {"Wooper", "Water", "50"}, null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    String expectedResult = "Slowpoke (Water) - 30";
    String expectedName = "Slowpoke";
    String expectedParty = "(Water)";
    int size = 3;

    // (1b) call the method we are testing
    String actualResult = ElectionManager.findLowestPollingCandidate(candidateList, size);
    String[] resultPieces = actualResult.split(" ");

    if (resultPieces.length != 4) {
      return false; // incorrect formatting
    }
    if (!resultPieces[0].equals(expectedName) || !resultPieces[1].equals(expectedParty))
      return false; // wrong name or wrong party
    if (!resultPieces[2].equals("-"))
      return false; // forgot the "-" between party and %

    // (2) verify that the expected method return value and the actual return value match
    if (!expectedResult.equals(actualResult))
      return false;
    // (3) this scenario should NOT modify the input array; check it against a copy we made
    if (!Arrays.deepEquals(candidateList, candidateCopy)) {
      return false;
    }
    // (4) if we have not yet returned false, we can now return true as all tests have passed!
    return true;
  }

  /**
   * PROVIDED MAIN METHOD to manage the tester methods above.
   * 
   * We're getting a little esoteric here to take advantage of loops to keep the code short; each
   * pass through the loop could also be written as follows:
   * 
   * boolean singleTest = testMethodCall(); allPass &= singleTest;
   * System.out.println("testMethodCall : " + singleTest);
   * 
   * @throws NoSuchMethodException                       if you spell a method name incorrectly
   * 
   *                                                     And a couple of other "checked" exceptions
   *                                                     that should never happen with our usage
   *                                                     here:
   * @throws IllegalAccessException
   * @throws java.lang.reflect.InvocationTargetException
   */
  public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException,
      java.lang.reflect.InvocationTargetException {
    boolean allPass = true, singlePass = true;
    String printFormat = "%-29s: %s\n";

    // NOTE TO STUDENTS: If you create any additional tests for any of the methods in
    // ElectionManager, add their names to the appropriate array below!
    String[] containsTests = {"testContainsEmpty", "testDoesNotContain", "testDoesContain"};

    String[] addTests =
        {"testAddToEmpty", "testAddToNonEmpty", "testAddCandidateErrors", "testAddToFull"};
    String[] dropTests =
        {"testDropOnlyCandidate", "testDropFirstCandidate", "testDropCandidateNotRunning"};
    String[] winTests = {"testUncontestedWinner", "testClearWinner", "testContingentElection"};
    String[] lowTests =
        {"testUncontestedLowestPolling", "testLowestUniqueVoteCount", "testLowestVoteCountTied"};
    String[][] testNames = {containsTests, addTests, dropTests, winTests, lowTests};
    // NOTE TO STUDENTS: this for-loop is moving through the method names we've added to the 2D
    // array testNames and attempting to call methods with those names from this tester
    // (specifically line 286 here). See Java's reflection framework for more details!
    for (String[] testSet : testNames) {
      for (String name : testSet) {
        singlePass = (boolean) ElectionManagerTester.class.getDeclaredMethod(name).invoke(null);
        allPass &= singlePass;
        System.out.printf(printFormat, name, singlePass);
      }
      System.out.println();
    }

    System.out.println("ALL TESTS: " + allPass);

  }

}
