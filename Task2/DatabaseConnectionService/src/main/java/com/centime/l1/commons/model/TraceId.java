
package com.centime.l1.commons.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraceId {
    @Schema(
        name = "uid",
        type = "string",
        example = "123e4567-e89b-42d3-a456-556642440334",
        description = "Unique Identification of current App"
    )
    private String uid;
    @Schema(
        name = "appId",
        type = "string",
        example = "first-service",
        description = "Application Identification of current App"
    )
    private String appId;

}
