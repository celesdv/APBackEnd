package com.app.APFinal.infrastructure.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PROFESSIONAL")
public class Professional {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PROFESSIONAL_ID")
  private Long id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "IMAGE", nullable = false)
  private String image;

  @Column(name = "POSITION", nullable = false)
  private String position;

  @Column(name = "BANNER", nullable = false)
  private String banner;

  @Column(name = "DESCRIPTION", nullable = false, columnDefinition="TEXT")
  private String description;

}
