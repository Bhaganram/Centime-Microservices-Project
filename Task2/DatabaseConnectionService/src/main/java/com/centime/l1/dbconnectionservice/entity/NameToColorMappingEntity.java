
package com.centime.l1.dbconnectionservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "NAME_TO_COLOR_MAPPING")
public class NameToColorMappingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "ID_GENERATION")
    @SequenceGenerator(name = "ID_GENERATION",
            sequenceName = "ID_GENERATION",
            allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PARENT_ID")
    private Long parentId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "COLOR")
    private String color;
}
