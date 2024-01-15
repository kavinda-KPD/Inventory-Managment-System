package com.example.Pos2023.service.impl;

import com.example.Pos2023.dto.ItemDTO;
import com.example.Pos2023.dto.paginated.PaginatedResponseItemDTO;
import com.example.Pos2023.entity.Item;
import com.example.Pos2023.repository.ItemRepo;
import com.example.Pos2023.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceIMPL implements ItemService
{

	@Autowired
	private ItemRepo itemRepo;

	@Override
	public String saveItem( ItemDTO itemDTO )
	{

		Item item = new Item( itemDTO.getItemId(), itemDTO.getItemName(), itemDTO.getItemBrand(), itemDTO.getItemDescription(), itemDTO.getItemPrice(), itemDTO.getItemExdate() );
		itemRepo.save( item );
		return "Save Item " + itemDTO.getItemName();
	}

	@Override
	public String updateItem( ItemDTO itemDTO )
	{

		if ( itemRepo.existsById( itemDTO.getItemId() ) )
		{
			Item item = itemRepo.getReferenceById( itemDTO.getItemId() );

			item.setItemName( itemDTO.getItemName() );
			item.setItemBrand( itemDTO.getItemBrand() );
			item.setItemDescription( itemDTO.getItemDescription() );
			item.setItemPrice( itemDTO.getItemPrice() );
			item.setItemExdate( itemDTO.getItemExdate() );

			itemRepo.save( item );

			return itemDTO.getItemName() + " Updated";

		}
		else
		{
			throw new RuntimeException( "no data found in that id" );
		}
	}

	@Override
	public String updateItemNew( ItemDTO itemDTO, int itemId )
	{
		if ( itemRepo.existsById(itemId) )
		{
			Item itemNew = itemRepo.getReferenceById(itemId);

			itemNew.setItemName( itemDTO.getItemName() );
			itemNew.setItemBrand( itemDTO.getItemBrand() );
			itemNew.setItemDescription( itemDTO.getItemDescription() );
			itemNew.setItemPrice( itemDTO.getItemPrice() );
			itemNew.setItemExdate( itemDTO.getItemExdate() );

			itemRepo.save( itemNew );

			return itemDTO.getItemName() + " Updated";

		}
		else
		{
			throw new RuntimeException( "no data found in that id" );
		}
	}

	@Override
	public List<ItemDTO> getAllItems()
	{

		List<Item> allItems = itemRepo.findAll();
		List<ItemDTO> allItemDTO = new ArrayList<>();

		for ( Item item : allItems )
		{
			ItemDTO itemDTO = new ItemDTO( item.getItemId(), item.getItemName(), item.getItemBrand(), item.getItemDescription(), item.getItemPrice(), item.getItemExdate() );
			allItemDTO.add( itemDTO );
		}
		return allItemDTO;
	}

	@Override
	public PaginatedResponseItemDTO getAllItemWithPaginated( int page, int size )
	{
		Pageable pageable = PageRequest.of( page - 1, size ); // PageRequest is zero-based, so we need to subtract 1 from the page number.
		Page<Item> itemPage = itemRepo.findAll( pageable );

		List<ItemDTO> items = itemPage.getContent().stream().map( this::convertToDTO ) // Assuming ItemDTO is a DTO class representing your Item entity.
				.collect( Collectors.toList() );

		return new PaginatedResponseItemDTO( items, itemPage.getTotalElements(), itemPage.getTotalPages() );
	}



	// Helper method to convert Item entity to DTO
	private ItemDTO convertToDTO( Item item )
	{
		// Implement this method to convert Item entity to ItemDTO
		// You can use a mapper library or manually create the ItemDTO object
		ItemDTO itemDTO = new ItemDTO( item.getItemId(), item.getItemName(), item.getItemBrand(), item.getItemDescription(), item.getItemPrice(), item.getItemExdate() );
		return itemDTO;
	}

	@Override
	public PaginatedResponseItemDTO filterItemsPaginated(
			String itemName, List<String> itemBrands, String itemDescription,
			int page, int size
	) {
		List<Item> filteredItems;

		// Fetch items from the database based on the provided itemBrands (exact matching)
		if (itemBrands != null && !itemBrands.isEmpty()) {
			filteredItems = itemRepo.findAllByItemBrandIn(itemBrands);
		} else {
			// If no itemBrands are provided, fetch all items
			filteredItems = itemRepo.findAll();
		}

		// Filter the items based on itemName and itemDescription if provided (partial matching)
		List<Item> finalFilteredItems = new ArrayList<>();
		for (Item item : filteredItems) {
			if (itemName.isEmpty() || item.getItemName().toLowerCase().contains(itemName.toLowerCase())) {
				if (itemDescription.isEmpty() || item.getItemDescription().toLowerCase().contains(itemDescription.toLowerCase())) {
					finalFilteredItems.add(item);
				}
			}
		}

		// Paginate the filtered items
		int totalElements = finalFilteredItems.size();
		int totalPages = (int) Math.ceil((double) totalElements / size);

		int startIndex = (page - 1) * size;
		int endIndex = Math.min(startIndex + size, totalElements);

		List<ItemDTO> paginatedItemDTOs = new ArrayList<>();
		for (int i = startIndex; i < endIndex; i++) {
			Item item = finalFilteredItems.get(i);
			ItemDTO itemDTO = new ItemDTO(
					item.getItemId(), item.getItemName(), item.getItemBrand(),
					item.getItemDescription(), item.getItemPrice(), item.getItemExdate()
			);
			paginatedItemDTOs.add(itemDTO);
		}

		return new PaginatedResponseItemDTO(paginatedItemDTOs, totalElements, totalPages);
	}


	@Override
	public List<ItemDTO> filterItems( String itemName, List<String> itemBrands, String itemDescription )
	{
		List<Item> filteredItems = new ArrayList<>();

		// Fetch items from the database based on the provided itemBrands (exact matching)
		if ( itemBrands != null && !itemBrands.isEmpty() )
		{
			filteredItems = itemRepo.findAllByItemBrandIn( itemBrands );
		}
		else
		{
			// If no itemBrands are provided, fetch all items
			filteredItems = itemRepo.findAll();
		}

		// Filter the items based on itemName and itemDescription if provided (partial matching)
		List<Item> finalFilteredItems = new ArrayList<>();
		for ( Item item : filteredItems )
		{
			if ( itemName.isEmpty() || item.getItemName().toLowerCase().contains( itemName.toLowerCase() ) )
			{
				if ( itemDescription.isEmpty() || item.getItemDescription().toLowerCase().contains( itemDescription.toLowerCase() ) )
				{
					finalFilteredItems.add( item );
				}
			}
		}

		// Convert the filtered items to ItemDTO objects and return the result
		List<ItemDTO> filteredItemDTOs = new ArrayList<>();
		for ( Item item : finalFilteredItems )
		{
			ItemDTO itemDTO = new ItemDTO( item.getItemId(), item.getItemName(), item.getItemBrand(), item.getItemDescription(), item.getItemPrice(), item.getItemExdate() );
			filteredItemDTOs.add( itemDTO );
		}

		return filteredItemDTOs;
	}

	//    @Override
	//    public List<ItemDTO> filterItems(String itemName, List<String> itemBrands, String itemDescription) {
	//
	//            // Fetch items from the database based on the provided itemBrands (exact matching)
	//        if (itemBrands != null && !itemBrands.isEmpty()) {
	//            List<Item> allItemsToBrand = itemRepo.findAllByItemBrandIn(itemBrands);
	//        } else {
	//            // If no itemBrands are provided, fetch all items
	//            List<Item> allItemsToBrand = itemRepo.findAll();
	//        }
	//
	//            // Fetch items from the database based on the provided itemName (partial matching)
	//        if (itemName != null && !itemName.isEmpty()) {
	//            List<Item> allItemsToName = itemRepo.findAllByItemNameContaining(itemName);
	//        } else {
	//            // If no itemName provided, fetch all items
	//            List<Item> allItemsToName = itemRepo.findAll();
	//        }
	//
	//            // Fetch items from the database based on the provided itemDescription (partial matching)
	//        if (itemName != null && !itemName.isEmpty()) {
	//            List<Item> allItemsToDescription = itemRepo.findAllByItemDescriptionContaining(itemDescription);
	//        } else {
	//            // If no itemDescription provided, fetch all items
	//            List<Item> allItemsToDescription = itemRepo.findAll();
	//        }
	//
	//
	//
	//
	//        return null;
	//    }

	@Override
	public List<ItemDTO> getItemsByName( String itemName )
	{
		List<Item> allItemsToName = itemRepo.findAllByItemNameContaining( itemName );
		List<ItemDTO> allItemToNameDTO = new ArrayList<>();

		for ( Item item : allItemsToName )
		{
			ItemDTO itemDTO = new ItemDTO( item.getItemId(), item.getItemName(), item.getItemBrand(), item.getItemDescription(), item.getItemPrice(), item.getItemExdate() );
			allItemToNameDTO.add( itemDTO );
		}
		return allItemToNameDTO;
	}

	//=======>>>>> Old method to get data from DB according to a 1 brand name
	//    @Override
	//    public List<ItemDTO> getItemsByBrand(String itemBrand) {
	//        List<Item> allItemsToBrand = itemRepo.findAllByItemBrandEquals(itemBrand);
	//        List<ItemDTO> allItemToBrandDTO = new ArrayList<>();
	//
	//        for(Item item : allItemsToBrand){
	//            ItemDTO itemDTO = new ItemDTO(
	//                    item.getItemId(),
	//                    item.getItemName(),
	//                    item.getItemBrand(),
	//                    item.getItemDescription(),
	//                    item.getItemPrice(),
	//                    item.getItemExdate()
	//            );
	//            allItemToBrandDTO.add(itemDTO);
	//        }
	//        return allItemToBrandDTO;
	//    }

	@Override
	public List<ItemDTO> getItemsByBrands( List<String> itemBrands )
	{
		List<Item> allItemsToBrand = itemRepo.findAllByItemBrandIn( itemBrands );
		List<ItemDTO> allItemToBrandDTO = new ArrayList<>();

		for ( Item item : allItemsToBrand )
		{
			ItemDTO itemDTO = new ItemDTO( item.getItemId(), item.getItemName(), item.getItemBrand(), item.getItemDescription(), item.getItemPrice(), item.getItemExdate() );
			allItemToBrandDTO.add( itemDTO );
		}
		return allItemToBrandDTO;
	}

	@Override
	public List<ItemDTO> getItemsByDescription( String itemDescription )
	{
		List<Item> allItemsToDescription = itemRepo.findAllByItemDescriptionContaining( itemDescription );
		List<ItemDTO> allItemToDescriptionDTO = new ArrayList<>();

		for ( Item item : allItemsToDescription )
		{
			ItemDTO itemDTO = new ItemDTO( item.getItemId(), item.getItemName(), item.getItemBrand(), item.getItemDescription(), item.getItemPrice(), item.getItemExdate() );
			allItemToDescriptionDTO.add( itemDTO );
		}
		return allItemToDescriptionDTO;
	}

	@Override
	public String deleteItem( int itemId )
	{

		if ( itemRepo.existsById( itemId ) )
		{
			itemRepo.deleteById( itemId );
			return "Deleted item " + itemId;
		}
		else
		{
			throw new RuntimeException( "No Item Found" );
		}
	}

}
