package org.english.form;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="words")
public class Word {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="meaning")
	private String meaning;
	@Column(name="pronunciation")
	private String pronunciation;
	@Column(name="soundmark")
	private String soundmark;
	@ManyToMany(cascade={CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinTable(
            name="word_wordtype",
            joinColumns=@JoinColumn(name="word_id"),
            inverseJoinColumns=@JoinColumn(name="wordtype_id")
    )
	private Set<WordType> wordTypes;
	
	@OneToMany(mappedBy="word",cascade={CascadeType.ALL},fetch = FetchType.EAGER)
	private Set<StudentWord> studentWords;
	
	public Set<StudentWord> getStudentWords() {
		return studentWords;
	}
	public void setStudentWords(Set<StudentWord> studentWords) {
		this.studentWords = studentWords;
	}
	public Set<WordType> getWordTypes() {
		return wordTypes;
	}
	public void setWordTypes(Set<WordType> wordTypes) {
		this.wordTypes = wordTypes;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	public String getPronunciation() {
		return pronunciation;
	}
	public void setPronunciation(String pronunciation) {
		this.pronunciation = pronunciation;
	}
	public String getSoundmark() {
		return soundmark;
	}
	public void setSoundmark(String soundmark) {
		this.soundmark = soundmark;
	}
}
