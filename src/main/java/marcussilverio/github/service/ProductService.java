package marcussilverio.github.service;

import marcussilverio.github.dto.ProductDto;
import marcussilverio.github.entity.ProductEntity;
import marcussilverio.github.repository.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductService {
  @Inject
  ProductRepository repository;
  @Inject
  Validator validator;
  
  public List<ProductDto> getAllProducts(){
    return repository.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
  }
  public ProductDto findProductById(Long id){
    return new ProductDto(repository.findById(id));
  }
  public void createNewProduct(ProductDto dto) throws Exception{
    Set<ConstraintViolation<ProductDto>> validate = validator.validate(dto);
    if(!validate.isEmpty())
      throw new Exception("Constraint violation error");
    repository.persist(new ProductEntity(dto));
  }
  public void changeProduct(Long id, ProductDto dto) throws Exception{
    Set<ConstraintViolation<ProductDto>> validate = validator.validate(dto);
    if(!validate.isEmpty())
      throw new Exception("Constraint violation error");
    ProductEntity product = repository.findById(id);
    if(product == null)
      throw new Exception("Not Found");
    product.setName(dto.getName());
    product.setDescription(dto.getDescription());
    product.setCategory(dto.getCategory());
    product.setModel(dto.getModel());
    product.setPrice(dto.getPrice());
  }
  public void removeProduct(Long id){ repository.deleteById(id); }
}
