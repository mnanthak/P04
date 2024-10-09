// Title: The ElectionManagerTester Class contains tester methods for the methods of the Candidate,
//////////////// Ballot, and Election classes
// Course: CS 300 Fall 2024
//
// Author:   Mohnish Nanthakumar
// Email:    mnanthakumar@wisc.edu
// Lecturer: Hobbes LeGault
//
// Partner Name:    Harsh Singh
// Partner Email:   hvsingh@wisc.edu
// Partner Lecturer's Name: Hobbes LeGault
//
// X Write-up states that pair programming is allowed for this assignment.
// X We have both read and understand the course Pair Programming Policy.
// X We have registered our team prior to the team registration deadline.
//
// Persons: NONE
// Online Sources: NONE

import java.util.NoSuchElementException;

/**
 * A tester class for the Election Manager project. It contains various tests to check the
 * correctness of the Candidate, Election, and Ballot classes.
 *
 */
public class ElectionManagerTester {

  /**
   * Tests the Candidate constructor, toString(), and getter method for correctness. This test
   * accounts for the fact that a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testCandidateConstructorAndGetters() {

    // in case we get an unexpected exception from a broken implementation
    // we handle it with a try-catch to avoid our tester from crashing
    try {
      // test the 2-argument constructor
      Candidate c = new Candidate("lebron james", "basketball");

      // check that the instance data fields have been initialized correctly
      // using the toString to do this we are also checking its correctness!
      // in a bad implementation either:
      // 1) the constructor didn't intialize a data field correctly OR
      // 2) toString() doesn't return the correct value
      if (!c.toString().equals("lebron james (basketball): 0"))
        return false;

      // let's also verify the one getter method agrees with the toString() output:
      if (c.getNumVotes() != 0)
        return false;

    } catch (Exception e) {
      // we encountered an exception when we should not have, it is a bad implementation!
      e.printStackTrace();
      return false;
    }

    // all tests pass:
    return true;
  }

  /**
   * Verifies that the Candidate constructor throws the correct type of exception(s) where an
   * exception is expected. See the Candidate documentation for details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testCandidateConstructorExceptions() {
    try {
      Candidate c = new Candidate("", null);
    } catch (IllegalArgumentException e) {
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Tests the Election constructor and associated getter methods for correctness. (Note that
   * SEAT_NAME is a publicly-accessible constant and can be verified directly.) This test accounts
   * for the fact a bad implementation may throw an Exception.
   * 
   * @return true if all tests pass, false otherwise
   */

  public static boolean testElectionConstructorAndGetters() {
    try {
      Election election = new Election("President", 8);
      election.addCandidate(new Candidate("Robert F. Kennedy Jr.", "We The People"));
      election.addCandidate(new Candidate("Donald Trump", "Republican Party"));
      if (!election.SEAT_NAME.equals("President")) {
        return false;
      }
      if (!(election.capacity() == 8)) {
        return false;
      }
      String expectedString =
          "President\nRobert F. Kennedy Jr. (We The People) : 0\nDonald Trump (Republican Party) : 0";
      if (!(election.toString()).equals(expectedString)) {
        return false;
      }
      if (!(election.getNumCandidates() == 2)) {
        return false;
      }
    } catch (Exception e) {
      // we encountered an exception when we should not have, it is a bad implementation!
      e.printStackTrace();
      return false;
    }
    // all tests pass:
    return true;
  }

