package marcussilverio.github.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import marcussilverio.github.entity.ProductEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<ProductEntity> {}
