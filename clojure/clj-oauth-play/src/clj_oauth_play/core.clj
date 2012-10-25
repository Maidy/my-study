(ns clj-oauth-play.core
  (:require [oauth.client :as oauth]
            [clj-http.client :as clent]))

(def consumer (oauth/make-consumer
               "b09f257a-0d6a-41aa-9924-5a38145713e2" ;;  CONSUMER-KEY
               "mxb-hGWht3-nv7xPvSr.RAR91J9NhMUgpEIVAtyrVRUlOy87XhdJHA00" ;; CONSUMER-SECRET
               "https://apis.daum.net/oauth/requestToken"
               "https://apis.daum.net/oauth/accessToken"
               "https://apis.daum.net/oauth/authorize"
               :hmac-sha1))

;; request token
(def request-token (oauth/request-token consumer "oob"))

;; approval url
(def approval-uri (oauth/user-approval-uri consumer
                                           (:oauth_token request-token)))

;; ;; approval-uri에서 verifier 값을 가져오는 방법은?
(def access-token-response (oauth/access-token consumer
                                               request-token
                                               "694794"))

;; ;; parameter가 있을때는 어떻게 해야 하지?
;; (def credentials (oauth/credentials consumer
;;                                     (:oauth_token access-token-response)
;;                                     (:oauth_token_secret access-token-response)
;;                                     :POST
;;                                     "https://apis.daum.net/yozm/v1_0/timeline/home.xml"
;;                                     {:status "posting from #clojure with #oauth"}))

;; (def response (http/post "https://apis.daum.net/yozm/v1_0/timeline/home.xml"
;;                          :query (merge credentials 
;;                                        {:status "posting from #clojure with #oauth"})
;;                          :parameters (http/map->params {:use-expect-continue false})
;;                          :as :string))

;; (:content response) ;; contents
