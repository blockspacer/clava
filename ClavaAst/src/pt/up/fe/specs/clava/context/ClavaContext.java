/**
 * Copyright 2018 SPeCS.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License. under the License.
 */

package pt.up.fe.specs.clava.context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.suikasoft.jOptions.Datakey.DataKey;
import org.suikasoft.jOptions.Datakey.KeyFactory;
import org.suikasoft.jOptions.Interfaces.DataStore;

public class ClavaContext {

    /// DATAKEYS BEGIN

    /**
     * The arguments used to call the parser.
     */
    public final static DataKey<List<String>> ARGUMENTS = KeyFactory
            .generic("arguments", new ArrayList<>());

    /**
     * IDs generator
     */
    public final static DataKey<ClavaIdGenerator> ID_GENERATOR = KeyFactory
            .object("idGenerator", ClavaIdGenerator.class)
            .setDefault(() -> new ClavaIdGenerator());

    public final static DataKey<ClavaFactory> FACTORY = KeyFactory
            .object("factory", ClavaFactory.class);

    /// DATAKEYS END

    private final DataStore data;

    public ClavaContext(List<String> arguments) {
        this.data = DataStore.newInstance(getClass());

        // Set arguments
        this.data.set(ARGUMENTS, Collections.unmodifiableList(arguments));

        // Initialize factory
        this.data.add(FACTORY, new ClavaFactory(this));
    }
}