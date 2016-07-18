/**
 * 
 */
package com.roxx.learning.servicemix.domain;

import static com.roxx.learning.servicemix.dao.JPAUserRepository.FIND_USER_BY_NAME;
import static com.roxx.learning.servicemix.dao.JPAUserRepository.GET_USER_BY_NAME;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author julien
 *
 */
@Entity
@Data
@Table(name = "TBL_USER")
@SequenceGenerator(name = "TBL_USER_ID_SEQ", sequenceName = "SEQ_USER_ID")
@NamedQueries(value = {
		@NamedQuery(name = FIND_USER_BY_NAME, query = "select u from User as u where u.name like :pName"), //
		@NamedQuery(name = GET_USER_BY_NAME, query = "select u from User as u where u.name = :pName"), //
})
public class User {

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_USER_ID_SEQ")
	private Long id;

	@Column(name = "USER_NAME", nullable = false)
	private String name;

	@Column(name = "USER_AVATAR")
	private byte[] avatar;

	@Column(name = "USER_EMAIL")
	private String email;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Column(name = "USER_ADDRESSES")
	private List<Adresse> adresses;

}
