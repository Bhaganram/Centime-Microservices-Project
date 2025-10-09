
package com.centime.l1.dbconnectionservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameToColorMappingRequestVO {

    private Long parentId;
    private String name;
    private String color;
}
