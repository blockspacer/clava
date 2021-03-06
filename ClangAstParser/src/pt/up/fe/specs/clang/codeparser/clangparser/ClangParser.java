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

package pt.up.fe.specs.clang.codeparser.clangparser;

import java.io.File;

import org.suikasoft.jOptions.Interfaces.DataStore;

import pt.up.fe.specs.clang.parsers.ClangParserData;
import pt.up.fe.specs.clava.language.Standard;

public interface ClangParser {

    ClangParserData parse(File file, String id, Standard standard, DataStore config);

    File getLastWorkingFolder();

    String getClangDump();

    ClangParser setBaseFolder(File baseFolder);

    // ClangParser setUsePlatformLibc(boolean usePlatformLibc);

}
