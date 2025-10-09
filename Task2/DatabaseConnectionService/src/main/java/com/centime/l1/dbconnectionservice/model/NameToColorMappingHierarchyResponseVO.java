
package com.centime.l1.dbconnectionservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameToColorMappingHierarchyResponseVO {

    private String name;
    private List<NameToColorMappingHierarchyResponseVO> subClasses = new ArrayList<>();

    public NameToColorMappingHierarchyResponseVO(String name){
        this.name = name;
    }

    public void addSubClasses(NameToColorMappingHierarchyResponseVO response){
        if(subClasses==null){
            subClasses = new ArrayList<>();
        }
        subClasses.add(response);
    }
}
