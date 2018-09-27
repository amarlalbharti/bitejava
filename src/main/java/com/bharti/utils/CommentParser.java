package com.bharti.utils;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.bharti.constraints.DateFormats;
import com.bharti.domain.Comments;

public class CommentParser {
	
	@SuppressWarnings("unchecked")
	public static JSONObject parseComment(Comments comment) {
		JSONObject json = new JSONObject();
		json.put("comment_id", comment.getCommentId());
		json.put("comment", comment.getComment());
		json.put("kid", comment.getKeynote().getKid());
		json.put("keynote", comment.getKeynote().getKeynote());
		json.put("comment_by", comment.getName());
		json.put("comment_date", DateFormats.getTimeValue(comment.getCreateDate()) );
		json.put("comment_by_email", comment.getEmail());
		
		return json;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONArray parseComment(List<Comments> comments) {
		JSONArray array = new JSONArray();
		for(Comments comment : comments) {
			array.add(parseComment(comment));
		}
		return array;
	}
}
