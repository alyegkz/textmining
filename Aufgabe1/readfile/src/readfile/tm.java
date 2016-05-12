/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readfile;
import java.util.List;

/**
 *
 * @author aliyegokoz
 */
public class tm {
     
    public static void main(String[] args) {
        Readfile sp = new Readfile();
        AllWorks works = null;
        if (args.length==0) {
            works = sp.readFiles("/Users/aliyegokoz/Documents/HDM/Textmining/TXT/");
        } else {
            works = sp.readFiles(args[0]);
        }
            for (Speaker speaker: works.getAllSpeakers()) {
                System.out.println(speaker.getName() + ": " + speaker.getNumberOfMonologues() + " times, " + speaker.getNumberOfWords() + " words, " + speaker.getNumberOfWords()/speaker.getNumberOfMonologues() + " words per monologue.");
            }
    }
}
