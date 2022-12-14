package com.example.project_vmo.models.request;
import com.example.project_vmo.models.entities.Categories;
import java.util.Date;
import java.util.List;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoodDto {

  @Id
  private int goodsId;
  @NotNull(message = "name is not null")
  private String goodsName;
  @NotNull(message = "price not null")
  private double price;
  private List<ImageDto> images;
  private Categories categories;
  @NotNull(message = "supplierId not null")
  @Min(value = 1, message = "id must be larger than 1")
  private int supplierId;
  private Date createAt;
}
