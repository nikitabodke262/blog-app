package com.blog.entities;

import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="post")
public class Post {


	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="title", length =100, nullable= false)
	private String title;
	
	@Column(name="content", length =100, nullable= false)
	private String content;
	
	@Column(name="image")
	private String imageName;
	
	@Column(name="date")
	private Date addedDate; 
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Comment> comments = new HashSet<>();
	
//	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinTable(name="users_role", joinColumns =@JoinColumn(name="users", referencedColumnName = "id"),
//				inverseJoinColumns = @JoinColumn(name="role", referencedColumnName = "id"))
//	private Set<Role> roles = new HashSet<>();
//	
}
