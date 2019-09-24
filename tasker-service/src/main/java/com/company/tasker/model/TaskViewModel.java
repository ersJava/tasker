package com.company.tasker.model;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

public class TaskViewModel {

    private int id;

    @NotEmpty(message = "Please supply a description for task")
    @Size(min=5, max=255, message = "Description must be between 5 and 255 characters")
    private String description;

    @Future
    @NotNull(message = "Please provide a created date for task")
    private LocalDate createDate;

    @Future
    @NotNull(message = "Please provide a due date for task")
    private LocalDate dueDate;

    @Size(min=1, max=50, message = "Please enter a valid category")
    private String category;

    private String advertisement;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(String advertisement) {
        this.advertisement = advertisement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskViewModel that = (TaskViewModel) o;
        return getId() == that.getId() &&
                getDescription().equals(that.getDescription()) &&
                getCreateDate().equals(that.getCreateDate()) &&
                getDueDate().equals(that.getDueDate()) &&
                getCategory().equals(that.getCategory()) &&
                getAdvertisement().equals(that.getAdvertisement());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getCreateDate(), getDueDate(), getCategory(), getAdvertisement());
    }
}
