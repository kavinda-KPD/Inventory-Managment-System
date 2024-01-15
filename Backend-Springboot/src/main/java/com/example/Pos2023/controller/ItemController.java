package com.example.Pos2023.controller;

import com.example.Pos2023.dto.ItemDTO;
import com.example.Pos2023.dto.paginated.PaginatedResponseItemDTO;
import com.example.Pos2023.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/items")
public class ItemController
{

	@Autowired
	private ItemService itemService;


	@PostMapping(path = "/save")
	public ResponseEntity<Object> saveItem( @RequestBody ItemDTO itemDTO )
	{
		String itemSavedMessage = itemService.saveItem( itemDTO );
		Map<String, String> response = new HashMap<>();
		response.put( "message", itemSavedMessage );
		return ResponseEntity.ok( response );
	}

//	@PutMapping(path = "/update")
//	public ResponseEntity<Object> updateItem( @RequestBody ItemDTO itemDTO )
//	{
//		String itemUpdatedMessage = itemService.updateItem( itemDTO );
//		Map<String, String> response = new HashMap<>();
//		response.put( "message", itemUpdatedMessage );
//		return ResponseEntity.ok( response );
//	}

	@PutMapping(path = "/update2/{itemId}")
	public ResponseEntity<Object> updateItemNew( @RequestBody ItemDTO itemDTO, @PathVariable(value = "itemId") int itemId)
	{
		String itemUpdatedMessage = itemService.updateItemNew( itemDTO, itemId );
		Map<String, String> response = new HashMap<>();
		response.put( "message", itemUpdatedMessage );
		return ResponseEntity.ok( response );
	}

	@GetMapping(path = "/get-all-items", params = { "page", "size" })
	public ResponseEntity<Object> getAllItems( @RequestParam(value = "page") int page, @RequestParam(value = "size") int size )
	{
		PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getAllItemWithPaginated( page, size );
		return ResponseEntity.ok( paginatedResponseItemDTO );
	}

	@GetMapping(path = "/filter-items", params = { "itemName","itemBrand","itemDescription", "page", "size" })
	public ResponseEntity<Object> filterItems(
			@RequestParam(value = "itemName", required = false, defaultValue = "") String itemName,
			@RequestParam(value = "itemBrand", required = false) List<String> itemBrands,
			@RequestParam(value = "itemDescription", required = false, defaultValue = "") String itemDescription ,
			@RequestParam(value = "page") int page,
			@RequestParam(value = "size") int size )
	{
		PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.filterItemsPaginated( itemName, itemBrands, itemDescription, page, size );
		return ResponseEntity.ok( paginatedResponseItemDTO );
	}

//	@GetMapping(path = "/filter-items")
//	public ResponseEntity<List<ItemDTO>> filterItems(
//			@RequestParam(value = "itemName", required = false, defaultValue = "") String itemName,
//			@RequestParam(value = "itemBrand", required = false) List<String> itemBrands,
//			@RequestParam(value = "itemDescription", required = false, defaultValue = "") String itemDescription
//	) {
////		System.out.println(itemName);
//		List<ItemDTO> filteredItems = itemService.filterItems(itemName, itemBrands, itemDescription);
//		return ResponseEntity.ok(filteredItems);
//	}

	@DeleteMapping(path = "/delete-customer/{itemId}")
	public ResponseEntity<Object> deleteItem(@PathVariable(value = "itemId") int itemId) {
		String deletedItem = itemService.deleteItem(itemId);
		Map<String, String> response = new HashMap<>();
		response.put("message", deletedItem);
		return ResponseEntity.ok(response);
	}

//	@GetMapping(path = "/get-items-by-name/{itemName}")
//	public List<ItemDTO> getItemsByName( @PathVariable(value = "itemName") String itemName )
//	{
//		List<ItemDTO> itemsToName = itemService.getItemsByName( itemName );
//		return itemsToName;
//	}
//
//	@GetMapping(path = "/get-items-by-brands")
//	public List<ItemDTO> getItemsByBrand( @RequestParam(value = "itemBrand") List<String> itemBrands )
//	{
//		List<ItemDTO> itemsToBrand = itemService.getItemsByBrands( itemBrands );
//		return itemsToBrand;
//	}
//
//	@GetMapping(path = "/get-items-by-description/{itemDescription}")
//	public List<ItemDTO> getItemsByDescription( @PathVariable(value = "itemDescription") String itemDescription )
//	{
//		List<ItemDTO> itemsToDescription = itemService.getItemsByDescription( itemDescription );
//		return itemsToDescription;
//	}

}

//	@PostMapping(path = "/save")
//	public String saveItem( @RequestBody ItemDTO itemDTO )
//	{
//		String itemSavedMessage = itemService.saveItem( itemDTO );
//		return itemSavedMessage;
//	}


//	@PutMapping(path = "/update")
//	public String updateItem( @RequestBody ItemDTO itemDTO )
//	{
//		String itemUpdatedMessage = itemService.updateItem( itemDTO );
//		return itemUpdatedMessage;
//	}


//	@GetMapping(path = "/get-all-items", params = { "page", "size" })
//	public PaginatedResponseItemDTO getAllItems( @RequestParam(value = "page") int page, @RequestParam(value = "size") int size )
//	{
//		//List<ItemDTO> allItems = itemService.getAllItems();
//		PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getAllItemWithPaginated( page, size );
//		return paginatedResponseItemDTO;
//	}


//	@GetMapping(path = "/filter-items")
//	public List<ItemDTO> filterItems( @RequestParam(value = "itemName", required = false, defaultValue = "") String itemName, @RequestParam(value = "itemBrand", required = false) List<String> itemBrands,
//			@RequestParam(value = "itemDescription", required = false, defaultValue = "") String itemDescription )
//	{
//		List<ItemDTO> filteredItems = itemService.filterItems( itemName, itemBrands, itemDescription );
//		return filteredItems;
//	}


//	@DeleteMapping(path = "/delete-customer/{itemId}")
//	public String deleteItem( @PathVariable(value = "itemId") int itemId )
//	{
//
//		String deletedItem = itemService.deleteItem( itemId );
//		return deletedItem;
//	}