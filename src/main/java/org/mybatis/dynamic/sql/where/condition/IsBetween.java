/**
 *    Copyright 2016-2018 the original author or authors.
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
package org.mybatis.dynamic.sql.where.condition;

import java.util.function.BiPredicate;
import java.util.function.Supplier;

import org.mybatis.dynamic.sql.AbstractTwoValueCondition;

public class IsBetween<T> extends AbstractTwoValueCondition<T> {

    protected IsBetween(Supplier<T> valueSupplier1, Supplier<T> valueSupplier2) {
        super(valueSupplier1, valueSupplier2);
    }
    
    protected IsBetween(Supplier<T> valueSupplier1, Supplier<T> valueSupplier2, BiPredicate<T, T> predicate) {
        super(valueSupplier1, valueSupplier2, predicate);
    }
    
    @Override
    public String renderCondition(String columnName, String placeholder1, String placeholder2) {
        return columnName + " between " + placeholder1 + " and " + placeholder2; //$NON-NLS-1$ //$NON-NLS-2$
    }

    public static class Builder<T> extends AndGatherer<T, IsBetween<T>> {
        private Builder(Supplier<T> valueSupplier1) {
            super(valueSupplier1);
        }
        
        @Override
        protected IsBetween<T> build() {
            return new IsBetween<>(valueSupplier1, valueSupplier2);
        }
    }
    
    public static <T> Builder<T> isBetween(Supplier<T> valueSupplier1) {
        return new Builder<>(valueSupplier1);
    }

    public IsBetween<T> when(BiPredicate<T, T> predicate) {
        return new IsBetween<>(valueSupplier1, valueSupplier2, predicate);
    }
}
