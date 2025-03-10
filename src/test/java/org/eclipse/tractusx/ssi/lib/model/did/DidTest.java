/*
 * ******************************************************************************
 * Copyright (c) 2021,2023 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0 which is available at
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 * *******************************************************************************
 */

package org.eclipse.tractusx.ssi.lib.model.did;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/** The type Did test. */
public class DidTest {
  /** Test did equals. */
  @Test
  public void testDidEquals() {

    Did did1 = new Did(new DidMethod("test"), new DidMethodIdentifier("myKey"));
    Did did2 = new Did(new DidMethod("test"), new DidMethodIdentifier("myKey"));

    Assertions.assertEquals(did1, did2);
  }
}
