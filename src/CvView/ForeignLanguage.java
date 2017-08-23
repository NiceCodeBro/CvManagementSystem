package CvView;

import java.util.ArrayList;

public class ForeignLanguage {

	private ArrayList<String> foreignName = new ArrayList<String>() ;
	private ArrayList<String> foreignLevel = new ArrayList<String>() ;
	
	public ArrayList<String> getForeignName() {
		return foreignName;
	}
	public void setForeignName(String langName) {
		foreignName.add(langName);
	}
	public ArrayList<String> getForeignLevel() {
		return foreignLevel;
	}
	public void setForeignLevel(String langLevel) {
		foreignLevel.add(langLevel);
	}

	
	
	
}
