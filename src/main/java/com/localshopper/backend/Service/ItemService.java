package com.localshopper.backend.Service;

import com.localshopper.backend.Model.Item;
import com.localshopper.backend.Model.Shop;
import com.localshopper.backend.Repository.ItemRepository;
import com.localshopper.backend.Repository.ShopRepository;
import com.localshopper.backend.dto.Shop.CreateItemRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ShopRepository shopRepository;

    public ItemService(ItemRepository itemRepository, ShopRepository shopRepository) {
        this.itemRepository = itemRepository;
        this.shopRepository = shopRepository;
    }

    public Item addItem(Long shopId, CreateItemRequest request) {
        Shop shop = shopRepository.findById(shopId).orElseThrow();

        Item item = new Item();
        item.setItemName(request.getItemName());
        item.setDescription(request.getDescription());
        item.setPrice(request.getPrice());
        item.setDiscountPrice(request.getPrice());
        item.setQuantity(request.getQuantity());
        item.setShop(shop);

        return itemRepository.save(item);
    }


    public List<Item> getItemsByShop(Long shopId) {
        return itemRepository.findByShopId(shopId);
    }
}
