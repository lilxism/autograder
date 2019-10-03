package ziptools;

import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FolderUnzipper {
    public static String unzipFolder(String path){
        String uncompressedFolderPath = "";
        //Attempts to open the folder
        try(ZipFile zipFile = new ZipFile(path)){
            File file = new File(path);
            FileSystem fileSystem = FileSystems.getDefault();

            //Get file entries
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            System.out.println(zipFile.getName());
            //We will unzip files in this folder
            String uncompressedFolder = file.getParent() + file.separator + stripExtension(file.getName());
            uncompressedFolderPath = "" + fileSystem.getPath(uncompressedFolder);

            //Check if uncompressedFolder already exists. If not, create it
            if(Files.notExists(fileSystem.getPath(uncompressedFolder))) {
                Files.createDirectory(fileSystem.getPath(uncompressedFolder));
            }

            while (entries.hasMoreElements()){
                ZipEntry entry = entries.nextElement();
                if (entry.isDirectory()){
                    //Check if folder entry already exists. If not, create it
                    if(Files.notExists(fileSystem.getPath(uncompressedFolder + file.separator + entry.getName()))) {
                        System.out.println("Creating Folder:" + uncompressedFolder + file.separator + entry.getName());
                        Files.createDirectories(fileSystem.getPath(uncompressedFolder + file.separator + entry.getName()));
                    }
                }
                else{
                    String uncompressedFileName = uncompressedFolder + file.separator + entry.getName();
                    Path uncompressedFilePath = fileSystem.getPath(uncompressedFileName);

                    //Check if entry already exists. If not, create it
                    if(Files.notExists(uncompressedFilePath)) {
                        InputStream is = zipFile.getInputStream(entry);
                        BufferedInputStream bis = new BufferedInputStream(is);

                        Files.createFile(uncompressedFilePath);
                        FileOutputStream fileOutput = new FileOutputStream(uncompressedFileName);
                        while (bis.available() > 0) {
                            fileOutput.write(bis.read());
                        }
                        fileOutput.close();
                        System.out.println("Written :" + entry.getName());
                    }
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return uncompressedFolderPath;
    }

    //Remove file extension from a string
    private static String stripExtension(final String s){
        return s != null && s.lastIndexOf(".") > 0 ? s.substring(0, s.lastIndexOf(".")) : s;
    }
}
