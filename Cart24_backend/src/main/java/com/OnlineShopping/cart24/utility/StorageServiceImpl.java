package com.OnlineShopping.cart24.utility;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;



@Component
public class StorageServiceImpl implements StorageService {

	@Value("${disk.upload.basepath}")
	private String BASEPATH;
	
	

	@Override
	public List<String> loadAll() {
		File dirPath = new File(BASEPATH);
		return Arrays.asList(dirPath.list());
	}

	@Override
	public String store(MultipartFile file) {
		System.out.println(file.getOriginalFilename());
		String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println(ext);
		String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ext;
		File filePath = new File(BASEPATH, fileName);
		try (FileOutputStream out = new FileOutputStream(filePath)) {
			FileCopyUtils.copy(file.getInputStream(), out);
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String storeUpdate(MultipartFile file, String Folder, String OName) {
		System.out.println("Request For Update image");
		System.out.println(file.getOriginalFilename());
		String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println(ext);
		// String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ext;
		String MBASEPATH = BASEPATH + File.separator + Folder;
		File filePath = new File(MBASEPATH, OName);
		try (FileOutputStream out = new FileOutputStream(filePath)) {
			FileCopyUtils.copy(file.getInputStream(), out);
			// return OName;
		} catch (Exception e) {
			System.out.println("Fail FileOutputStream in StoreUpdate");
			/* e.printStackTrace(); */
		}
		return null;
	}

	@Override
	public String[] stores(MultipartFile file, MultipartFile file1, MultipartFile file2, MultipartFile file3) {
		System.out.println("Request For Store Images");
		System.out.println(file.getOriginalFilename() + " " + file1.getOriginalFilename() + " "
				+ file2.getOriginalFilename() + " " + file3.getOriginalFilename());
		String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String ext1 = file1.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String ext2 = file2.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String ext3 = file3.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println(ext);
		String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ext;
		String fileName1 = UUID.randomUUID().toString().replaceAll("-", "") + ext1;
		String fileName2 = UUID.randomUUID().toString().replaceAll("-", "") + ext2;
		String fileName3 = UUID.randomUUID().toString().replaceAll("-", "") + ext3;
		File filePathD = new File(BASEPATH, fileName);
		filePathD.mkdir();
		System.out.println("Diarectory does not Exist, Diarectory created:" + fileName);
		String MBASEPATH = BASEPATH + File.separator + fileName;
		File filePath = new File(MBASEPATH, fileName);
		File filePath1 = new File(MBASEPATH, fileName1);
		File filePath2 = new File(MBASEPATH, fileName2);
		File filePath3 = new File(MBASEPATH, fileName3);

		try (FileOutputStream out = new FileOutputStream(filePath);
				FileOutputStream out1 = new FileOutputStream(filePath1);
				FileOutputStream out2 = new FileOutputStream(filePath2);
				FileOutputStream out3 = new FileOutputStream(filePath3)) {

			FileCopyUtils.copy(file.getInputStream(), out);
			FileCopyUtils.copy(file1.getInputStream(), out1);
			FileCopyUtils.copy(file2.getInputStream(), out2);
			FileCopyUtils.copy(file3.getInputStream(), out3);
			String fileNames[] = { fileName, fileName1, fileName2, fileName3 };

			return fileNames;
		} catch (Exception e) {
			System.out.println("ERRor");
			System.out.println("Fail FileOutputStream in Stores");
			/* e.printStackTrace(); */
		}
		return null;
	}

	@Override
	public Resource load(String fileName) {
		File filePath = new File(BASEPATH, fileName);
		if (filePath.exists())
			return new FileSystemResource(filePath);
		return null;
	}

	@Override
	public Resource loads(String fileName) {
		System.out.println("Request For Resource REload");
		String folder = fileName.substring(0, fileName.indexOf('-'));
		String name = fileName.substring(fileName.indexOf('-') + 1);

		System.out.println("Diarectory NAME:" + folder + " NAME:" + name);
		String MBASEPATH = BASEPATH + File.separator + folder;
		File filePath = new File(MBASEPATH, name);
		if (filePath.exists())
			return new FileSystemResource(filePath);
		return null;
	}
	
	@Override
	public Resource Dloads(String fileName) {
		System.out.println("Request For Resource REload");
		String folder = fileName.substring(0, fileName.indexOf('-'));
		String name = fileName.substring(fileName.indexOf('-') + 1);

		System.out.println("Diarectory NAME:" + folder + " NAME:" + name);
		String DBASEPATH = BASEPATH + File.separator + "COPYPATH";
		String MBASEPATH = DBASEPATH + File.separator + folder;
		
		File filePath = new File(MBASEPATH, name);
		if (filePath.exists())
			return new FileSystemResource(filePath);
		return null;
	}

	@Override
	public void deleteFolder(String fileName) {
		System.out.println("Request For Folder Deleted");
		File filePath = new File(BASEPATH, fileName);
		if (filePath.exists()) {
			try {
				FileUtils.deleteDirectory(filePath);
				System.out.println("Folder Deleted");
			} catch (IOException e) {
				System.out.println("ERRor");
				System.out.println("Fail to delete in deleteFolder");
				/* e.printStackTrace(); */
			}
			System.out.println("Folder Deleted");
		}
	}
	
	@Override
	public void deleteFolderRestore(String fileName) {
		System.out.println("Request For Folder Deleted");
		String COPYPATH=BASEPATH + File.separator + "COPYPATH";
		File filePath = new File(COPYPATH, fileName);
		if (filePath.exists()) {
			try {
				FileUtils.deleteDirectory(filePath);
				System.out.println("Folder Deleted");
			} catch (IOException e) {
				System.out.println("ERRor");
				System.out.println("Fail to delete in deleteFolder");
				/* e.printStackTrace(); */
			}
			System.out.println("Folder Deleted");
		}
	}

	@Override
	public void delete(String Folder, String fileName) {
		System.out.println("Request For file Deleted");
		// TODO Auto-generated method stub

	}

	@Override
	public void copy(String Folder, String fileName) {
		System.out.println("Request For Folder copy");
		String COPYPATH = BASEPATH + File.separator + "COPYPATH";
		String Dir = BASEPATH + File.separator + "COPYPATH" + File.separator + Folder;
		String SRC = BASEPATH + File.separator + Folder + File.separator + fileName;
		String DESTs = BASEPATH + File.separator + "COPYPATH"+ File.separator + Folder + File.separator + fileName;
		File filePathD = new File(COPYPATH);
		File copyPathD = new File(Dir);
		if (!filePathD.exists()) {
			/*--COPYPATH FOLDER--*/
			if (filePathD.mkdir()) {

				if (!copyPathD.exists()) {
					if (copyPathD.mkdir()) {
						System.out.println("Diarectory does not Exist, Diarectory created");
						File filePath = new File(BASEPATH, Folder);

						if (filePath.exists()) {
							File sources = new File(SRC);
							File dests = new File(DESTs);
							try {
								FileCopyUtils.copy(sources, dests);
								System.out.println("File Exist and Copied");
							} catch (IOException e) {
								System.out.println("Fail to copy in copy");
								System.out.println("ERRor");
								/* e.printStackTrace(); */
							}

						} else {
							System.out.println("File Does not Exist");
						}
					} else {
						System.out.println("Folder create ERROR");
					}
				} else {
					System.out.println("Diarectory does not Exist, Diarectory created");
					File filePath = new File(BASEPATH, Folder);

					if (filePath.exists()) {
						File sources = new File(SRC);
						File dests = new File(DESTs);
						try {
							FileCopyUtils.copy(sources, dests);
							System.out.println("File Exist and Copied");
						} catch (IOException e) {
							System.out.println("ERRor");
							System.out.println("Fail to copy in copy");
							/* e.printStackTrace(); */
						}

					} else {
						System.out.println("File Does not Exist");
					}

				}

			}

			else {
				System.out.println("File create ERROR");
			}

		} else {
			if (!copyPathD.exists()) {
				if (copyPathD.mkdir()) {
					System.out.println("Diarectory does not Exist, Diarectory created");
					File filePath = new File(BASEPATH, Folder);

					if (filePath.exists()) {
						File sources = new File(SRC);
						File dests = new File(DESTs);
						try {
							FileCopyUtils.copy(sources, dests);
							System.out.println("File Exist and Copied");
						} catch (IOException e) {
							System.out.println("ERRor");
							System.out.println("Fail to copy in copy");
							/* e.printStackTrace(); */
						}

					} else {
						System.out.println("File Does not Exist");
					}
				} else {
					System.out.println("Folder create ERROR");
				}
			} else {
				System.out.println("Diarectory does not Exist, Diarectory created");
				File filePath = new File(BASEPATH, Folder);

				if (filePath.exists()) {
					File sources = new File(SRC);
					File dests = new File(DESTs);
					try {
						FileCopyUtils.copy(sources, dests);
						System.out.println("File Exist and Copied");
					} catch (IOException e) {
						System.out.println("ERRor");
						System.out.println("Fail to copy in copy");
						/* e.printStackTrace(); */
					}

				} else {
					System.out.println("File Does not Exist");
				}

			}

		}

	}

	@Override
	public void copyFolder(String Folder) {
		System.out.println("Request For Folder Copy");
		String COPYPATH = BASEPATH + File.separator + "COPYPATH";
		String SRC = BASEPATH + File.separator + Folder;
		String DESTs = BASEPATH + File.separator + "COPYPATH" + File.separator + Folder;
		File filePathD = new File(COPYPATH);
		if (!filePathD.exists()) {

			if (filePathD.mkdir()) {
				System.out.println("Diarectory does not Exist, Diarectory created");
				File filePath = new File(SRC);

				if (filePath.exists()) {
					File sources = new File(SRC);
					File dests = new File(DESTs);
					try {
						//FileOutputStream out = new FileOutputStream(filePath);
						FileCopyUtils.copy(sources, dests);
						/*
						 * Files.copy(sources, out); FileUtils.copyDirectory(sources,dests );
						 */
						System.out.println("File Exist and Copied");
					} catch (IOException e) {
						System.out.println("ERRor in copy folder");
						System.out.println("Fail to copy in copyFolder string");
						/* e.printStackTrace(); */
					}

				} else {
					System.out.println("File Does not Exist");
				}
			} else {
				System.out.println("File create ERROR");
			}

		} else {
			System.out.println("Folder Exist");
			File filePath = new File(SRC);
			if (filePath.exists()) {
				File sources = new File(SRC);
				File dests = new File(DESTs);
				try {
					//FileOutputStream out = new FileOutputStream(filePath);
					FileCopyUtils.copy(sources, dests);
					/*
					 * Files.copy(sources, out); FileUtils.copyDirectory(sources,dests );
					 */
					System.out.println("File Exist and Copied");
				} catch (IOException e) {
					System.out.println("ERRor in copy folder");
					System.out.println("Fail to copy in copyFolder string");
					/* e.printStackTrace(); */
				}

			} else {
				System.out.println("File Does not Exist");
			}

		}

	}

	@Override
	public void copyFolder(String Folder, String fileName) {
		System.out.println("Request For Folder copy");
		String COPYPATH = BASEPATH + File.separator + "COPYPATH";
		String Dir = BASEPATH + File.separator + "COPYPATH" + File.separator + Folder;
		String SRC = BASEPATH + File.separator + Folder + File.separator + fileName;
		String DESTs = BASEPATH + File.separator + "COPYPATH"+ File.separator + Folder + File.separator + fileName;
		File filePathD = new File(COPYPATH);
		File copyPathD = new File(Dir);
		if (!filePathD.exists()) {
			/*--COPYPATH FOLDER--*/
			if (filePathD.mkdir()) {

				if (!copyPathD.exists()) {
					if (copyPathD.mkdir()) {
						System.out.println("Diarectory does not Exist, Diarectory created");
						File filePath = new File(BASEPATH, Folder);

						if (filePath.exists()) {
							File sources = new File(SRC);
							File dests = new File(DESTs);
							try {
								FileCopyUtils.copy(sources, dests);
								System.out.println("File Exist and Copied");
							} catch (IOException e) {
								System.out.println("ERRor");
								System.out.println("Fail to copy in copyFolder two string");
								/* e.printStackTrace(); */
							}

						} else {
							System.out.println("File Does not Exist");
						}
					} else {
						System.out.println("Folder create ERROR");
					}
				} else {
					System.out.println("Diarectory does not Exist, Diarectory created");
					File filePath = new File(BASEPATH, Folder);

					if (filePath.exists()) {
						File sources = new File(SRC);
						File dests = new File(DESTs);
						try {
							FileCopyUtils.copy(sources, dests);
							System.out.println("File Exist and Copied");
						} catch (IOException e) {
							System.out.println("ERRor");
							System.out.println("Fail to copy in copyFolder two string");
							/* e.printStackTrace(); */
						}

					} else {
						System.out.println("File Does not Exist");
					}

				}

			}

			else {
				System.out.println("File create ERROR");
			}

		} else {
			if (!copyPathD.exists()) {
				if (copyPathD.mkdir()) {
					System.out.println("Diarectory does not Exist, Diarectory created");
					File filePath = new File(BASEPATH, Folder);

					if (filePath.exists()) {
						File sources = new File(SRC);
						File dests = new File(DESTs);
						try {
							FileCopyUtils.copy(sources, dests);
							System.out.println("File Exist and Copied");
						} catch (IOException e) {
							System.out.println("ERRor");
							System.out.println("Fail to copy in copyFolder two string");
							/* e.printStackTrace(); */
						}

					} else {
						System.out.println("File Does not Exist");
					}
				} else {
					System.out.println("Folder create ERROR");
				}
			} else {
				System.out.println("Diarectory does not Exist, Diarectory created");
				File filePath = new File(BASEPATH, Folder);

				if (filePath.exists()) {
					File sources = new File(SRC);
					File dests = new File(DESTs);
					try {
						FileCopyUtils.copy(sources, dests);
						System.out.println("File Exist and Copied");
					} catch (IOException e) {
						System.out.println("ERRor");
						System.out.println("Fail to copy in copyFolder two string");
						/* e.printStackTrace(); */
					}

				} else {
					System.out.println("File Does not Exist");
				}

			}

		}

	}
	
	@Override
	public void copyFolderRestore(String Folder, String fileName) {
		System.out.println("Request For Folder copy");
		String COPYPATH = BASEPATH + File.separator + "COPYPATH";
		String Dir = BASEPATH + File.separator +  Folder;
		String SRC = BASEPATH + File.separator + "COPYPATH"+ File.separator + Folder + File.separator + fileName;
		String DESTs = BASEPATH + File.separator + Folder + File.separator + fileName;
		File filePathD = new File(BASEPATH);
		File copyPathD = new File(Dir);
		
		if (!filePathD.exists()) {
			/*--COPYPATH FOLDER--*/
			if (filePathD.mkdir()) {

				if (!copyPathD.exists()) {
					if (copyPathD.mkdir()) {
						System.out.println("Diarectory does not Exist, Diarectory created");
						File filePath = new File(COPYPATH, Folder);

						if (filePath.exists()) {
							File sources = new File(SRC);
							File dests = new File(DESTs);
							try {
								FileCopyUtils.copy(sources, dests);
								System.out.println("File Exist and Copied");
							} catch (IOException e) {
								System.out.println("ERRor");
								System.out.println("Fail to copy in copyFolder two string");
								/* e.printStackTrace(); */
							}

						} else {
							System.out.println("File Does not Exist");
						}
					} else {
						System.out.println("Folder create ERROR");
					}
				} else {
					System.out.println("Diarectory Exist");
					File filePath = new File(COPYPATH, Folder);

					if (filePath.exists()) {
						File sources = new File(SRC);
						File dests = new File(DESTs);
						try {
							FileCopyUtils.copy(sources, dests);
							System.out.println("File Exist and Copied");
						} catch (IOException e) {
							System.out.println("ERRor");
							System.out.println("Fail to copy in copyFolder two string");
							/* e.printStackTrace(); */
						}

					} else {
						System.out.println("File Does not Exist");
					}

				}

			}

			else {
				System.out.println("File create ERROR");
			}

		} else {
			if (!copyPathD.exists()) {
				if (copyPathD.mkdir()) {
					System.out.println("Diarectory does not Exist, Diarectory created");
					File filePath = new File(COPYPATH, Folder);

					if (filePath.exists()) {
						File sources = new File(SRC);
						File dests = new File(DESTs);
						try {
							FileCopyUtils.copy(sources, dests);
							System.out.println("File Exist and Copied");
						} catch (IOException e) {
							System.out.println("ERRor");
							System.out.println("Fail to copy in copyFolder two string");
							/* e.printStackTrace(); */
						}

					} else {
						System.out.println("File Does not Exist");
					}
				} else {
					System.out.println("Folder create ERROR");
				}
			} else {
				System.out.println("Diarectory Exist");
				File filePath = new File(COPYPATH, Folder);

				if (filePath.exists()) {
					File sources = new File(SRC);
					File dests = new File(DESTs);
					try {
						FileCopyUtils.copy(sources, dests);
						System.out.println("File Exist and Copied");
					} catch (IOException e) {
						System.out.println("ERRor");
						System.out.println("Fail to copy in copyFolder two string");
						/* e.printStackTrace(); */
					}

				} else {
					System.out.println("File Does not Exist");
				}

			}

		}

	}

}
