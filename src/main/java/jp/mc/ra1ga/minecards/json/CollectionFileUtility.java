package jp.mc.ra1ga.minecards.json;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import jp.mc.ra1ga.minecards.MineCards;

public class CollectionFileUtility {

	public static void writeCardCollectionsToJson(MineCards plugin){
		List<String> list = new ArrayList<>();
		CollectionFile cf = new CollectionFile(plugin, MineCards.COLLECTION_FILE_NAME);

		Gson gson = new Gson();
		for(Object obj : CardCollectionAccessor.getInstance().getAllJsonIns()){
			list.add(gson.toJson(obj));
		}
		cf.writeJson(list);
	}

	public static void readCardCollectionsFromJson(MineCards plugin){
		CardCollectionAccessor cca = CardCollectionAccessor.getInstance();
		CollectionFile cf = new CollectionFile(plugin, MineCards.COLLECTION_FILE_NAME);

		for(String json : cf.loadJson()){
			cca.putJsonIns(json);
		}
	}

}
