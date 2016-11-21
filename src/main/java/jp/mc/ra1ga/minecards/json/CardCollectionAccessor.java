package jp.mc.ra1ga.minecards.json;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;

import jp.mc.ra1ga.mycore.json.JsonInstanceAccessible;

public class CardCollectionAccessor implements JsonInstanceAccessible {
	private CardCollectionAccessor() {
		instances = new ArrayList<>();
	}
	public static CardCollectionAccessor getInstance(){
		return cca;
	}

	private static final CardCollectionAccessor cca = new CardCollectionAccessor();
	private List<CardCollection> instances;

	@Override
	public Object getJsonInsFrom(UUID uuid) {
		for(CardCollection cc : instances){
			if(cc.getUUID().equals(uuid)){
				return cc;
			}
		}
		return null;
	}

	@Override
	public List<Object> getAllJsonIns() {
		List<Object> list = new ArrayList<>();
		for(CardCollection cc : instances){
			list.add(cc);
		}
		return list;
	}

	@Override
	public void putJsonIns(String json) {
		Gson gson = new Gson();
		CardCollection ins = gson.fromJson(json, CardCollection.class);

		addInstance(ins);
	}

	@Override
	public Object initJsonIns(UUID uuid) {
		CardCollection ins = new CardCollection(uuid, new ArrayList<CardCollectionCards>());

		addInstance(ins);
		return ins;
	}

	private void addInstance(CardCollection ins){
		if(ins.getUUID() != null){
			CardCollection rm;
			if((rm = (CardCollection) getJsonInsFrom(ins.getUUID())) != null){
				instances.remove(rm);
			}
		}
		instances.add(ins);
	}

}
