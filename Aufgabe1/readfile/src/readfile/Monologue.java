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
public class Monologue {
    private Speaker speaker;
    private List<String> path;
    private String text;
    
    public Speaker getSpeaker(){
        return speaker;
    }
    
    public void setSpeaker(Speaker speaker){
        this.speaker = speaker;
    }
    
    public List<String> getPath(){
        return path;
    }
    
    public void setPath(List<String> path){
        this.path = path;
    }
    
    public String getText(){
        return text;
    }
    
    public void setText(String text){
        this.text = text;
    }
    
    public String toString(){
        return "Monologue{" + "speaker=" + speaker + ", path=" + path + ", text=" + text + "}\n";}
}
