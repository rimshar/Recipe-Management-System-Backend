package com.recipes.RecipeManagementBackend.model;

public class RecipeTO {
    private String name;
    private String instructions;
    private String link;
    private Long userId;
    private RecipeIngredientTO[] ingredientBlock;
    private String pictureLink;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "RecipeTO{" +
                "name='" + name + '\'' +
                ", instructions='" + instructions + '\'' +
                ", link='" + link + '\'' +
                ", userId=" + userId +
                ", ingredientBlock=" + ingredientBlock +
                '}';
    }

    public RecipeIngredientTO[] getIngredientBlock() {
        return ingredientBlock;
    }

    public void setIngredientBlock(RecipeIngredientTO[] ingredientBlock) {
        this.ingredientBlock = ingredientBlock;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }
}
