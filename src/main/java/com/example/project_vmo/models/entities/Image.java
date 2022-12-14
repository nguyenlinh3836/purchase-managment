package com.example.project_vmo.models.entities;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "fileType")
  private String fileType;

  @Column(name = "imageUrl")
  private String imageUrl;

  @Column
  @CreationTimestamp
  private Date createAt;

  @Column
  @UpdateTimestamp
  private Date updatedAt;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "goodsId")
  @ToString.Exclude
  private Good goods;
}
