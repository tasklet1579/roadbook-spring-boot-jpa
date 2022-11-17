package edu.book.roadbookspringbootjpa.repository;

import edu.book.roadbookspringbootjpa.dto.ItemSearchDto;
import edu.book.roadbookspringbootjpa.dto.MainItemDto;
import edu.book.roadbookspringbootjpa.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {
    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}
