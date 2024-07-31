package com.msjo.shop.entity;

import com.msjo.shop.dto.req.ProductMypriceRequestDto;
import com.msjo.shop.dto.req.ProductRequestDto;
import com.msjo.shop.entity.audit.Timestamped;
import com.msjo.shop.naver.dto.ItemDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "product") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
@DynamicUpdate
public class Product extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private int lprice;

    @Column(nullable = false)
    private int myprice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "product")
    private List<ProductFolder> productFolderList = new ArrayList<>();

    public Product(ProductRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.image = requestDto.getImage();
        this.link = requestDto.getLink();
        this.lprice = requestDto.getLprice();
        this.user = user;
    }

    public void update(ProductMypriceRequestDto request) {
        this.myprice = request.getMyprice();
    }

    public void updateByItemDto(ItemDto itemDto) {
        this.lprice = itemDto.getLprice();
    }
}