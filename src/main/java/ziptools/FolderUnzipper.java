package ziptools;

import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
            String uncompressedFolder = file.getParent() + file.separator + removeExtension(file.getName());
            uncompressedFolderPath = fileSystem.getPath(uncompressedFolder).toString();

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

    //Loops through the files in the folder and puts the names in a String array and returns it
    public static String[] retrieveFileNames(String folderPath){
        File folder = new File(folderPath);
        File[] fileList = folder.listFiles();
        String[] fileNames;

        if(fileList != null) {
            fileNames = new String[fileList.length];

            for (int i = 0; i < fileList.length; i++) {
                fileNames[i] = fileList[i].getName();
            }
        }
        else{
            fileNames = new String[1];
            fileNames[0] = folderPath;
        }
        return fileNames;
    }

    //Loops through the files in the folder and puts the absolute paths in a String array and returns it
    public static String[] retrieveAbsoluteFilePaths(String folderPath){
        File folder = new File(folderPath);
        File[] fileList = folder.listFiles();
        String[] filePaths;

        if(fileList != null) {
            filePaths = new String[fileList.length];

            for (int i = 0; i < fileList.length; i++) {
                filePaths[i] = fileList[i].getAbsolutePath();
            }
        }
        else{
            filePaths = new String[1];
            filePaths[0] = folder.getPath();
        }
        return filePaths;
    }

    //Loops through the files in the folder and puts the relative paths in a String array and returns it
    public static String[] retrieveRelativeFilePaths(String folderPath){
        File folder = new File(folderPath);
        File[] fileList = folder.listFiles();
        String[] filePaths;

        if (fileList != null) {
            filePaths = new String[fileList.length];

            for (int i = 0; i < fileList.length; i++) {
                filePaths[i] = fileList[i].getPath();
            }
        }
        else{
            filePaths = new String[1];
            filePaths[0] = folder.getPath();
        }

        return filePaths;
    }

    //Remove file extension from a string
    public static String removeExtension(String s) {

        String separator = System.getProperty("file.separator");
        String filename;

        // Remove the path upto the filename.
        int lastSeparatorIndex = s.lastIndexOf(separator);
        if (lastSeparatorIndex == -1) {
            filename = s;
        } else {
            filename = s.substring(lastSeparatorIndex + 1);
        }

        // Remove the extension.
        int extensionIndex = filename.lastIndexOf(".");
        if (extensionIndex == -1)
            return filename;

        return filename.substring(0, extensionIndex);
    }

    public static void resetFile(File file){
        file.delete();
    }
}
