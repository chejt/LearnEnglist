package org.english.form;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
@Entity
@Table(name="word_types")
public class WordType {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="name")
	private String name; //类型名称
	/*@ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name="word_wordtype",
            joinColumns=@JoinColumn(name="word_id"),
            inverseJoinColumns=@JoinColumn(name="wordtype_id")
    )*/
    @ManyToMany(mappedBy="wordTypes",cascade={CascadeType.ALL},fetch = FetchType.EAGER)
	private Set<Word> words;
	public Set<Word> getWords() {
		return words;
	}
	public void setWords(Set<Word> words) {
		this.words = words;
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
}
