package com.ServerSep3.Server.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class PostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
    @ManyToOne
    @JoinColumn(name = "ctaegory_id")
    private CategoryModel category;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String imageUrl;
    @Column
    private String location;

    public PostModel() {
    }

    public PostModel(int id, UserModel user, CategoryModel category, String title, String description, String imageUrl, String location) {
        this.id = id;
        this.user = user;
        this.category = category;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
