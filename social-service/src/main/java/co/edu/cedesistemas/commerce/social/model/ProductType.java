package co.edu.cedesistemas.commerce.social.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = true)
@NodeEntity(label = "ProductType")
public class ProductType extends RepresentationModel<ProductType> {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
