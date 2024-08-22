package com.OnlineShopping.cart24.utility;

import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	
	List<String> loadAll();
	String store(MultipartFile file);
	Resource load(String fileName);
	void delete(String Folder, String fileName);
	void copyFolder(String fileName);
	Resource loads(String fileName);
	String[] stores(MultipartFile file, MultipartFile file1, MultipartFile file2, MultipartFile file3);
	String storeUpdate(MultipartFile file, String Folder, String OName);
	void copy(String Folder, String fileName);
	void deleteFolder(String productImageName);
	void copyFolder(String Folder, String fileName);
	Resource Dloads(String fileName);
	void copyFolderRestore(String Folder, String fileName);
	void deleteFolderRestore(String fileName);

}
