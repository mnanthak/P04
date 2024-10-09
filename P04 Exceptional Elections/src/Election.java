// Title:    The Election Class represents an Election with a list of candidates running and election info
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
// Online Sources:  NONE

import java.util.NoSuchElementException;

/**
 * An instantiable class representing an election, which maintains a list of Candidates running in 
 * the election and some information about that election. For use in the Exceptional Election 
 * project in CS300.
 */
public class Election {
  /**
   * An oversize, compact array that stores the candidates for this election
   */
  private Candidate[] candidates;
  
  /**
   * The number of candidates currently present in the election
   */
  private int numCandidates;
  
  /**
   * The name of the position for which this election is being held
   */
  final String SEAT_NAME;
  
  /**
   * Initializes the oversize array for this election's candidates and sets the name of the 
   * position for which this election is being held.
   * 
   * @param seatName the name of this election's position
   * @param maxCandidates the maximum number of candidates permitted to run in this election
   * @throws IllegalArgumentException if the maximum number of candidates is not strictly greater 
   *         than 0
   */
  Election(String seatName, int maxCandidates) {
    if (maxCandidates <= 0) {
      throw new IllegalArgumentException("Maximum number of candidates must be greater than 0");
    }
    
    SEAT_NAME = seatName;
    candidates = new Candidate[maxCandidates];
    numCandidates = 0;
  }
  
  /**
   * Accessor method for the current number of candidates in this Election
   * 
   * @return the number of candidates currently running in this election
   */
  public int getNumCandidates() {
    return numCandidates;
  }
  
  /**
   * Accessor method for the maximum number of candidates in this Election. This can be calculated 
   * without adding additional instance fields!
   * 
   * @return the maximum number of candidates permitted to run in this election
   */
  public int capacity() {
    return candidates.length;
  }
  
  /**
   * Adds a candidate to the END of this election's list. Do NOT maintain the candidate list in 
   * alphabetical order this time.
   * 
   * @param candidate the Candidate to add to this election
   * @throws IllegalArgumentException if this candidate is already present in the candidates list 
   *         for this election
   */
  public void addCandidate(Candidate candidate) {
    
  }
  
  /**
   * Removes the candidate matching the provided candidate exactly from the election's list of 
   * candidates
   * 
   * @param candidate the candidate to remove
   * @throws IllegalStateException if you try to remove from an empty candidates list
   * @throws NoSuchElementException if the given Candidate is not present in this election's list 
   *         of candidates
   */
  public void removeCandidate(Candidate candidate) {
    
  }
  
  /**
   * Returns a reference to the Candidate receiving more than 50% of the votes in this election
   * 
   * @return the Candidate with >50% of the votes across this election's candidates
   * @throws IllegalStateException if the candidates list is empty
   * @throws NoSuchElementException if no one candidate has more than 50% of the votes (P01's "contingent" election)
   */
  public Candidate findWinner() {
    return new Candidate("Name", "Party");
  }
  
  /**
   * Increases the vote count of the given candidate by one
   * 
   * @param candidate the candidate to vote for
   * @throws NoSuchElementException the candidate to vote for
   */
  public void vote(Candidate candidate) {
    
  }
  
  @Override
  /**
   * Creates and returns a String representation of this Election object, with each Candidate's 
   * string representation on a separate line. The first line of the output String contains the 
   * name of the seat this election is for. For example, in a three-candidate race for the Best 
   * Pokemon seat:
   * 
   * "Best Pokemon
   * Wooper (Water): 5
   * Pikachu (Electric): 3
   * Charmander (Fire): 1"
   * 
   * @return the String representation of the current state of this Election, which does NOT end 
   *         with a newline
   */
  public String toString() {
    return "";
  }
  
  
  @Override
  /**
   * Determines whether a provided object is equivalent to this Election. If anObject is not an 
   * Election at all, they are not equal. If it IS an Election, they are equivalent if and only if 
   * their seat names match, ignoring capitalization. That is, an election for "Best Pokemon" and 
   * an election for "best pokemon" are considered EQUAL.
   * 
   * @param anObject the object to compare this Election against
   * @return true if the given object represents a Election equivalent to this election, false 
   *         otherwise.
   */
  public boolean equals(Object anObject) {  
    return false;
  }
}
