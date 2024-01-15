package com.example.Pos2023.service;

import com.example.Pos2023.dto.ItemDTO;
import com.example.Pos2023.dto.paginated.PaginatedResponseItemDTO;

import java.util.List;

public interface ItemService {
    String saveItem(ItemDTO itemDTO);

    String updateItem(ItemDTO itemDTO);

    List<ItemDTO> getAllItems();

    String deleteItem(int itemId);

    List<ItemDTO> getItemsByName(String itemName);

   // List<ItemDTO> getItemsByBrand(String itemBrand);

    List<ItemDTO> getItemsByDescription(String itemDescription);

    List<ItemDTO> getItemsByBrands(List<String> itemBrands);
    
    List<ItemDTO> filterItems(String itemName, List<String> itemBrands, String itemDescription);

    PaginatedResponseItemDTO getAllItemWithPaginated(int page, int size);

    String updateItemNew( ItemDTO itemDTO, int itemId );

    PaginatedResponseItemDTO filterItemsPaginated( String itemName, List<String> itemBrands, String itemDescription, int page, int size );
}
