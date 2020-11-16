/*
 * Copyright 2020 Red Hat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.apicurio.registry.cli;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Ales Justin
 */
public abstract class CUJsonCommand<T> extends CUCommand {
    private static final ObjectMapper mapper = new ObjectMapper();
    private Class<T> jsonType;

    public CUJsonCommand(Class<T> jsonType) {
        this.jsonType = jsonType;
    }

    abstract Object execute(T json);

    @Override
    protected Object run(InputStream data) throws IOException {
        T json = mapper.readValue(data, jsonType);
        return execute(json);
    }
}