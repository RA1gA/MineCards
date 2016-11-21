package jp.mc.ra1ga.mycore.json;

import java.util.List;
import java.util.UUID;

public interface JsonInstanceAccessible {

	public Object getJsonInsFrom(UUID uuid);

	public List<Object> getAllJsonIns();

	public void putJsonIns(String json);

	public Object initJsonIns(UUID uuid);

}
