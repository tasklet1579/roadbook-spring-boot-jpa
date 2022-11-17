package edu.book.roadbookspringbootjpa.dto;

import edu.book.roadbookspringbootjpa.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSearchDto {
    private String searchDateType; // all: 상품 등록일 전체, 1d: 최근 하루 동안, 1w: 최근 일주일 동안, 1m: 최근 한 달 동안, 6m: 최근 6개월 동안
    private ItemSellStatus searchSellStatus;
    private String searchBy; // itemNm: 상품명, createdBy: 상품 등록자 아이디
    private String searchQuery = "";
}
