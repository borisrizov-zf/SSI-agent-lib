#!/usr/bin/env bash
#
# /********************************************************************************
#  Copyright (c) 2021,2023 Contributors to the Eclipse Foundation
#
#  See the NOTICE file(s) distributed with this work for additional
#  information regarding copyright ownership.
#
#  This program and the accompanying materials are made available under the
#  terms of the Apache License, Version 2.0 which is available at
#  https://www.apache.org/licenses/LICENSE-2.0.
#
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
#  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
#  License for the specific language governing permissions and limitations
#  under the License.
#
#  SPDX-License-Identifier: Apache-2.0
# ********************************************************************************/
#
# abort on nonzero exitstatus
set -o errexit
# abort on unbound variable
set -o nounset
# don't hide errors within pipes
set -o pipefail

# converts all puml files to png images
SCRIPT_DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
BASEDIR="$SCRIPT_DIR/../puml"
TARGETDIR="$BASEDIR/../images"
mkdir -p "$TARGETDIR"
# shellcheck disable=SC2231
for FILE in $BASEDIR/*.puml; do
  echo Converting "$FILE"
  FILE_PNG=${FILE//\.puml/\.png}
  docker run --rm -i think/plantuml -tpng > "$FILE_PNG" < "$FILE"
done
mv "$BASEDIR"/*.png "$TARGETDIR"/
echo Done
