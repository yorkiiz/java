package com.yorkiiz.demo.annotation.Imports;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class Myimportselector implements ImportSelector {


    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.yorkiiz.project.entity.Company",
                "com.yorkiiz.project.entity.Member"

        };
    }
}
