package com.watchme.dspcoresupport.utils;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public class FileUtils extends org.apache.commons.io.FileUtils
{
    private static Logger log = LoggerFactory.getLogger(FileUtils.class);

    public static boolean copyFile(String srcFileName, String descFileName)
    {
        return copyFileCover(srcFileName, descFileName, false);
    }

    public static boolean copyFileCover(String srcFileName, String descFileName, Boolean coverlay)
    {
        int i;
        File srcFile = new File(srcFileName);

        if (!(srcFile.exists())) {
            log.debug("复制文件失败，源文件 " + srcFileName + " 不存在!");
            return false;
        }

        if (!(srcFile.isFile())) {
            log.debug("复制文件失败，" + srcFileName + " 不是一个文件!");
            return false;
        }
        File descFile = new File(descFileName);

        if (descFile.exists())
        {
            if (coverlay) {
                log.debug("目标文件已存在，准备删除!");
                if (delFile(descFileName)) //break;
                log.debug("删除目标文件 " + descFileName + " 失败!");
                return false;
            }

            log.debug("复制文件失败，目标文件 " + descFileName + " 已存在!");
            return false;
        }

        if (!(descFile.getParentFile().exists()))
        {
            log.debug("目标文件所在的目录不存在，创建目录!");

            if (!(descFile.getParentFile().mkdirs())) {
                log.debug("创建目标文件所在的目录失败!");
                return false;
            }

        }

        int readByte = 0;
        InputStream ins = null;
        OutputStream outs = null;
        try
        {
            ins = new FileInputStream(srcFile);

            outs = new FileOutputStream(descFile);
            byte[] buf = new byte[1024];

            while ((readByte = ins.read(buf)) != -1)
            {
                outs.write(buf, 0, readByte);
            }
            log.debug("复制单个文件 " + srcFileName + " 到" + descFileName + "成功!");
            i = 1;

//            return i;
        }
        catch (Exception e)
        {
            log.debug("复制文件失败：" + e.getMessage());
            i = 0;

//            return i;
        }
        finally
        {
            if (outs != null)
                try {
                    outs.close();
                } catch (IOException oute) {
                    oute.printStackTrace();
                }

            if (ins != null)
                try {
                    ins.close();
                } catch (IOException ine) {
                    ine.printStackTrace();
                }
        }
        return false;
    }

    public static boolean copyDirectory(String srcDirName, String descDirName)
    {
        return copyDirectoryCover(srcDirName, descDirName, false);
    }

    public static boolean copyDirectoryCover(String srcDirName, String descDirName, boolean coverlay)
    {
        File srcDir = new File(srcDirName);

        if (!(srcDir.exists())) {
            log.debug("复制目录失败，源目录 " + srcDirName + " 不存在!");
            return false;
        }

        if (!(srcDir.isDirectory())) {
            log.debug("复制目录失败，" + srcDirName + " 不是一个目录!");
            return false;
        }

        String descDirNames = descDirName;
        if (!(descDirNames.endsWith(File.separator)))
            descDirNames = descDirNames + File.separator;

        File descDir = new File(descDirNames);

        if (descDir.exists()) {
            if (coverlay)
            {
                log.debug("目标目录已存在，准备删除!");
                if (delFile(descDirNames)) {
//                    break;
                }
                log.debug("删除目录 " + descDirNames + " 失败!");
                return false;
            }

            log.debug("目标目录复制失败，目标目录 " + descDirNames + " 已存在!");
            return false;
        }

        log.debug("目标目录不存在，准备创建!");
        if (!(descDir.mkdirs())) {
            log.debug("创建目标目录失败!");
            return false;
        }

        boolean flag = true;

        File[] files = srcDir.listFiles();
        for (int i = 0; i < files.length; ++i)
        {
            if (files[i].isFile()) {
                flag = copyFile(files[i].getAbsolutePath(), descDirName + files[i].getName());

                if (!(flag))
                    break;

            }

            if (files[i].isDirectory()) {
                flag = copyDirectory(files[i].getAbsolutePath(), descDirName + files[i].getName());

                if (!(flag))
                    break;
            }

        }

        if (!(flag)) {
            log.debug("复制目录 " + srcDirName + " 到 " + descDirName + " 失败!");
            return false;
        }
        log.debug("复制目录 " + srcDirName + " 到 " + descDirName + " 成功!");
        return true;
    }

    public static boolean delFile(String fileName)
    {
        File file = new File(fileName);
        if (!(file.exists())) {
            log.debug(fileName + " 文件不存在!");
            return true;
        }
        if (file.isFile())
            return deleteFile(fileName);

        return deleteDirectory(fileName);
    }

    public static boolean deleteFile(String fileName)
    {
        File file = new File(fileName);
        if ((file.exists()) && (file.isFile())) {
            if (file.delete()) {
                log.debug("删除文件 " + fileName + " 成功!");
                return true;
            }
            log.debug("删除文件 " + fileName + " 失败!");
            return false;
        }

        log.debug(fileName + " 文件不存在!");
        return true;
    }

    public static boolean deleteDirectory(String dirName)
    {
        String dirNames = dirName;
        if (!(dirNames.endsWith(File.separator)))
            dirNames = dirNames + File.separator;

        File dirFile = new File(dirNames);
        if ((!(dirFile.exists())) || (!(dirFile.isDirectory()))) {
            log.debug(dirNames + " 目录不存在!");
            return true;
        }
        boolean flag = true;

        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; ++i)
        {
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());

                if (flag) break;
                break;
            }

            if (files[i].isDirectory()) {
                flag = deleteDirectory(files[i].getAbsolutePath());

                if (!(flag))
                    break;
            }

        }

        if (!(flag)) {
            log.debug("删除目录失败!");
            return false;
        }

        if (dirFile.delete()) {
            log.debug("删除目录 " + dirName + " 成功!");
            return true;
        }
        log.debug("删除目录 " + dirName + " 失败!");
        return false;
    }

    public static boolean createFile(String descFileName)
    {
        File file = new File(descFileName);
        if (file.exists()) {
            log.debug("文件 " + descFileName + " 已存在!");
            return false;
        }
        if (descFileName.endsWith(File.separator)) {
            log.debug(descFileName + " 为目录，不能创建目录!");
            return false;
        }
        if ((!(file.getParentFile().exists())) &&
                (!(file.getParentFile().mkdirs()))) {
            log.debug("创建文件所在的目录失败!");
            return false;
        }

        try
        {
            if (file.createNewFile()) {
                log.debug(descFileName + " 文件创建成功!");
                return true;
            }
            log.debug(descFileName + " 文件创建失败!");
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            log.debug(descFileName + " 文件创建失败!"); }
        return false;
    }

    public static boolean createDirectory(String descDirName)
    {
        String descDirNames = descDirName;
        if (!(descDirNames.endsWith(File.separator)))
            descDirNames = descDirNames + File.separator;

        File descDir = new File(descDirNames);

        if ((descDir.exists()) && (descDir.isDirectory())) {
            log.debug("目录 " + descDirNames + " 已存在!");
            return false;
        }

        if (descDir.mkdirs()) {
            log.debug("目录 " + descDirNames + " 创建成功!");
            return true;
        }
        log.debug("目录 " + descDirNames + " 创建失败!");
        return false;
    }

    public static void writeToFile(String fileName, String content, boolean append)
    {
        try
        {
            write(new File(fileName), content, "utf-8", append);
            log.debug("文件 " + fileName + " 写入成功!");
        } catch (IOException e) {
            log.debug("文件 " + fileName + " 写入失败! " + e.getMessage());
        }
    }

    public static void writeToFile(String fileName, String content, String encoding, boolean append)
    {
        try
        {
            write(new File(fileName), content, encoding, append);
            log.debug("文件 " + fileName + " 写入成功!");
        } catch (IOException e) {
            log.debug("文件 " + fileName + " 写入失败! " + e.getMessage());
        }
    }

    private static String getEntryName(String dirPath, File file)
    {
        String dirPaths = dirPath;
        if (!(dirPaths.endsWith(File.separator)))
            dirPaths = dirPaths + File.separator;

        String filePath = file.getAbsolutePath();

        if (file.isDirectory())
            filePath = filePath + "/";

        int index = filePath.indexOf(dirPaths);

        return filePath.substring(index + dirPaths.length());
    }

    /**
     * 上传文件
     * @param uploadFile
     * @return
     */
    public static ModelAndView uploadFileAction(@RequestParam("uploadFile") MultipartFile uploadFile,String upLoadPath) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("uploadAndDownload");
        InputStream fis = null;
        OutputStream outputStream = null;
        try {
            fis = uploadFile.getInputStream();
            outputStream = new FileOutputStream(upLoadPath+uploadFile.getOriginalFilename());
            IOUtils.copy(fis,outputStream);
            modelAndView.addObject("sucess", "上传成功");
            return modelAndView;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        modelAndView.addObject("sucess", "上传失败!");
        return modelAndView;
    }
    /**
     * 下载文件
     * @param request
     * @param response
     */
    public static void downloadFileAction(HttpServletRequest request, HttpServletResponse response,String filePath,String fileName,String outName) {
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        fileName = transferName(request,fileName);
        log.info("下载文件名为:"+fileName);
        try {
            File file = new File(filePath+outName);
//            File file = new File("J:\\工作-BONC\\项目\\28所\\dsp-core-support\\uploadfile\\timg1.jpg");
            fis = new FileInputStream(file);
//            response.setHeader("Content-Disposition", "attachment; filename="+fileName);
            //解决火狐文件空格
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(response.getOutputStream());
            IOUtils.copy(bis,bos);
            response.flushBuffer();
        } catch (FileNotFoundException e) {
            log.error("FileNotFoundException {}", e);
        } catch (IOException e) {
            log.error("下载失败 {}", e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    log.error("close error {}", e);
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //获取浏览器信息
    public static String transferName(HttpServletRequest re, String fileName){
        String fileNameTra = "";
        try {
            //判断浏览器的顺序很重要
            String agent = re.getHeader("User-Agent");
            System.out.println(agent);
            if(agent.contains("Edge")){
                //Edge已测试
                System.out.println("Edge");
                //处理空格转为加号的问题
                fileNameTra=URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+","%20");
            }else if(agent.contains("Chrome")){
                //google已测试
                System.out.println("Chrome");
                fileNameTra=new String(fileName.getBytes(), "iso8859-1");
            }else if(agent.contains("Firefox")){
                //Firefox待测试
                System.out.println("Firefox");
                fileNameTra=new String(fileName.getBytes(), "iso8859-1");
            }else if(agent.contains("MSIE") || agent.contains("Trident")){
                //IE11已测试
                System.out.println("IE");
                //在IE8以后，微软使用了Trident来作为IE浏览器的标志，兼容老的版本,处理空格转为加号的问题
                fileNameTra=URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+","%20");
            }else{
                //其余浏览器  可测试并添加 待测试
                System.out.println("Other");
                fileNameTra=URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+","%20");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return fileNameTra;
    }

    /**
     * 下载文件
     * @param request
     * @param response
     */
    public static void downloadFileAction(HttpServletRequest request, HttpServletResponse response,String filePath,String fileName) {

        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
        FileInputStream fis = null;
        try {
            File file = new File((filePath+fileName).replaceAll(" ", ""));
//            File file = new File("J:\\工作-BONC\\项目\\28所\\dsp-core-support\\uploadfile\\timg1.jpg");
            fis = new FileInputStream(file);
            response.setHeader("Content-Disposition", "attachment; filename="+file.getName());
            IOUtils.copy(fis,response.getOutputStream());
            response.flushBuffer();
        } catch (FileNotFoundException e) {
            log.error("FileNotFoundException {}", e);
        } catch (IOException e) {
            log.error("下载失败 {}", e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    log.error("close error {}", e);
                }
            }
        }
    }

    public static void uploadFile(MultipartFile uploadFile,String uploadPath)
    {
        InputStream fis = null;
        OutputStream outputStream = null;
        try {
            fis = uploadFile.getInputStream();
            log.info("上传文件信息:uploadPath:"+uploadPath+",uploadFile:"+uploadFile.toString()+",uploadFilename:"+uploadFile.getOriginalFilename());
            outputStream = new FileOutputStream(uploadPath+uploadFile.getOriginalFilename());
            IOUtils.copy(fis,outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void downloadFile(HttpServletRequest request, HttpServletResponse response, String fileName)
            throws Exception
    {
        String path = request.getSession().getServletContext().getRealPath("/") + "/download/";

        FileInputStream in = new FileInputStream(path + fileName);

        response.setHeader("content-disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));

        OutputStream out = response.getOutputStream();

        byte[] buffer = new byte[1024];
        int len = 0;

        while ((len = in.read(buffer)) > 0)
        {
            out.write(buffer, 0, len);
        }

        in.close();

        out.close();
    }

    public static String readClasspthFile(String filePath)
    {
        InputStream in = FileUtils.class.getResourceAsStream(filePath);
        String fileStr = "";
        try {
            String tempStr = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            while ((tempStr = reader.readLine()) != null)
                fileStr = fileStr + tempStr;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return fileStr;
    }

    public static void mkDir(File file)
    {
        if (file.getParentFile().exists()) {
            file.mkdir();
        } else {
            mkDir(file.getParentFile());

            file.mkdirs();
        }
    }

    public static void main(String[] args) throws IOException {
        writeToFile("D:\\ftp\\test.txt", "test\t1234567\n", false);

        String file = readFileToString(new File("D:\\ftp\\test.txt"));

        System.out.println(file);
    }
}
