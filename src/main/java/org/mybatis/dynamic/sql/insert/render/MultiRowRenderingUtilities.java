/**
 *    Copyright 2016-2020 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.dynamic.sql.insert.render;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.mybatis.dynamic.sql.util.AbstractColumnMapping;

public class MultiRowRenderingUtilities {
    
    private MultiRowRenderingUtilities() {}
    
    public static Function<AbstractColumnMapping, FieldAndValue> toFieldAndValue(MultiRowValuePhraseVisitor visitor) {
        return insertMapping -> MultiRowRenderingUtilities.toFieldAndValue(visitor, insertMapping);
    }
    
    public static FieldAndValue toFieldAndValue(MultiRowValuePhraseVisitor visitor,
            AbstractColumnMapping insertMapping) {
        return insertMapping.accept(visitor);
    }

    public static String calculateColumnsPhrase(List<FieldAndValue> fieldsAndValues) {
        return fieldsAndValues.stream()
                .map(FieldAndValue::fieldName)
                .collect(Collectors.joining(", ", "(", ")")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

}
