package jp.mc.ra1ga.mycore.json;

import java.io.File;
import java.util.List;

public interface JsonFileable {
	
	public File loadFile();
	
	public List<String> loadJson();
	
	public void writeJson(List<String> jsons);
	
}
