package com.example.fieldworks.jpetstore_boot.web;

import com.example.fieldworks.jpetstore_boot.domain.Category;
import com.example.fieldworks.jpetstore_boot.domain.Item;
import com.example.fieldworks.jpetstore_boot.domain.Product;
import com.example.fieldworks.jpetstore_boot.domain.logic.PetStoreFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/catalog")
public class CatalogRestController {
    private final PetStoreFacade petStore;
    public CatalogRestController(PetStoreFacade petStore) {
        this.petStore = petStore;
    }

    @GetMapping("/categories/{categoryId}")
    public List<Product> getProductListByCategory(@PathVariable String categoryId) {
        return this.petStore.getProductListByCategory(categoryId);
    }

    @GetMapping("/categories")
    public List<Category> getCategoryList() {
        return this.petStore.getCategoryList();
    }

    @GetMapping("/products/{productId}")
    public List<Item> getItemListByProductId(@PathVariable String productId) {
        return this.petStore.getItemListByProduct(productId);
    }

    @GetMapping("/items/{itemId}")
    public Item getItem(@PathVariable String itemId) {
        return this.petStore.getItem(itemId);
    }
}
