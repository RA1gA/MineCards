package jp.mc.ra1ga.minecards.json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import com.google.gson.Gson;

import jp.mc.ra1ga.mycore.json.JsonFileable;

public class CollectionFile implements JsonFileable {
	public CollectionFile(JavaPlugin plugin, String fileName) {
		this.plugin = plugin;
		this.fileName = fileName;
	}

	private JavaPlugin plugin;
	private String fileName;

	@Override
	public File loadFile() {
		File file = new File(plugin.getDataFolder().getAbsolutePath() + "\\" + fileName);
		try {
			plugin.getDataFolder().mkdirs();
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	@Override
	public List<String> loadJson() {
		List<String> list = new ArrayList<>();
		File file = loadFile();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			String json;
			while((json = br.readLine()) != null){
				list.add(json);
			};
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void writeJson(List<String> jsons) {
		File file = loadFile();
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
			PrintWriter pw = new PrintWriter(bw);

			Gson gson = new Gson();
			for(Object obj : CardCollectionAccessor.getInstance().getAllJsonIns()){
				pw.println(gson.toJson(obj));
			}

			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
