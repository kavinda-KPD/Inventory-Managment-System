package com.example.Pos2023.repository;

import com.example.Pos2023.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ItemRepo extends JpaRepository<Item,Integer> {


    List<Item> findAllByItemNameEquals(String itemName);

    List<Item> findAllByItemBrandEquals(String itemBrand);

    List<Item> findAllByItemDescriptionContaining(String itemDescription);

    List<Item> findAllByItemBrandIn(List<String> itemBrands);

    List<Item> findAllByItemNameContaining(String itemName);

}
