(ns html5-history-test.views.common
  (:use noir.core
        hiccup.core
        hiccup.page-helpers))

(defpartial layout [& content]
            (html5
              [:head
               [:title "html5-history-test"]
               (include-css "/css/reset.css")
               (include-css "/css/default.css")
               (include-js "/js/common.js")]
              [:body
               [:div#wrapper
                content]]))
