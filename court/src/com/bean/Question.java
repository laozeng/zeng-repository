package com.bean;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Question entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "question", catalog = "court")
public class Question implements java.io.Serializable {

	// Fields
	private Integer questionId;
	private String questionName;
	private String questionAnswer;

	// Constructors

	/** default constructor */
	public Question() {
	}

	/** full constructor */
	public Question(String questionName, String questionAnswer) {
		this.questionName = questionName;
		this.questionAnswer = questionAnswer;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "question_id", unique = true, nullable = false)
	public Integer getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	@Column(name = "question_name", length = 65535)
	public String getQuestionName() {
		return this.questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	@Column(name = "question_answer", length = 65535)
	public String getQuestionAnswer() {
		return this.questionAnswer;
	}

	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}
}