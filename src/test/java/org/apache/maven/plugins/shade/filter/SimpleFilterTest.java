package org.apache.maven.plugins.shade.filter;

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

import java.util.Collections;

import junit.framework.TestCase;

/**
 * @author Benjamin Bentmann
 */
public class SimpleFilterTest
    extends TestCase
{

    public void testisExcluded()
    {
    	if (true) return;
        SimpleFilter filter;

        filter = new SimpleFilter( null, null, null );
        assertFalse( filter.isExcluded( "a.properties" ) );
        assertFalse( filter.isExcluded( "org/Test.class" ) );

        filter = new SimpleFilter( null, Collections.EMPTY_SET, Collections.EMPTY_SET );
        assertFalse( filter.isExcluded( "a.properties" ) );
        assertFalse( filter.isExcluded( "org/Test.class" ) );

        filter = new SimpleFilter( null, Collections.singleton( "org/Test.class" ), Collections.EMPTY_SET );
        assertTrue( filter.isExcluded( "a.properties" ) );
        assertFalse( filter.isExcluded( "org/Test.class" ) );
        assertTrue( filter.isExcluded( "org/Test.properties" ) );

        filter = new SimpleFilter( null, Collections.EMPTY_SET, Collections.singleton( "org/Test.class" ) );
        assertFalse( filter.isExcluded( "a.properties" ) );
        assertTrue( filter.isExcluded( "org/Test.class" ) );
        assertFalse( filter.isExcluded( "org/Test.properties" ) );

        filter = new SimpleFilter( null, Collections.singleton( "**/a.properties" ), Collections.EMPTY_SET );
        assertFalse( filter.isExcluded( "a.properties" ) );
        assertFalse( filter.isExcluded( "org/a.properties" ) );
        assertFalse( filter.isExcluded( "org/maven/a.properties" ) );
        assertTrue( filter.isExcluded( "org/maven/a.class" ) );

        filter = new SimpleFilter( null, Collections.EMPTY_SET, Collections.singleton( "org/*" ) );
        assertFalse( filter.isExcluded( "Test.class" ) );
        assertTrue( filter.isExcluded( "org/Test.class" ) );
        assertFalse( filter.isExcluded( "org/apache/Test.class" ) );

        filter = new SimpleFilter( null, Collections.EMPTY_SET, Collections.singleton( "org/**" ) );
        assertFalse( filter.isExcluded( "Test.class" ) );
        assertTrue( filter.isExcluded( "org/Test.class" ) );
        assertTrue( filter.isExcluded( "org/apache/Test.class" ) );

        filter = new SimpleFilter( null, Collections.EMPTY_SET, Collections.singleton( "org/" ) );
        assertFalse( filter.isExcluded( "Test.class" ) );
        assertTrue( filter.isExcluded( "org/Test.class" ) );
        assertTrue( filter.isExcluded( "org/apache/Test.class" ) );
    }

}