  /**
   * Verifies that the Election constructor throws the correct type of exception(s) in situations
   * where an exception is expected. See the Election documentation for details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testElectionConstructorExceptions() {
    try {
      Election election1 = new Election("President", -5);
    } catch (IllegalArgumentException e) {
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Tests the Election's addCandidate() method for correctness in non-Exception situations. This
   * test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddCandidate() {
    Election election;
    try {
      election = new Election("President", 8);
      election.addCandidate(new Candidate("Robert F. Kennedy Jr.", "We The People"));
    } catch (Exception e) {
      return false;
    }
    if (!(election.getNumCandidates() == 1)) {
      return false;
    }
    if (!(election.capacity() == 8)) {
      return false;
    }
    String expectedString = "President\nRobert F. Kennedy Jr. (We The People) : 0";
    if (!election.toString().equals(expectedString)) {
      return false;
    }
    return true;
  }

  /**
   * Verifies that the Election's addCandidate() method throws the correct type of exception(s) in
   * situations where an exception is expected. See the Election documentation for details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddCandidateExceptions() {
    try {
      Election election = new Election("President", 8);
      election.addCandidate(new Candidate("Robert F. Kennedy Jr.", "We The People"));
      election.addCandidate(new Candidate("Robert F. Kennedy Jr.", "We The People"));
    } catch (IllegalArgumentException e) {
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Tests the Election's vote() method for correctness in non-Exception situations. This test
   * accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testVote() {
    Election election;
    Candidate c;
    try {
      election = new Election("President", 8);
      c = new Candidate("Robert F. Kennedy Jr.", "We The People");
      election.addCandidate(c);
      election.vote(c);
    } catch (Exception e) {
      return false;
    }
    if (!(election.getNumCandidates() == 1)) {
      return false;
    }
    if (!(election.capacity() == 8)) {
      return false;
    }
    String expectedString = "President\nRobert F. Kennedy Jr. (We The People) : 1";
    if (!election.toString().equals(expectedString)) {
      return false;
    }
    return true;
  }

  /**
   * Verifies that the Election's vote() method throws the correct type of exception(s) in
   * situations where an exception is expected. See the Election documentation for details.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testVoteExceptions() {
    ////////////////////////////////////////////////////////////////////////////////////////
    // we're doing the setup separately, so we can isolate the actual test later.
    // if anything fails HERE, that's a different problem than the one we're trying to test,
    // and the test should fail.

    Election election = null; // declare outside of the try block for scope reasons
    try {
      election = new Election("Sportsball", 10);
      Candidate c1 = new Candidate("lebron james", "basketball");
      Candidate c2 = new Candidate("messi", "soccer");
      election.addCandidate(c1);
      election.addCandidate(c2);
    } catch (Exception e) {
      System.out.println("Unable to continue with this test for unrelated reasons!!");
      e.printStackTrace();
      return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    // THIS part is what we are actually testing:

    try {
      election.vote(new Candidate("usain bolt", "athletics"));
      return false; // this line only runs if NO exception is thrown from the previous line
    } catch (NoSuchElementException e) {
      // this is correct
    } catch (Exception e) {
      // this only runs if an exception other than NoSuchElementException was thrown,
      // which is wrong!
      e.printStackTrace();
      return false;
    }
    // all tests pass:
    return true;
  }

  /**
   * Tests the Election's removeCandidate() method for correctness. This test accounts for the fact
   * a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveCandidate() {
    Election election;
    Candidate c;
    try {
      election = new Election("President", 8);
      c = new Candidate("Robert F. Kennedy Jr.", "We The People");
      election.addCandidate(c);
      election.removeCandidate(c);
    } catch (Exception e) {
      return false;
    }
    if (!(election.getNumCandidates() == 0)) {
      return false;
    }
    if (!(election.capacity() == 8)) {
      return false;
    }
    String expectedString = "President";
    if (!election.toString().equals(expectedString)) {
      return false;
    }
    return true;
  }

  /**
   * Verifies that the Election's removeCandidate() method throws the correct type of exception(s)
   * in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveCandidateExceptions() {
    // set-up for the first test
    // we're doing the setup separately, so we can isolate the actual test later.
    // if anything fails HERE, that's a different problem than the one we're trying to test,
    // and the test should fail.
    Election election = null;
    Candidate c;
    try {
      election = new Election("President", 8);
      c = new Candidate("Robert F. Kennedy Jr.", "We The People");
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    // first test
    try {
      election.removeCandidate(c);
    } catch (IllegalStateException e) {
      // this is correct
    } catch (Exception e) {
      // this only runs if an exception other than NoSuchElementException was thrown,
      // which is wrong!
      e.printStackTrace();
      return false;
    }
    // set-up for second test
    Candidate c1;
    Candidate c2;
    try {
      election = new Election("President", 8);
      c1 = new Candidate("Robert F. Kennedy Jr.", "We The People");
      c2 = new Candidate("Donald Trump", "Republican Party");
      election.addCandidate(c1);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    // second test
    try {
      election.removeCandidate(c2);
    } catch (NoSuchElementException e) {
      // this is correct
    } catch (Exception e) {
      // this only runs if an exception other than NoSuchElementException was thrown,
      // which is wrong!
      e.printStackTrace();
      return false;
    }
    // all tests pass:
    return true;

  }

  /**
   * Tests the Ballot two-phase setup process in non-Exception situations. This test accounts for
   * the fact that a bad implementation may throw an Exception.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testBallotSetup() {
    // Phase 1: add elections to the Ballot class
    // Phase 2: create a Ballot and verify that it has the correct number of elections
    // (hint: use toString())
    Election e1;
    Candidate c;
    try {
      e1 = new Election("President", 3);
      Ballot.addElection(e1);
      Ballot.addElection(new Election("Senate", 3));
      Ballot.addElection(new Election("House", 3));
      c = new Candidate("Robert F. Kennedy Jr.", "We The People");
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    try {
      Ballot ballot = new Ballot();
      e1.addCandidate(c);
      ballot.vote("President", c);
      if (!ballot.toString().equals("President: true\nSenate: false\nHouse: false")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Verifies that the Ballot two-phase setup process throws the correct type of exception(s) in
   * situations where an exception is expected. See the Ballot documentation for details.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testBallotSetupExceptions() {
    try {
      Ballot ballot = new Ballot();
      return false;
    } catch (IllegalStateException e) {
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    try {
      Ballot.addElection(new Election("President", 4));
      Ballot ballot = new Ballot();
      Ballot.addElection(new Election("Senate", 4));
      return false;
    } catch (IllegalStateException e) {
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    try {
      Ballot.addElection(new Election("President", 4));
      Ballot.addElection(new Election("President", 4));
    } catch (IllegalArgumentException e) {
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Tests the Ballot vote() and hasVoted() methods in non-Exception situations. This test accounts
   * for the fact that a bad implementation may throw an Exception.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testBallotVote() {
    Election e1 = new Election("President", 3);
    Candidate c;
    try {
      Ballot.addElection(e1);
      Ballot.addElection(new Election("Senate", 3));
      Ballot.addElection(new Election("House", 3));
      c = new Candidate("Robert F. Kennedy Jr.", "We The People");
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    Ballot ballot = new Ballot();
    try {
      e1.addCandidate(c);
      ballot.vote("President", c);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    try {
      if (!ballot.hasVoted("President")) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    if (!ballot.toString().equals("President: true\nSenate: false\nHouse: false")) {
      return false;
    }
    return true;
  }

  /**
   * Verifies that the Ballot vote() and hasVoted() methods throw the correct type of exception(s)
   * in situations where an exception is expected. See the Ballot documentation for details.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testBallotVoteExceptions() {
    Election e1 = new Election("President", 8);
    Election e2 = new Election("Governor", 3);
    Candidate c1 = new Candidate("Robert F. Kennedy Jr.", "We The People");
    Candidate c2 = new Candidate("Ron DeSantis", "Governor");
    e1.addCandidate(c1);
    e2.addCandidate(c2);
    Ballot.addElection(e1);
    Ballot ballot = new Ballot();
    ballot.vote("President", c1);
    try {
      ballot.vote("President", c1);
      return false;
    } catch (IllegalStateException e) {
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    try {
      ballot.vote("Senate", c1);
      return false;
    } catch (NoSuchElementException e) {
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    try {
      ballot.vote("President", c2);
      return false;
    } catch (NoSuchElementException e) {
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Runs all testing methods and prints out their results.
   * 
   * @return true if and only if all the tests return true, false otherwise
   */
  public static boolean runAllRequiredTests() {

    boolean test1 = testCandidateConstructorAndGetters();
    System.out.println("testCandidateConstructorAndGetters(): " + (test1 ? "PASS" : "FAIL"));

    boolean test2 = testCandidateConstructorExceptions();
    System.out.println("testCandidateConstructorExceptions(): " + (test2 ? "PASS" : "FAIL"));

    boolean test3 = testElectionConstructorAndGetters();
    System.out.println("testElectionConstructorAndGetters(): " + (test3 ? "PASS" : "FAIL"));

    boolean test4 = testElectionConstructorExceptions();
    System.out.println("testElectionConstructorExceptions(): " + (test4 ? "PASS" : "FAIL"));

    boolean test5 = testAddCandidate();
    System.out.println("testAddCandidate(): " + (test5 ? "PASS" : "FAIL"));

    boolean test6 = testAddCandidateExceptions();
    System.out.println("testAddCandidateExceptions(): " + (test6 ? "PASS" : "FAIL"));

    boolean test7 = testVote();
    System.out.println("testVote(): " + (test7 ? "PASS" : "FAIL"));

    boolean test8 = testVoteExceptions();
    System.out.println("testVoteExceptions(): " + (test8 ? "PASS" : "FAIL"));

    boolean test9 = testRemoveCandidate();
    System.out.println("testRemoveCandidate(): " + (test9 ? "PASS" : "FAIL"));

    boolean test10 = testRemoveCandidateExceptions();
    System.out.println("testRemoveCandidateExceptions(): " + (test10 ? "PASS" : "FAIL"));

    boolean test11 = testBallotSetup();
    System.out.println("testBallotSetup(): " + (test11 ? "PASS" : "FAIL"));

    boolean test12 = testBallotSetupExceptions();
    System.out.println("testBallotSetupExceptions(): " + (test12 ? "PASS" : "FAIL"));

    boolean test13 = testBallotVote();
    System.out.println("testBallotVote(): " + (test13 ? "PASS" : "FAIL"));

    boolean test14 = testBallotVoteExceptions();
    System.out.println("testBallotVoteExceptions(): " + (test14 ? "PASS" : "FAIL"));

    return test1 && test2 && test3 && test4 && test5 && test6 && test7 && test8 && test9 && test10
        && test11 && test12 && test13 && test14;
  }

  /**
   * Calls runAllRequiredTests and displays the output. If you add additional private testers, call
   * them directly in the main method rather than adding them to the previous method.
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("runAllRequiredTests(): " + runAllRequiredTests());
  }
}