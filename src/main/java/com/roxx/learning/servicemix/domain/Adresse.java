/**
 * 
 */
package com.roxx.learning.servicemix.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author julien
 *
 */
@Data
@Entity
@Table(name = "TBL_ADDRESS")
@SequenceGenerator(name = "TBL_ADDRESS_ID_SEQ", initialValue = 1, sequenceName = "SEQ_ADDRESS_ID")
public class Adresse {

	@Id
	@Column(name = "ADDRESS_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_ADDRESS_ID_SEQ")
	private Long id;

	@Column(name = "NO_ET_VOIE", nullable = false)
	private String numeroEtVoie;

	@Column(name = "COMPLEMENT_ADDR")
	private String complementAdresse;

	@Column(name = "CODE_POSTAL", nullable = false)
	private String codePostal;

	@Column(name = "VILLE", nullable = false)
	private String ville;

}
