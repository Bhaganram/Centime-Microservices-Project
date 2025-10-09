
package com.centime.l1.dbconnectionservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NameToColorMappingResponseVO {

    private Long id;
    private Long parentId;
    private String name;
    private String color;
}
