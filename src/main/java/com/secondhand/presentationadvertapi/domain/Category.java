package com.secondhand.presentationadvertapi.domain;

import com.secondhand.presentationadvertapi.domain.events.category.CategoryCreatedEvent;
import com.secondhand.presentationadvertapi.domain.hibernate.AggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Entity
@Table(name = "Categories")
@Where(clause = "IsDeleted=false")
public class Category extends AggregateRoot<Category> {

    @Id
    @GeneratedValue(generator = "category_id_generator")
    @GenericGenerator(
            name = "category_id_generator",
            strategy = "com.secondhand.presentationadvertapi.domain.hibernate.AssignedIdOrSequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "prefer_sequence_per_entity", value = "true"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "10"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            })
    @Column(name = "Id")
    private Long id;

    @Column(name = "Name")
    private String name;

    protected Category() {
    }

    protected Category(long id, String name) {
        this.id = id;
        this.name = name;
        raiseEvent(new CategoryCreatedEvent(id, name));
    }

    public static Category create(long id, String name) {
        return new Category(id, name);
    }
}
