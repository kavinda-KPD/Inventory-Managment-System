package com.example.Pos2023.dto.paginated;

import com.example.Pos2023.dto.ItemDTO;

import java.util.List;

public class PaginatedResponseItemDTO {

    private List<ItemDTO> items;
    private int currentPage;
    private int totalPages;
    private long totalItems;

    public PaginatedResponseItemDTO() {
    }

    public PaginatedResponseItemDTO(List<ItemDTO> items, int currentPage, int totalPages, long totalItems) {
        this.items = items;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
    }

    public PaginatedResponseItemDTO(List<ItemDTO> items, long totalElements, int totalPages) {
        this.items = items;
        this.totalItems = totalElements;
        this.totalPages = totalPages;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    @Override
    public String toString() {
        return "PaginatedResponseItemDTO{" +
                "items=" + items +
                ", currentPage=" + currentPage +
                ", totalPages=" + totalPages +
                ", totalItems=" + totalItems +
                '}';
    }
}
