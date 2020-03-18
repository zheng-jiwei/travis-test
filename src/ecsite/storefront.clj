(ns ecsite.storefront
    (:gen-class)
    (:require
      [clj-http.client :as client]
      [clojure.data.json :as json]
      [clojure.string :as str]
      [cheshire.core :as cjson]
      [ecsite.common.other :as other]
     [clojure.test :as ct]
      )

  (:import
    (java.util Date)
    (com.auth0.jwt.algorithms Algorithm)
    (com.auth0.jwt JWT JWTVerifier)
    (com.auth0.jwt.exceptions JWTCreationException JWTVerificationException)
    (com.auth0.jwt.interfaces Verification DecodedJWT))
    )

(def ^:dynamic store_setting (ref {}))

(defn id-to-base64 [^String type ^String id]
  (String. (.encode (java.util.Base64/getEncoder) (.getBytes (str "gid://bigcommerce/" type "/" id))))
  )

(defn base64-to-id [^String base64]
  (String. (.decode (java.util.Base64/getDecoder) (.getBytes base64)))
  )

(defn verify-token [token]
  (-> token (str/split #"\.") second base64-to-id json/read-str (get "eat") (* 1000) (> (.getTime (Date.))))
  )

(defn parse-token-info [token index]
  (try
    (json/read-str (String. (.decode (java.util.Base64/getUrlDecoder) (get (str/split token #"\.") index)) "UTF-8"))
    (catch Exception e
      ))
  )

(defn create-jwt [json-data ^Date expires]
  (try
    (let [expire expires
          alg (Algorithm/HMAC256 (get @store_setting "client_secret"))]
      (-> (JWT/create)
          (.withIssuer "bigcommerce")
          (.withExpiresAt expire)
          (.withClaim (-> json-data first key str) (-> json-data first val))
          (.withClaim (-> json-data second key str) (-> json-data second val))
          (.sign alg)
          )
      )
    (catch JWTCreationException e
      (prn "### e" (.getCause e))
      {:error (.getCause e)}
      ))
  )

(defn verify-jwt [jwt]
  (try
    (let [alg (Algorithm/HMAC256 (get @store_setting "client_secret"))
          verifier (-> alg JWT/require (.withIssuer (into-array ["bigcommerce"])) (.build))
          ^DecodedJWT decodeJwt (.verify verifier jwt)
          token (.getToken decodeJwt)
          ]
      (parse-token-info token 1)
      )
    (catch JWTVerificationException e
      (prn "### e" (.getCause e))
      {:error "error"}
      ))
  )

(defn get-order-info [request]
    (try
      (let [customer_token (-> request :cookies (get "customer_token") :value)
            customer_id (when (not-empty customer_token) (-> customer_token verify-jwt (get "customer_id")))
            order_id (-> request :params :order_id)
             ]
          {:status 200 :body (cjson/generate-string {:id order_id :customer_id customer_id})}
        )
      (catch Exception e
        (prn "### error=" e)
        {:status 501 :body (cjson/generate-string e)}
        ))
    )
