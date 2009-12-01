/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/* $Id: PSDictionaryFormatException.java 627367 2008-02-13 12:03:30Z maxberger $ */

package org.apache.fop.render.ps;

/**
 * Thrown to indicate that a formatting error has occured when
 * trying to parse a postscript dictionary object
 */
public class PSDictionaryFormatException extends Exception {

    private static final long serialVersionUID = 6492321557297860931L;

    /**
     * Default constructor
     * @param string error message
     */
    public PSDictionaryFormatException(String string) {
        super(string);
    }
}
