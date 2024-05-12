package com.secondhand.presentationadvertapi.domain;

import com.secondhand.presentationadvertapi.domain.events.advert.AdvertCreatedEvent;
import com.secondhand.presentationadvertapi.domain.hibernate.AggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Entity
@Table(name = "Adverts")
@Where(clause = "IsDeleted=false")
public class Advert extends AggregateRoot<Advert> {

    @Id
    @GeneratedValue(generator = "advert_id_generator")
    @GenericGenerator(
            name = "advert_id_generator",
            strategy = "com.secondhand.presentationadvertapi.domain.hibernate.AssignedIdOrSequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "prefer_sequence_per_entity", value = "true"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "10"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            })
    @Column(name = "Id")
    private Long id;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryId", foreignKey = @ForeignKey(name = "FK_AdvertCategory_CategoryId"), nullable = false)
    private Category category;

    protected Advert() {
    }

    protected Advert(long id, String title, String description, Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        raiseEvent(new AdvertCreatedEvent(id, title, description, category.getId()));
    }

    public static Advert create(long id, String title, String description, Category category) {
        return new Advert(id, title, description, category);
    }
}
