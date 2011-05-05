(ns hello-midje.test.core
  (:use [hello-midje.core] :reload-all)
  (:use [clojure.test])
  (:use [midje.sweet]))

(deftest replace-me ;; FIXME: write
  (is false "No tests have been written."))

;.;. FAIL at (NO_SOURCE_FILE:1)
;.;.     Expected: 1
;.;.       Actual: "1"
(fact
 (parse-input "1") => 1)
