(ns genericgacha.web
  (:gen-class)
  (:use ring.util.response)
  (:use ring.middleware.resource)
  (:use ring.middleware.content-type)
  (:use ring.middleware.not-modified)
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [genericgacha.stats :as stats]))



(def resourced-handler
  (-> (fn [request]
        (-> (resource-response "public/index.html")
            (content-type "text/html")))
      (wrap-resource "public")
      (wrap-content-type)
      (wrap-not-modified)))

(def not-found-handler
  (-> (fn [request]
        (-> (resource-response "public/not-found.html")
            (content-type "text/html")))
      (wrap-resource "public")
      (wrap-content-type)
      (wrap-not-modified)))

(defroutes index-handler
  (GET "/" [] resourced-handler)
  (route/resources "/")
  (route/not-found not-found-handler))
