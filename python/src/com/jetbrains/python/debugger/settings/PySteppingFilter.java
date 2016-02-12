/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jetbrains.python.debugger.settings;

import org.jetbrains.annotations.NotNull;

public class PySteppingFilter {
  private boolean myIsEnabled;
  private @NotNull String myFilter;

  public PySteppingFilter() {
    myIsEnabled = true;
    myFilter = "";
  }

  public PySteppingFilter(boolean isEnabled, @NotNull String filter) {
    myIsEnabled = isEnabled;
    myFilter = filter;
  }

  public boolean isEnabled() {
    return myIsEnabled;
  }

  public void setEnabled(boolean enabled) {
    myIsEnabled = enabled;
  }

  @NotNull
  public String getFilter() {
    return myFilter;
  }

  public void setFilter(@NotNull String filter) {
    myFilter = filter;
  }
}
