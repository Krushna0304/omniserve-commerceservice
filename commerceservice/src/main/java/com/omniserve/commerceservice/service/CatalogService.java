package com.omniserve.commerceservice.service;

import com.omniserve.commondblib.dto.ItemDto;
import com.omniserve.commondblib.entity.Item;
import com.omniserve.commondblib.repository.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class CatalogService {
    @Autowired
    private ItemRepository itemRepository;

    public List<ItemDto> getAllItems() {
        List<Item> itemList = itemRepository.findAll();

        if (itemList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No items found");
        }

        return itemList.stream()
                .map(item -> new ItemDto(
                        item.getItemId(),
                        item.getItemName(),
                        item.getItemDescription(),
                        item.getItemPrice(),
                        item.getItemImgUrl()
                ))
                .toList();
    }
    public ItemDto getItemById(String itemId) throws ResponseStatusException{
        Item item = itemRepository.findByItemId(itemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item Not Found"));

        return new ItemDto(
                item.getItemId(),
                item.getItemName(),
                item.getItemDescription(),
                item.getItemPrice(),
                item.getItemImgUrl()
        );
    }

}
