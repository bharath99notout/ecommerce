package com.vedantu.ecommerce.repository;
import com.vedantu.ecommerce.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends MongoRepository<Inventory, String> {

    public Inventory findByInventoryId(long inventoryId);

    public Inventory findByProductId(long productId);
}
