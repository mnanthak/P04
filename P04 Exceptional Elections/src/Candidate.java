// Title:    The Candiate Class represents a Candidate in an election with information about the candidate
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
// Online Sources:  https://www.geeksforgeeks.org/instanceof-keyword-in-java/ 
//                  (Helped us understand how to use instanceof operator)

/**
 * An instantiable class representing a candidate in an election. For use in the Exceptional 
 * Election project in CS300.
 */
public class Candidate {
  /**
   * The name of this candidate
   */
  private String name;
  
  /**
   * The number of votes this candidate has received; cannot be a negative number
   */
  private int numVotes;
  
  /**
   * The party of this candidate
   */
  private String party;
  
  /**
   * Creates a new Candidate object with the given name and party.
   * 
   * @param name the candidate's name, cannot be null or blank
   * @param party the candidate's party, cannot be null or blank
   * @throws IllegalArgumentException if the name and/or party is null or blank
   */
  public Candidate(String name, String party) {
    if (name == null || party == null || name.equals(" ") || party.equals(" ")) {
      throw new IllegalArgumentException("Name and party cannot be null or blank");
    }
    
    this.name = name;
    this.numVotes = 0;
    this.party = party;
  }
  
  /**
   * Accessor for the candidate's current number of votes
   * 
   * @return the number of votes this candidate has received
   */
  public int getNumVotes() {
    return numVotes;
  }
  
  /**
   * Adds one (1) vote to this candidate's total
   */
  public void addVote() {
    numVotes++;
  }
  
  @Override
  /**
   * Creates and returns a String representation of this candidate in the form "name (party): 
   * numVotes". For example, Wooper from the Water party who received 5 votes would produce "Wooper 
   * (Water): 5"
   * 
   * @return a String representation of the candidate as described in this comment, which does NOT 
   * end with a newline
   */
  public String toString() {
    return name + " (" + party + "): " + numVotes;
  }
  
  @Override
  /**
   * Determines whether this candidate and anObject are copies (deep or shallow) of each other. If 
   * anObject is not a Candidate object at all, they are not equal. If it IS a Candidate, then they 
   * are equal if and only if this candidate and anObject have exactly the same name, party, and 
   * number of votes.
   * 
   * @param anObject the object to compare this Candidate against
   * @return true if the given object represents a Candidate equivalent to this candidate, false 
   * otherwise.
   */
  public boolean equals(Object anObject) {
    // Check if object is an instance of the Candidate class, return false if it isn't true
    if (! (anObject instanceof Candidate)) {
      return false;
    }
    else {
      // If object is an instance of the Candidate class, return true if name, party, and numVotes
      // match
      if (this.toString().equals(anObject.toString())) {
        return true;
      }
    }
    
    // Return false if name, party, and numVotes didn't match
    return false;
  }
}
