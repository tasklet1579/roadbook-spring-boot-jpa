package edu.book.roadbookspringbootjpa.entity.unused;

import edu.book.roadbookspringbootjpa.entity.Member;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

public class Item {
    @ManyToMany
    @JoinTable(
            name = "member_item",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Member> members;
}
