// Title:    Class representing a Ballot with a list of Elections, which also contributes to voting 
//           in those elections
// Course:   CS 300 Fall 2024
//
// Author:   Mohnish Nanthakumar
// Email:    mnanthakumar@wisc.edu
// Lecturer: Hobbes LeGault
//
// Partner Name:    Harsh Singh
// Partner Email:   hvsingh@wisc.edu
// Partner Lecturer's Name: Hobbes LeGault
// 
//   X Write-up states that pair programming is allowed for this assignment.
//   X We have both read and understand the course Pair Programming Policy.
//   X We have registered our team prior to the team registration deadline.
//
// Persons:         NONE
// Online Sources:  
//           https://stackoverflow.com/questions/7937029/how-to-break-out-or-exit-a-method-in-java
//                  (Used to understand how to exit a method in java)

import java.util.ArrayList; 
import java.util.NoSuchElementException;

/**
 * An instantiable class representing a ballot. The class stores a list of Elections, and each 
 * Ballot instance is allowed one vote per Election. For use in the Exceptional Election project in 
 * CS300.
 */
public class Ballot {
  /**
   * A class variable to track whether ballots have yet been created.
   */
  private static boolean ballotsCreated;
  
  /**
   * An ArrayList containing the active elections that Ballots may vote in.
   */
  private static ArrayList<Election> elections = new ArrayList<>();
  
  /**
   * An array containing one boolean per Election in the elections ArrayList at the time of this 
   * Ballot's creation.
   */
  private boolean[] hasVoted;
  
  /**
   * Initializes a new ballot which corresponds to the current number of elections present in the 
   * elections ArrayList. This new Ballot has not yet voted in any of the elections. 
   * 
   * When a Ballot is successfully created, this constructor sets the class ballotsCreated variable 
   * to true.
   * 
   * @throws IllegalStateException if you attempt to create a Ballot when there are no elections 
   *         to vote in yet
   */
  public Ballot() {
    if (elections.size() == 0) {
      throw new IllegalStateException("No elections to vote for, add an election first.");
    }
    
    // Creates array for hasVoted that is the same length as the list of election
    hasVoted = new boolean[elections.size()];
    
    
    // Set ballotsCreated to true once ballot is successfully created
    ballotsCreated = true;
  }
  
  /**
   * Adds an election to the end of the elections ArrayList, as long as this election (or one equal 
   * to it) is not yet present.
   * 
   * @param election the election to add to the list of elections
   * @throws IllegalStateException if any ballots have been created, at which point no more 
   *         elections may be added to the list
   * @throws IllegalArgumentException if the election is already present in the list
   */
  public static void addElection(Election election) {
    // Throws IllegalStateException if ballots have been created
    if (ballotsCreated) {
      throw new IllegalStateException("Ballots have been created, no election may be added");
    }
    
    // Throws IllegalArgumentException if election already exists
    for (int i = 0; i < elections.size(); i++) {
      if (elections.get(i).equals(election)) {
        throw new IllegalArgumentException("Election is already present in the list");
      }
    }
    
    // Adds election to list of elections if exception hasn't been thrown
    elections.add(election);
  }
  
  /**
   * Adds 1 vote to the provided Candidate running in the election for the given seatName, if this 
   * Ballot has not yet voted in that election. Marks the election as having been voted in if the 
   * vote is successful.
   * 
   * You may wish to create a (private) helper method to find the index of a particular election in 
   * the elections ArrayList given its seat name, or explore options ArrayList provides.
   * 
   * @param seatName the name of the seat for the election to vote in
   * @param candidate the Candidate to vote for
   * @throws IllegalStateException if this ballot has already voted in the given election
   * @throws NoSuchElementException if the given seat name does not correspond to an election on 
   *         this ballot, or if the given candidate is not running in that election
   */
  public void vote(String seatName, Candidate candidate) {
    // Finds election that has the same seat name as the parameter entered
    for (int i = 0; i < hasVoted.length; i++) {
      if (elections.get(i).SEAT_NAME.equals(seatName)) {
        
        // If election with seat name has been found, check if vote has been cast for this seat 
        // name
        if (hasVoted[i]) {
          // If vote has been cast, throw IllegalStateException
          throw new IllegalStateException("Ballot has already voted in the given election.");
        }
        
        // Add vote for specific candidate, should throw error if candidate isn't present
        elections.get(i).vote(candidate);
        
        // Set hasVoted for specific election to true
        hasVoted[i] = true;
        
        // Exit the method entirely through empty return statement, will prevent exception from 
        // being thrown (seat name was found)
        return;
      }
    }
    
    // Throw NoSuchElementException if seat name wasn't present in list
    throw new NoSuchElementException("Seat name isn't present in list of elections");
  }
  
  /**
   * Checks whether this ballot has already voted in an election corresponding to the given 
   * seatName
   * 
   * @param seatName the name of the seat for the election to vote in
   * @return true if this ballot has already cast a vote for the specified election, false 
   *         otherwise
   * @throws NoSuchElementException if the given seat name does not correspond to an election on 
   *         this ballot
   */
  public boolean hasVoted(String seatName) {
    // Finds election that has the same seat name as the parameter entered
    for (int i = 0; i < hasVoted.length; i++) {
      if (elections.get(i).SEAT_NAME.equals(seatName)) {
        // If election with seat name has been found, finds whether the person has voted in that 
        // election and returns that value
        return hasVoted[i];
      }
    }
    
    // If no name was found, throw NoSuchElementException stating that seat name wasn't present
    throw new NoSuchElementException("Name does not correspond to an election");
  }

  @Override
  /**
   * Creates and returns a String representation of this ballot's voter state as follows: in order, 
   * lists the seatName of the election from the elections ArrayList and whether this Ballot has 
   * yet cast a vote in that election. For example, for a ballot with four elections that has been 
   * voted from twice, you might get the String representation:
   * 
   * "Best Pokemon: false
   * Basketball MVP: true
   * Worst Football Team: true
   * Brunch Location: false"
   * 
   * @return a string representation of this ballot as described in this comment, which does NOT 
   *         end with a newline
   */
  public String toString() {
    // Get info for first election in list without newline character
    String ballotInfo = elections.get(0).SEAT_NAME + ": " + hasVoted[0];
    
    // Get remaining info for the election in list with newline character
    for (int i = 1; i < hasVoted.length; i++) {
      ballotInfo += "\n" + elections.get(i).SEAT_NAME + ": " + hasVoted[i];
    }
    
    return ballotInfo;
  }
}
