package com.bharti.constraints;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class StaticMethods 
{
	public static List<String> images = new ArrayList<>();
	public static List<String> docs = new ArrayList<>();
	public static List<String> audio = new ArrayList<>();
	public static List<String> video = new ArrayList<>();
	
	static
	{
		images.add("png");
		images.add("jpg");
		images.add("jpeg");
		images.add("gif");
		images.add("bmp");
		
		video.add("webm");
		video.add("mkv");
		video.add("flv");
		video.add("vob");
		video.add("avi");
		video.add("mov");
		video.add("mp4");
		video.add("3gp");
		
		audio.add("mp3");
		audio.add("aac");
		audio.add("ima4");
		audio.add("dvi");
		
	}
	
	public static String getFileType(String filename)
	{
		String fileType = "other";
		
		String ext = FilenameUtils.getExtension(filename);
		if(ext != null)
		{
			if(images.contains(ext.toLowerCase()))
			{
				fileType = "image";
			}
			else if(video.contains(ext.toLowerCase()))
			{
				fileType = "vedio";
			}
			else if(audio.contains(ext.toLowerCase()))
			{
				fileType = "audio";
			}
		}
		return fileType;
	}
}
