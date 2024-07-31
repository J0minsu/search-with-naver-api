package com.msjo.shop.repository;

import com.msjo.shop.entity.Folder;
import com.msjo.shop.entity.Product;
import com.msjo.shop.entity.ProductFolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductFolderRepository extends JpaRepository<ProductFolder, Long> {
    Optional<ProductFolder> findByProductAndFolder(Product product, Folder folder);
}
