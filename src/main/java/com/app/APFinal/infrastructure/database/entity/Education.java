package com.app.APFinal.infrastructure.database.entity;

import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EDUCATION")
public class Education {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "EDUCATION_ID")
  private Long id;

  @Column(name = "TITLE", nullable = false)
  private String title;

  @Column(name = "INSTITUTION", nullable = false)
  private String institution;

  @Column(name = "IMAGE")
  private String image;

  @Column(name = "START", nullable = false)
  private String start;

  @Column(name = "END")
  private String end;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "PROFESSIONAL_ID")
  private Professional professional;

  @Column(name = "SOFT_DELETED")
  private Boolean softDeleted;

  @Column(name = "CREATE_TIMESTAMP")
  @CreationTimestamp
  private Timestamp createTimestamp;

}
