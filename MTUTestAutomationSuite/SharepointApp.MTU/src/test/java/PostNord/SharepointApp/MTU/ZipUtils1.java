package PostNord.SharepointApp.MTU;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils1
{

private List<String> fileList;

public ZipUtils1()
{
   fileList = new ArrayList<String>();
}



public void zipIt(String zipFile)
{
   byte[] buffer = new byte[1024];
   String source = "";
   FileOutputStream fos = null;
   ZipOutputStream zos = null;
   try
   {
      try
      {
         source = RunTest.SOURCE_FOLDER.substring(RunTest.SOURCE_FOLDER.lastIndexOf("\\") + 1, RunTest.SOURCE_FOLDER.length());
      }
     catch (Exception e)
     {
        source = RunTest.SOURCE_FOLDER;
     }
     fos = new FileOutputStream(zipFile);
     zos = new ZipOutputStream(fos);

     System.out.println("Output to Zip : " + zipFile);
     FileInputStream in = null;

     for (String file : this.fileList)
     {
        System.out.println("File Added : " + file);
        ZipEntry ze = new ZipEntry(source + File.separator + file);
        zos.putNextEntry(ze);
        try
        {
           in = new FileInputStream(RunTest.SOURCE_FOLDER + File.separator + file);
           int len;
           while ((len = in.read(buffer)) > 0)
           {
              zos.write(buffer, 0, len);
           }
        }
        finally
        {
           in.close();
        }
     }

     zos.closeEntry();
     System.out.println("Folder successfully compressed");

  }
  catch (IOException ex)
  {
     ex.printStackTrace();
  }
  finally
  {
     try
     {
        zos.close();
     }
     catch (IOException e)
     {
        e.printStackTrace();
     }
  }
}

public void generateFileList(File node)
{

  // add file only
  if (node.isFile())
  {
     fileList.add(generateZipEntry(node.toString()));

  }

  if (node.isDirectory())
  {
	  System.out.println("node.isDirectory()");
     String[] subNote = node.list();
     for (String filename : subNote)
     {
        generateFileList(new File(node, filename));
        System.out.println("filename:"+filename);
     }
  }
}

private String generateZipEntry(String file)
{
   return file.substring(RunTest.SOURCE_FOLDER.length() + 1, file.length());
}
}    