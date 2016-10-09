package com.bharti.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Lob;

import org.hibernate.validator.constraints.NotEmpty;

import com.bharti.domain.Tag;

public class QuestionAnswerModel 
{
	private long qid;
	
	@NotEmpty(message="Question is required")
	@Lob
	private String question;
	
	private Set<String> tags = new HashSet<>();

	@NotEmpty(message="Answer is required")
	@Lob
	private String answer;
	
	public long getQid() {
		return qid;
	}

	public void setQid(long qid) {
		this.qid = qid;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
