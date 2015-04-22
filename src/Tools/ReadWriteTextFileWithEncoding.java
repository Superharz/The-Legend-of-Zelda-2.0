package Tools;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

    /** 
     * Read and write a file using an explicit encoding.
     * @author NOT from SuperHarz Ent.
    */
public final class ReadWriteTextFileWithEncoding {
  /** Constructor. */
  public ReadWriteTextFileWithEncoding(String aFileName) {
    fFileName = aFileName;
  }
  
  /** Write fixed content to the given file. */
  public void write(String[] what, boolean sorted) throws IOException  {
    PrintWriter out;
    if (!sorted)
        out = new PrintWriter(new FileWriter(fFileName, true));
    else
        out = new PrintWriter(new FileWriter(fFileName));
    try {
        for (String what1 : what) {  
            out.write(what1);
                out.write(System.getProperty("line.separator"));
        }
    }
    finally {
      out.close();
    }
    if(!sorted)
        sort();
  }
  
  /** Read the contents of the given file. */
  public LinkedList<String> read() throws IOException {
    LinkedList<String> list = new LinkedList<String>();
    Scanner scanner = new Scanner(new FileInputStream(fFileName), fEncoding);
    try {
      while (scanner.hasNextLine()){
        list.add(scanner.nextLine());
      }
    }
    finally{
      scanner.close();
    }
    return list;
  }
  public String readString() throws IOException {
    StringBuilder text = new StringBuilder();
    String NL = System.getProperty("line.separator");
    Scanner scanner = new Scanner(new FileInputStream(fFileName), fEncoding);
    try {
      while (scanner.hasNextLine()){
        text.append(scanner.nextLine()).append(NL);
      }
    }
    finally{
      scanner.close();
    }
    return text.toString();
  }
  
  
  private final String fFileName;
  private final String fEncoding = "UTF-8";
  /**
   * Sorts the LinkedList created by the read() method
   * It sorts it after the amount of shoots the player needed
   * From small to big
   * Calls the write() method to rewrite the sorted text file
   */
  private void sort() {
      try {
          LinkedList<String> l = read();
          int[] score = new int[l.size()];
          for (int i = 0; i < score.length; i++) {
              String g = l.get(i);
              for (int j = 0; j < g.length(); j++) {
                  
                  if (g.charAt(j) == ' ') {
                      String k = "";
                      for (int m = j+1; m < g.length() && (g.charAt(m) != ' ') && (g.charAt(m) != System.getProperty("line.separator").charAt(0)) ; m++) {
                          k = k+g.charAt(m);
                      }
                      score[i] = Integer.parseInt(k);
                      break;                      
                  }
              }
          }
          int[] sorted = new int[score.length];
          System.arraycopy(score, 0, sorted, 0, score.length);
          Arrays.sort(sorted);
          String[] end = new String[score.length];
          for (int i = 0; i < score.length; i++) {
              for (int j = 0; j < sorted.length; j++) {
                  if (score[j] == sorted[i])
                      end[i] = l.get(j);
              }
          } 
          write(end,true);
      } catch (IOException ex) {
          Logger.getLogger(ReadWriteTextFileWithEncoding.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
}