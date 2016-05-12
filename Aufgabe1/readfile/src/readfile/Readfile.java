/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readfile;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.charset.StandardCharsets;


/**
 *
 * @author aliyegokoz
 */
public class Readfile {

    /**
     * @param args the command line arguments
     */
    
    public Work readFile(String filename){
        String fulltext = "";
        Work work = new Work();
        Map<String, Speaker> speakers = new HashMap<>();
        
        try {
            
                        //Read Textfile
			File file = new File("/Users/aliyegokoz/Documents/HDM/Textmining/The First Part of King Henry VI.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-16"));
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = in.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
		fileReader.close();
                        fulltext = stringBuffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
                Pattern p1 = Pattern.compile("<STAGE DIR>[\\n\\w\\W\\s]*?</STAGE DIR>");
                Matcher m1 = p1.matcher(fulltext);
                fulltext = m1.replaceAll("\t");
                   
                List<String> path = new ArrayList<String>();
                StringBuffer tmp = new StringBuffer();
                for (String line : fulltext.split("\n")){
                    Pattern r = Pattern.compile("^<(/?)([^/>a-z]+)>");
                    Matcher m = r.matcher(line);
                    
                    if(m.find()){
                        boolean starttag = !m.group(1).equals("/");
                        String tagname = m.group(2);
                        if(starttag){
                            //System.out.println("Start: " + tagname);
                            path.add(tagname);
                            //System.out.println("You are here: " + path.toString());
                        } else if(path.size() > 0 && tagname.equals(path.get(path.size() -1))){
                            //System.out.println("End " + tagname);
                            Monologue mon = new Monologue();
                            mon.setText(tmp.toString().trim());
                            
                            mon.setPath(new ArrayList<String>(path));
                            tmp.setLength(0);
                            path.remove(path.size() - 1);
                            if(mon.getText().length()>0){
                                work.add(mon);
                            }
                        }
                    }else {
                        if(line.startsWith("\t")){
                            tmp.append(line.trim()).append(" ");
                        }
                    }
                }
                
           return work;     
    }
    
    public AllWorks readFiles(String directory) {
        AllWorks res = new AllWorks();
        File dir = new File(directory);
        for (File f: dir.listFiles()) {
            if (f.isFile() && f.getName().toLowerCase().endsWith(".txt")) {
                res.add(readFile(f.getAbsolutePath()));
            } else if (f.isDirectory() && !f.getName().toLowerCase().endsWith("_characters")) {
                res.addAll(readFiles(f.getAbsolutePath()));
            }
        }
        return res;
    }
   
}