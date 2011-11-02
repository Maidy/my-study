(ns html5-history-test.views.welcome
  (:require [html5-history-test.views.common :as common]
            [noir.content.pages :as pages]
            [noir.response :as response])
  (:use noir.core
        hiccup.core
        hiccup.page-helpers))

(defpage "/welcome" []
         (common/layout
           [:p "Welcome to html5-history-test"]))

(def tab-names {:tab1 "TAB1"
                :tab2 "TAB2"
                :tab3 "TAB3"})

(def contents {:tab1 "Hello Tab1!!!"
               :tab2 "Hello Tab2!!!"
               :tab3 "Hello Tab3!!!"})

(defpartial tab-menu [tab selected]
  ((add-optional-attrs (fn [& args]
                         [:li {:id tab} [:a {:href tab
                              :onclick "clickMenuItem(this.parentNode.id);return false;"}
                               (tab-names (keyword tab))]]))
   (if selected {:class "selected"})))

(defpartial tab [tab]
  [:ul {:id "menu"}
   (tab-menu "tab1" (= tab "tab1"))
   (tab-menu "tab2" (= tab "tab2"))
   (tab-menu "tab3" (= tab "tab3"))]
  [:div {:id "contents"} (contents (keyword tab))])

(defpage "/tab1" []
  (common/layout
   (tab "tab1")))

(defpage "/tab2" []
  (common/layout
   (tab "tab2")))

(defpage "/tab3" []
  (common/layout
   (tab "tab3")))

(defpage "/tab1.json" []
  (response/json
   { :contents (contents :tab1)}))

(defpage "/tab2.json" []
  (response/json
   { :contents (contents :tab2)}))

(defpage "/tab3.json" []
  (response/json
   { :contents (contents :tab3)}))
