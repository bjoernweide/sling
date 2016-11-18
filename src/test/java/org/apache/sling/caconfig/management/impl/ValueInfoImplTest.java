/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.sling.caconfig.management.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.caconfig.management.ValueInfo;
import org.apache.sling.caconfig.override.impl.ConfigurationOverrideManager;
import org.apache.sling.caconfig.spi.metadata.PropertyMetadata;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ValueInfoImplTest {
    
    private PropertyMetadata<String> propertyMetadata;
    
    @Mock
    private Resource contextResource;
    @Mock
    private ConfigurationOverrideManager configurationOverrideManager;

    @Before
    public void setUp() {
        propertyMetadata = new PropertyMetadata<String>("prop1", "defValue");
    }
    
    @Test
    public void testValueMetadata() {
        ValueInfo<String> underTest = new ValueInfoImpl<>("name1", "value", propertyMetadata, null, null, null,
                contextResource, "test", configurationOverrideManager);
        
        assertEquals("name1", underTest.getName());
        assertSame(propertyMetadata, underTest.getPropertyMetadata());
        assertNull(underTest.getValue());
        assertEquals("value", underTest.getEffectiveValue());
        assertNull(underTest.getConfigSourcePath());
        assertFalse(underTest.isDefault());
        assertFalse(underTest.isInherited());
        assertFalse(underTest.isOverridden());
    }

    @Test
    public void testNoValueMetadata() {
        ValueInfo<String> underTest = new ValueInfoImpl<>("name1", null, propertyMetadata, null, null, null,
                contextResource, "test", configurationOverrideManager);
        
        assertEquals("name1", underTest.getName());
        assertSame(propertyMetadata, underTest.getPropertyMetadata());
        assertNull(underTest.getValue());
        assertEquals("defValue", underTest.getEffectiveValue());
        assertNull(underTest.getConfigSourcePath());
        assertTrue(underTest.isDefault());
        assertFalse(underTest.isInherited());
        assertFalse(underTest.isOverridden());
    }

    @Test
    public void testValueNoMetadata() {
        ValueInfo<String> underTest = new ValueInfoImpl<>("name1", "value", null, null, null, null,
                contextResource, "test", configurationOverrideManager);
        
        assertEquals("name1", underTest.getName());
        assertNull(underTest.getPropertyMetadata());
        assertNull(underTest.getValue());
        assertEquals("value", underTest.getEffectiveValue());
        assertNull(underTest.getConfigSourcePath());
        assertFalse(underTest.isDefault());
        assertFalse(underTest.isInherited());
        assertFalse(underTest.isOverridden());
    }

}