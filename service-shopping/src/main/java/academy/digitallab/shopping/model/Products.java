package academy.digitallab.shopping.model;

import lombok.Data;

@Data
public class Products {
    private Long id;

    private String name;
    private String description;

    private Double stock;
    private Double price;
    private String status;

    private Category category;
}
