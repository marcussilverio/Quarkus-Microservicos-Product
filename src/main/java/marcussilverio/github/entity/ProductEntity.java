package marcussilverio.github.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import marcussilverio.github.dto.ProductDto;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
public class ProductEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;
  private String category;
  private String model;
  private BigDecimal price;

  public ProductEntity (ProductDto dto) {
    this.id = dto.getId();
    this.name = dto.getName();
    this.description = dto.getDescription();
    this.category = dto.getCategory();
    this.model = dto.getModel();
    this.price = dto.getPrice();
  }
}
