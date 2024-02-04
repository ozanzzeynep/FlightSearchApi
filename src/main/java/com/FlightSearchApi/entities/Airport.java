package com.FlightSearchApi.entities;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "airports")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@AttributeOverride(
        name = "id",
        column = @Column(
                name = "airport_id"
    )
)
public class Airport extends BaseEntity{


    @Column(name = "city")
    private String city;

}
