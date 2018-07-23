/*
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fcmchat.adminsdk.database.core;

public interface AuthTokenProvider {

  /**
   * Gets the token that should currently be used for authenticated requests.
   *
   * @param forceRefresh Pass true to get a new, up-to-date token rather than a (potentially
   *     expired) cached token.
   * @param listener Listener to be notified after operation completes.
   */
  void getToken(boolean forceRefresh, GetTokenCompletionListener listener);

  /**
   * Adds a TokenChangeListener to be notified of token changes.
   *
   * @param listener Listener to be added.
   */
  void addTokenChangeListener(TokenChangeListener listener);

  interface GetTokenCompletionListener {

    /**
     * Called if the getToken operation completed successfully. Token may be null if there is no
     * auth state currently.
     */
    void onSuccess(String token);

    /**
     * Called if the getToken operation fails.
     *
     * <p>TODO: Figure out sane way to plumb errors through.
     */
    void onError(String error);
  }

  interface TokenChangeListener {

    /**
     * Called whenever an event happens that will affect the current auth token (e.g. user logging
     * in or out). Use {@link #getToken(boolean, GetTokenCompletionListener)} method to get the
     * updated token.
     */
    void onTokenChange(String token);

  }
}
