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

package org.eclipse.tractusx.ssi.lib.model.verifiable.credential;

import java.net.URI;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.NoArgsConstructor;
import org.eclipse.tractusx.ssi.lib.model.proof.Proof;

/** The type Verifiable credential builder. */
@NoArgsConstructor
public class VerifiableCredentialBuilder {

  private List<URI> context = List.of(VerifiableCredential.DEFAULT_CONTEXT);
  private URI id;
  private List<String> types;
  private URI issuer;
  private Instant issuanceDate;
  private Instant expirationDate;
  private List<VerifiableCredentialSubject> credentialSubject;
  private Proof proof;
  private VerifiableCredentialStatus credentialStatus;

  /**
   * Context verifiable credential builder.
   *
   * @param context the context
   * @return the verifiable credential builder
   */
  public VerifiableCredentialBuilder context(List<URI> context) {
    this.context = context;
    return this;
  }

  /**
   * Id verifiable credential builder.
   *
   * @param id the id
   * @return the verifiable credential builder
   */
  public VerifiableCredentialBuilder id(URI id) {
    this.id = id;
    return this;
  }

  /**
   * Type verifiable credential builder.
   *
   * @param types the types
   * @return the verifiable credential builder
   */
  public VerifiableCredentialBuilder type(List<String> types) {
    this.types = types;
    return this;
  }

  /**
   * Issuer verifiable credential builder.
   *
   * @param issuer the issuer
   * @return the verifiable credential builder
   */
  public VerifiableCredentialBuilder issuer(URI issuer) {
    this.issuer = issuer;
    return this;
  }

  /**
   * Issuance date verifiable credential builder.
   *
   * @param issuanceDate the issuance date
   * @return the verifiable credential builder
   */
  public VerifiableCredentialBuilder issuanceDate(Instant issuanceDate) {
    this.issuanceDate = issuanceDate;
    return this;
  }

  /**
   * Expiration date verifiable credential builder.
   *
   * @param expirationDate the expiration date
   * @return the verifiable credential builder
   */
  public VerifiableCredentialBuilder expirationDate(Instant expirationDate) {
    this.expirationDate = expirationDate;
    return this;
  }

  /**
   * Credential subject verifiable credential builder.
   *
   * @param credentialSubject the credential subject
   * @return the verifiable credential builder
   */
  public VerifiableCredentialBuilder credentialSubject(
      List<VerifiableCredentialSubject> credentialSubject) {
    this.credentialSubject = credentialSubject;
    return this;
  }

  /**
   * Credential subject verifiable credential builder.
   *
   * @param credentialSubject the credential subject
   * @return the verifiable credential builder
   */
  public VerifiableCredentialBuilder credentialSubject(
      VerifiableCredentialSubject credentialSubject) {
    this.credentialSubject = List.of(credentialSubject);
    return this;
  }

  /**
   * Proof verifiable credential builder.
   *
   * @param proof the proof
   * @return the verifiable credential builder
   */
  public VerifiableCredentialBuilder proof(Proof proof) {
    this.proof = proof;
    return this;
  }

  /**
   * Verifiable credential status verifiable credential builder.
   *
   * @param credentialStatus the credential status
   * @return the verifiable credential builder
   */
  public VerifiableCredentialBuilder verifiableCredentialStatus(
      VerifiableCredentialStatus credentialStatus) {
    this.credentialStatus = credentialStatus;
    return this;
  }

  /**
   * Build verifiable credential.
   *
   * @return the verifiable credential
   */
  public VerifiableCredential build() {
    DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern(VerifiableCredential.TIME_FORMAT).withZone(ZoneOffset.UTC);

    // Map.of does not work, as proof can be null
    Map<String, Object> map = new HashMap<>();
    map.put(VerifiableCredential.CONTEXT, context);
    map.put(VerifiableCredential.ID, id.toString());
    map.put(VerifiableCredential.TYPE, types);
    map.put(VerifiableCredential.ISSUER, issuer.toString());
    map.put(VerifiableCredential.CREDENTIAL_SUBJECT, credentialSubject);
    map.put(VerifiableCredential.ISSUANCE_DATE, formatter.format(issuanceDate));
    if (expirationDate != null) {
      map.put(VerifiableCredential.EXPIRATION_DATE, formatter.format(expirationDate));
    }
    if (proof != null) {
      map.put(VerifiableCredential.PROOF, proof);
    }

    return new VerifiableCredential(map);
  }
}
