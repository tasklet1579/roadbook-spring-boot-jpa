package edu.book.roadbookspringbootjpa.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import edu.book.roadbookspringbootjpa.constant.ItemSellStatus;
import edu.book.roadbookspringbootjpa.dto.ItemSearchDto;
import edu.book.roadbookspringbootjpa.entity.Item;
import edu.book.roadbookspringbootjpa.entity.QItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom { 
    private JPAQueryFactory queryFactory; // 동적으로 쿼리를 생성하기 위해서

    public ItemRepositoryCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    private BooleanExpression searchSellStatusEq(ItemSellStatus searchSellStatus) {
        return searchSellStatus == null ? null : // 결과값이 null이면 where절에서 해당 조건은 무시된다
                QItem.item.itemSellStatus.eq(searchSellStatus);
    }

    private BooleanExpression regDtsAfter(String searchDateType) {
        LocalDateTime localDateTime = LocalDateTime.now();

        if (StringUtils.equals("all", searchDateType) || searchDateType == null) {
            return null;
        } else if (StringUtils.equals("1d", searchDateType)) {
            localDateTime = localDateTime.minusDays(1);
        } else if (StringUtils.equals("1w", searchDateType)) {
            localDateTime = localDateTime.minusWeeks(1);
        } else if (StringUtils.equals("1m", searchDateType)) {
            localDateTime = localDateTime.minusMonths(1);
        } else if (StringUtils.equals("6m", searchDateType)) {
            localDateTime = localDateTime.minusMonths(6);
        }

        return QItem.item.regTime.after(localDateTime);
    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery) {
        if (StringUtils.equals("itemNm", searchBy)) {
            return QItem.item.itemNm.like("%" + searchQuery + "%");
        } else if (StringUtils.equals("createdBy", searchBy)) {
            return QItem.item.createdBy.like("%" + searchQuery + "%");
        }

        return null;
    }

    @Override
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
        QueryResults<Item> results = queryFactory.selectFrom(QItem.item)
                                                 // ',' 단위로 넣어줄 경우 and 조건으로 인식
                                                 .where(regDtsAfter(itemSearchDto.getSearchDateType()),
                                                         searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
                                                         searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))
                                                 .orderBy(QItem.item.id.desc())
                                                 .offset(pageable.getOffset())
                                                 .limit(pageable.getPageSize())
                                                 .fetchResults();
        
        List<Item> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }
}
