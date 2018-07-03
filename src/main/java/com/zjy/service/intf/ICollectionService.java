package com.zjy.service.intf;

import java.util.Map;

public interface ICollectionService {
	public Map<String,String> collectOrCancel(String username,String fileHash);
	public Map<String, Object> getAllCollectionByName(String username, String spage, String ssize);
}
